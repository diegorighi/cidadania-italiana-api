package br.com.righi.agencia.api.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Document("documento")
public class Documento {

	@Nonnull private String cpf = null;
	
	public Documento(String cpf) {
		this.cpf = cpf;
	}
	
}
