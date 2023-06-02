package br.com.righi.agencia.api.entities;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Document("documento")
public class Documento {

	@Indexed(unique = true) 
	@Nonnull @Size(min = 11, max = 11) 
	private String cpf = null;
	
	public Documento(String cpf) {
		this.cpf = cpf;
	}
	
}
