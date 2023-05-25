package br.com.righi.agencia.api.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.righi.agencia.api.entities.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {

	public Optional<Cliente> findByDocumento_Cpf(String cpf);
	
	@Query("SELECT c FROM Cliente c WHERE c.documento.cpf = :cpf")
    public Optional<Cliente> findClienteByCpf(@Param("cpf") String cpf);
	
}
