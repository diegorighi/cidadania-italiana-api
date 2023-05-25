package br.com.righi.agencia.api.dto;

import jakarta.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ClienteDTO {
	
	@Nonnull private String mensagemStatus;
	
	@Nonnull private String primeiroNome;
	@Nonnull private String segundoNome;
	@Nonnull private String sobrenome;
	
	@Nonnull private String email;
	@Nonnull private Boolean sucesso = false;

	public ClienteDTO(String mensagemStatus, String primeiroNome, String segundoNome, String sobrenome, String email, Boolean sucesso) {
		this.mensagemStatus = mensagemStatus;
		this.primeiroNome = primeiroNome;
		this.segundoNome = segundoNome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.sucesso = sucesso;
	}

	public void setMensagemStatus(String mensagemStatus) {
		this.mensagemStatus = mensagemStatus;
	}
	
	

}
