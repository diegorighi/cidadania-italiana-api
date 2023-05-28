package br.com.righi.agencia.api.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ClienteMensagemDTO {
	
	private String mensagemStatus;
	private Boolean returnStatus = Boolean.FALSE;
	
	public ClienteMensagemDTO() {}

	public ClienteMensagemDTO(String mensagemStatus, Boolean returnStatus) {
		this.mensagemStatus = mensagemStatus;
	}

	
}
