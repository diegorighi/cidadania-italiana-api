package br.com.righi.agencia.api.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Document("cliente")
public class Cliente {
	
	@Nonnull private Pessoa pessoa;
	@Nonnull private Documento documento;
	@Nonnull private Endereco endereco;
	@Nonnull private Contato contato;
	@Nonnull private Credencial credencial;
    
    private Boolean isAtivo = false;
    
    public Cliente() {}

	public Cliente(Pessoa pessoa, Documento documento, Endereco endereco, Contato contato, Credencial credencial) {
		this.pessoa = pessoa;
		this.documento = documento;
		this.endereco = endereco;
		this.contato = contato;
		this.credencial = credencial;
		this.isAtivo = true;
	}
    
    
    

}
