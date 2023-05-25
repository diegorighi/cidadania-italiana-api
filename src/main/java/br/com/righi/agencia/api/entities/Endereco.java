package br.com.righi.agencia.api.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Document("endereco")
public class Endereco {

	@Nonnull private String logradouro = null;
    @Nonnull private int numeroLogradouro = 0;
    @Nonnull private String complemento = null;
    @Nonnull private String cidade = null;
    @Nonnull private String uf = null;
    @Nonnull private String cep = null;
    
	public Endereco(String logradouro, int numeroLogradouro, String complemento, String cidade, String uf, String cep) {
		this.logradouro = logradouro;
		this.numeroLogradouro = numeroLogradouro;
		this.complemento = complemento;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
	}
    
    
}
