package br.com.righi.agencia.api.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class TokenDTO  {
	
	private String token;

	public TokenDTO(String token) {
		this.token = token;
	}
	
	
}
