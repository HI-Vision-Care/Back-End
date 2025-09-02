package com.hivision.hivision.config;

import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.service.cservice.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class Filter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    @Lazy // Lazy để tránh vòng lặp phụ thuộc giữa các bean
    @Qualifier("handlerExceptionResolver")
    HandlerExceptionResolver handlerExceptionResolver;

    private final List<String> AUTH_PERMISSIONS = List.of(
            "/HiVision/account/**",
            "/HiVision/account/register",
            "/HiVision/auth/**",
            "/HiVision/ws/**",
//            "/BidKoi/account/creation",
//            "/BidKoi/account",

            "/HiVision/swagger-ui/index.html",
            "/HiVision/v3/api-docs/**",     // Allow OpenAPI docs
            "/HiVision/swagger-ui/**",       // Allow Swagger UI access
            "/HiVision/swagger-resources/**", // Allow Swagger resources
            "/HiVision/oauth2/**",

            "/HiVision/appointment/book-consultation-guest",
            "/HiVision/medical-service"

//            "/BidKoi/shipping/**",
//            "/BidKoi/account/number/**"


    );

    public boolean checkIsPublicAPI(String uri) {
        //uri :

        // nếu gặp những cái api trong list ở trên => cho phép truy cập lun => true
        AntPathMatcher pathMatch = new AntPathMatcher();

        //check token => false
        return AUTH_PERMISSIONS.stream().anyMatch(pattern -> pathMatch.match(pattern, uri));

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        //response.setHeader("Access-Control-Allow-Origin", "https://auctionkoi.azurewebsites.net"); // Hoặc thay thế "*" bằng nguồn cụ thể nếu muốn bảo mật
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5174");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//        response.setHeader("Access-Control-Allow-Origin", "https://hivision.vercel.app");
//        response.setHeader("Access-Control-Allow-Origin", "https://hivisionwebdeploy.vercel.app/");
        //response.setHeader("Access-Control-Allow-Origin", "https://bid-koi-n1yy.vercel.app");



        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        //check xem api mà nguười dùng yc có phải là một public api hay ko
        boolean isPublicAPI = checkIsPublicAPI(request.getRequestURI());

        if (isPublicAPI) {
            filterChain.doFilter(request, response);
        }else{
            String token = getToken(request);
            if (token == null) {

//                log.info("Request URI: {}", request.getRequestURI());
                //ko đc phép truy cap
                handlerExceptionResolver.resolveException(request,response,null,new AppException(ErrorCode.EMPTY_TOKEN));
                return;
            }
            // => có token
            // check token co đúng ko => lay thông tin account từ token
            Account account;
            try {
                account = tokenService.getAccountByToken(token);
            }catch (ExpiredJwtException e) {
                // response token het han
                handlerExceptionResolver.resolveException(request,response,null,new AppException(ErrorCode.TOKEN_EXPIRED));
                return;
            }catch (MalformedJwtException e) {
                // response token sai
                handlerExceptionResolver.resolveException(request,response,null,new AppException(ErrorCode.TOKEN_ERROR));
                return;
            }
            // token chuan => cho phep truy cap
            // lưu lại thong tin account
            if(account==null){
                handlerExceptionResolver.resolveException(request,response,null,new AppException(ErrorCode.USER_NOT_FOUND));
                return;
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    account,
                    token,
                    account.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //token ok cho vao
            filterChain.doFilter(request, response);
        }
    }

//    public String getToken (HttpServletRequest request) {
//        String authHedear = request.getHeader("Authorization");
//        if(authHedear == null) return null;
//        return authHedear.substring(7);
//    }

    public String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }


}
