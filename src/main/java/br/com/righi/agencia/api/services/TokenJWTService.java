package br.com.righi.agencia.api.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.righi.agencia.api.dto.TokenDTO;

@Service
public class TokenJWTService {

	@Value("${SECRET_TOKEN}")
	private String secretToken;

	public TokenDTO geraRetornoToken(String login) {
		return new TokenDTO(gerarToken(login));
	}

	public String gerarToken(String login) {
		try {
			var algoritmo = Algorithm.HMAC256(this.secretToken);
			return JWT.create().withIssuer("api-cidadania").withExpiresAt(dataExpiracao()).withSubject(login)
					.sign(algoritmo);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Erro ao gerar token JWT");
		}
	}

	public String getSubject(String tokenJWT) {
		try {
			var algoritmo = Algorithm.HMAC256(this.secretToken);
			return JWT.require(algoritmo)
					.withIssuer("api-cidadania")
					.build()
					.verify(tokenJWT)
					.getSubject();

		} catch (JWTVerificationException exception) {
			throw new RuntimeException("Token expirado ou inválido!");
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
