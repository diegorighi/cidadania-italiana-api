package br.com.righi.agencia.api.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Nonnull;
import lombok.Getter;

@Getter
@Document("credencial")
public class Credencial {
	
	@Nonnull private String login = null;
    @Nonnull private String senha = null;
    
	public Credencial(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
    
    public void setSenha(String senha) {
		this.senha = senha;
	}

}
