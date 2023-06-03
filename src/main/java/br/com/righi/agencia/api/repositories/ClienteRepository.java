package br.com.righi.agencia.api.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.righi.agencia.api.entities.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {

	@Query("{'documento.cpf': ?0}")
    public Optional<Cliente> findDocumentoCpf(@Param("cpf") String cpf);

	@Query("{'credencial.login': ?0}")
	public UserDetails findByLogin(@Param("login") String login);
	
}
