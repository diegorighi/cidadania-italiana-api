package br.com.righi.agencia.api.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Document("credencial")
public class Credencial {
	
	@Nonnull private String login = null;
	
    @Nonnull 
    @Size(min = 8, max = 16) 
    private String senha = null;
    
    @Deprecated
    public Credencial() {}
    
	public Credencial(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
    
    public void setSenha(String senha) {
		this.senha = senha;
	}

}
