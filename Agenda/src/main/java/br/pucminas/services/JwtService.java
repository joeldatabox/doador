package br.pucminas.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Base64;

/**
 * Created by Joel Rodrigues on 03/11/2016.
 */
@Service
public class JwtService {
    @Value("${jwt.expire.hours}")
    private Long expireHours;

    @Value("${jwt.token.secret}")
    private String plainSecret;
    private String encodedSecret;

    protected String generateEncodedSecret(String  plainSecret){
        if(StringUtils.isEmpty(plainSecret)){
            throw new IllegalArgumentException("JWT secret cannot be null or empty");
        }
        return Base64.getEncoder().encodeToString(plainSecret.getBytes());
    }

}
