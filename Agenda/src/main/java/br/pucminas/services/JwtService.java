package br.pucminas.services;

import br.pucminas.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.print.DocFlavor;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    protected String generateEncodedSecret(String plainSecret) {
        if (StringUtils.isEmpty(plainSecret)) {
            throw new IllegalArgumentException("JWT secret cannot be null or empty");
        }
        return Base64.getEncoder().encodeToString(plainSecret.getBytes());
    }

    protected Date getExpirationTime() {
        Date now = new Date();
        Long expireInMilis = TimeUnit.HOURS.toMillis(expireHours);
        return new Date(expireInMilis + now.getTime());
    }

    protected JwtUser getUser(String encodedSecret, String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(encodedSecret)
                .parseClaimsJws(token)
                .getBody();

        String userName = claims.getSubject();
        String role = (String) claims.get("role");
        return new JwtUser(userName, role);
    }

    public JwtUser getUser(String token) {
        return getUser(this.encodedSecret, token);
    }

    protected String getToken(String encodedSecret, JwtUser jwtUser) {
        Date now = new Date();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(jwtUser.getUserName())
                .claim("role", jwtUser.getRole())
                .setIssuedAt(now)
                .setExpiration(getExpirationTime())
                .signWith(SignatureAlgorithm.HS512, encodedSecret)
                .compact();
    }

    public String getToken(JwtUser jwtUser) {
        return getToken(this.encodedSecret, jwtUser);
    }

}


























