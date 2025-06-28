package com.hivision.hivision.service.cservice;

import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.payload.request.GoogleLoginRequest;
import com.hivision.hivision.payload.response.GoogleLoginResponse;
import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.service.iservice.IAccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    final IAccountService accountService;
    final TokenService tokenService;
    final RestTemplate restTemplate = new RestTemplate(); // RestTemplate để gọi API bên ngoài

    public GoogleLoginResponse authenticateWithGoogle(GoogleLoginRequest request){
        String googleToken = request.getAccessToken();

        // Gọi Google để lấy thông tin người dùng từ token
        Map<String, Object> googleUser = getGoogleUserInfo(googleToken);

        String email = (String) googleUser.get("email");
        String name = (String) googleUser.get("name");
        String picture = (String) googleUser.get("picture");

        // Check hoặc tạo mới tài khoản
        Account account = accountService.findOrCreateByEmail(email, name, picture);

        // Tạo JWT token nội bộ
        String internalToken = tokenService.generateToken(account);

        return GoogleLoginResponse.builder()
                .authenticated(true)
                .accessToken(internalToken)
                .build();
    }

    private Map<String, Object> getGoogleUserInfo(String accessToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    "https://www.googleapis.com/oauth2/v3/userinfo",
                    HttpMethod.GET,
                    entity,
                    Map.class
            );

            return response.getBody();
        } catch (Exception ex) {
            log.error("Invalid Google token", ex);
            throw new AppException(ErrorCode.INVALID_GOOGLE_TOKEN);
        }
    }

}
