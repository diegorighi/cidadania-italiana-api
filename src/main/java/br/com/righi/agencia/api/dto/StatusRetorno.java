package br.com.righi.agencia.api.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class StatusRetorno {

	private String mensagemStatus;
	private Boolean returnStatus = false;
}
