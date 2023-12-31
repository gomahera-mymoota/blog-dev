package com.kosa.springbootdeveloper.config.jwt;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyMap;

@Getter
public class JwtFactory {

    private String subject = "test@email.com";
    private Date issuedAt = new Date();
    private Date expiration = new Date(new Date().getTime() + Duration.ofDays(14).toMillis());
    private Map<String, Object> claims = emptyMap();

    @Builder
    public JwtFactory(String subject, Date issuedAt, Date expiration, Map<String, Object> claims) {
//        this.subject = subject != null ? subject : this.subject;
//        this.issuedAt = issuedAt != null ? issuedAt : this.issuedAt;
//        this.expiration = expiration != null ? expiration : this.expiration;
//        this.claims = claims != null ? claims : this.claims;

        this.subject = Optional.ofNullable(subject).orElse(this.subject);
        this.issuedAt = Optional.ofNullable(issuedAt).orElse(this.issuedAt);
        this.expiration = Optional.ofNullable(expiration).orElse(this.expiration);
        this.claims = Optional.ofNullable(claims).orElse(this.claims);
    }

    public static JwtFactory withDefaultValues() {
        return JwtFactory.builder().build();
    }

    public String createToken(JwtProperties jwtProperties) {
        return Jwts.builder()
                .setSubject(this.subject)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(this.issuedAt)
                .setExpiration(this.expiration)
                .addClaims(this.claims)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }
}
