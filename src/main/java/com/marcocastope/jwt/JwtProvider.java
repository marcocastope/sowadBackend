package com.marcocastope.jwt;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

	private String SECRET_KEY = "jwt.secret";

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public String generateToken(String username) {
		Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
		return Jwts.builder().setSubject(username).setExpiration(date).signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}

	public boolean validateToken(String token) {

		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			logger.info("invalid token");
		}
		return false;
	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
}
