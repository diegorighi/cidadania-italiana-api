package br.com.righi.agencia.api.forms;

import jakarta.annotation.Nonnull;
import lombok.Getter;

@Getter
public class ClienteFormSenha {

	@Nonnull private String cpf;
	@Nonnull private String senha;
	
	public ClienteFormSenha(String cpf, String senha) {
		this.cpf = cpf;
		this.senha = senha;
	}

	
}
