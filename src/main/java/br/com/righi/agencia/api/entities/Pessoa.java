package br.com.righi.agencia.api.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Document("pessoa")
public class Pessoa {

	@Nonnull private String primeiroNome = null;
    private String segundoNome = null;
    @Nonnull private String sobrenome = null;
    @Nonnull private String dataNascimento = null;
    
	public Pessoa(String primeiroNome, String segundoNome, String sobrenome, String dataNascimento) {
		this.primeiroNome = primeiroNome;
		this.segundoNome = segundoNome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
	}
    
}
