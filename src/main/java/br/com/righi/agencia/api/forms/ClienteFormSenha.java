package br.com.righi.agencia.api.forms;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ClienteFormSenha {

	@Nonnull private String cpf;
	
	@Nonnull @Size(min=8, max=16)
	private String senha;
	
	public ClienteFormSenha(String cpf, String senha) {
		this.cpf = cpf;
		this.senha = senha;
	}

	
}
