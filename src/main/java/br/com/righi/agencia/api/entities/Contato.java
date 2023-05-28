package br.com.righi.agencia.api.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Document("contato")
public class Contato {

	@Nonnull private String email = null;
    @Nonnull private String celular = null;
    
    @Deprecated
    public Contato() {}
    
	public Contato(String email, String celular) {
		this.email = email;
		this.celular = celular;
	}
	
}
