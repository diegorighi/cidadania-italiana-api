package br.com.righi.agencia.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.righi.agencia.api.dto.ClienteDTO;
import br.com.righi.agencia.api.entities.Cliente;
import br.com.righi.agencia.api.forms.ClienteForm;
import br.com.righi.agencia.api.handlers.ClienteHandler;
import br.com.righi.agencia.api.repositories.ClienteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@PropertySource("classpath:mensagens.properties")
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Value("${mensagem.retorno.api.sucesso}")
	private String mensagemSucesso;
	
	@Value("${mensagem.retorno.api.usuario.existente}")
	private String mensagemUsuarioExistente;
	
	
	
	@Transactional
	public ClienteDTO cadastrarCliente(ClienteForm formularioCliente) {
		Boolean sucesso = false;
		Optional<Cliente> clienteExistente = null;
		long startTime, endTime, totalTime = 0;
		
		startTime = System.currentTimeMillis();
		
		log.info("###################################################");
		log.info("[INNER SERVICE] Iniciando processamento da informacao");
		log.info("###################################################");
		clienteExistente = retornaClientePorCpf(formularioCliente.getCpf());
		
		//Se não existir usuário na base: inclua
		if(clienteExistente.isEmpty()) {
			log.info("###################################################");
			log.info("[INNER SERVICE] Iniciando persistencia em banco");
			log.info("###################################################");
			ClienteHandler handler = new ClienteHandler();
			Cliente novoCliente = handler.formToEntity(formularioCliente);
			repository.save(novoCliente);
			sucesso = true;
		}
		
		//Prepara mensagem de retorno
		ClienteDTO mensagemRetorno = criaRetorno(formularioCliente, sucesso);
		if(sucesso) {
			mensagemRetorno.setMensagemStatus(this.mensagemSucesso);
		}
		
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		
		log.info("###################################################");
		log.info(String.format("[INNER SERVICE] Tempo total de processamento: %d ms", totalTime));
		log.info("###################################################");
		
		return mensagemRetorno;
	}
	
	public Optional<Cliente> retornaClientePorCpf(String cpf) {
		long startTime, endTime, totalTime = 0;
		log.info("###################################################");
		log.info("[INNER SERVICE] Iniciando pesquisa na base de dados");
		log.info("###################################################");
		startTime = System.currentTimeMillis();
		
		
		Optional<Cliente> retornoBanco = repository.findDocumentoCpf(cpf.replaceAll("[^0-9]", ""));
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		
		log.info("###################################################");
		log.info(String.format("[INNER SERVICE] Tempo de busca: %d ms", totalTime));
		log.info("###################################################");
		
		return retornoBanco;
	}

	private ClienteDTO criaRetorno(ClienteForm formularioCliente, Boolean sucesso) {
		ClienteDTO mensagemRetorno = 
				new ClienteDTO(this.mensagemUsuarioExistente, formularioCliente.getPrimeiroNome(), 
						formularioCliente.getSegundoNome(), formularioCliente.getSobrenome(), 
						formularioCliente.getEmail(), sucesso);
		return mensagemRetorno;
	}
	
	public Page<Cliente> retornarListaClientes(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	private void releaseContador(long inicio, long fim, long total) {
		inicio = fim = total = 0;
	}
	
}

	