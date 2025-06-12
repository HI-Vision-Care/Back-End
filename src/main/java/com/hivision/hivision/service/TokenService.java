package com.hivision.hivision.service;

import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.repository.IAccountRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenService {

    @Autowired
    IAccountRepo accountRepo;

    @Value("${jwt.signerKey}")
    public String SECRET_KEY;

    private SecretKey getSignerKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // create token
    public String generateToken(Account account) {
        String token = Jwts.builder()
                .subject(account.getId())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignerKey())
                .compact();
        return token;
    }

    //verify token
    public Account getAccountByToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSignerKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String accountId = claims.getSubject();

        return accountRepo.findAccountById(accountId);
    }
}
