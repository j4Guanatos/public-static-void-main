package com.j4gdl.security.token;

import com.j4gdl.security.model.AccessJwtToken;
import com.j4gdl.security.model.JwtToken;
import com.j4gdl.security.model.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Emmanuel_Garcia on 3/30/2017.
 */
@Component
public class JwtTokenFactory {


    public AccessJwtToken createAccessJwtToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername()))
            throw new IllegalArgumentException("Cannot create JWT Token without username");

        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty())
            throw new IllegalArgumentException("User doesn't have any privileges");

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put("scopes", userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

        Instant currentTime = Instant.now();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer("J4GDL")
                .setIssuedAt(Date.from(currentTime))
                .setExpiration(Date.from(currentTime.plusSeconds(60 * 5)))
                .signWith(SignatureAlgorithm.HS512, "testKey")
                .compact();

        return new AccessJwtToken(token, claims);
    }

    public JwtToken createRefreshToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername())) {
            throw new IllegalArgumentException("Cannot create JWT Token without username");
        }

        Instant currentTime = Instant.now();

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put("scopes", Arrays.asList("REFRESH"));

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer("J4GDL")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(currentTime))
                .setExpiration(Date.from(currentTime.plusSeconds(60*7)))
                .signWith(SignatureAlgorithm.HS512, "testKey")
                .compact();

        return new AccessJwtToken(token, claims);
    }

}
