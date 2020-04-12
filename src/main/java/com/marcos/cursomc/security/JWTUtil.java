package com.marcos.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;//Armazena as reinvidicações do token
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	public boolean tokenValido (String token) {
		Claims claims  = getClaimS(token);
		if(claims != null ) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if(username != null  && expirationDate != null  && now.before(expirationDate))  {//Instante Atual é inferior a data de expiração
				return true;
			}
		}
	return false;
	}
	
	public String getUsername (String token) {
		Claims claims  = getClaimS(token);
		if(claims != null ) {
			return claims.getSubject();
		}
		return null;
	}

	private Claims getClaimS(String token) {// Função que recupera os claims apartir do token
		try {
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}
		catch(Exception e) {
			return null;
		}
	}
	
}