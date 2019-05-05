package com.mall.user.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public void te(){
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Map map = new HashMap(5);
        map.put("name", "hcq");
        map.put("name2", "hcq2");
        String token = Jwts.builder()
                .setId("88")
                .setSubject("Hcq")
                .setIssuedAt(new Date())
                .signWith(key)
                .compact();
        System.out.println(token);
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        System.out.println("IssuedAt:" + claims.getIssuedAt());
        System.out.println("IssuedAt:" + claims.get("name"));
        System.out.println("IssuedAt:" + claims.get("user"));
    }
}
