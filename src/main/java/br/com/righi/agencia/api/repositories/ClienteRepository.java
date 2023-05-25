package br.com.righi.agencia.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.righi.agencia.api.entities.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

	public Cliente findByPessoaDocumentoCpf(String cpf);
	
}
