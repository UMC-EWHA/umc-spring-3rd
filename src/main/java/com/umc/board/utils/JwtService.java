package com.umc.board.utils;

import com.umc.board.config.Secret;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class JwtService {

    // JWT 생성
    public String createJwt(int userIdx) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type", "jwt")
                .claim("userIdx", userIdx)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + 1*(1000*60*60*24*365)))
                .compact();
    }

    // X-ACCESS-TOKEN으로 JWT 추출
    public String getJwt() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }

    // JWT에서 userIdx 추출
    public int getUserIdx() throws Exception {
        String accessToken = getJwt();
        if (accessToken == null || accessToken.length() == 0) {
            throw new Exception("JWT를 입력해주세요.");
        }

        Jws<Claims> claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(Secret.JWT_SECRET_KEY)
                    .parseClaimsJws(accessToken);
        } catch (Exception e) {
            throw new Exception("유효하지 않은 JWT입니다.");
        }

        return claims.getBody().get("userIdx", Integer.class);
    }
}
