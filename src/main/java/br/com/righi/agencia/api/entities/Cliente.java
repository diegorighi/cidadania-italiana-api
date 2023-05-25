package br.com.righi.agencia.api.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Document("cliente")
public class Cliente {
	
	private Pessoa pessoa;
    private Documento documento;
    private Endereco endereco;
    private Contato contato;
    private Credencial credencial;
    

}
