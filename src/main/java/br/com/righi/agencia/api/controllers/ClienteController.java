package br.com.righi.agencia.api.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.righi.agencia.api.dto.ClienteMensagemDTO;
import br.com.righi.agencia.api.entities.Cliente;
import br.com.righi.agencia.api.forms.ClienteEnderecoFormRecord;
import br.com.righi.agencia.api.forms.ClienteForm;
import br.com.righi.agencia.api.forms.ClienteFormSenha;
import br.com.righi.agencia.api.services.ClienteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@PostMapping
	public ResponseEntity<ClienteMensagemDTO> cadastrar(@RequestBody ClienteForm formularioCliente) {
		log.info("###################################################");
		log.info("[INBOUND] Coletando dados do usuario");
		
		ClienteMensagemDTO retorno = service.cadastrarCliente(formularioCliente);
		if(retorno.getReturnStatus()) {
			return ResponseEntity.status(HttpStatus.OK).body(retorno);
		}else{
			log.error("[OUTBOUND] CONFLITO: Usuário já existe!");
			log.error("###################################################");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(retorno);
		}
	}
	
	/**
	 * Retorna DTO para o cliente
	 * SpringBoot Security Permissions
	 * ROLES: [API_CLIENT, FRONTEND_CLIENT, DEBUGGER_DEV]
	 * @param cpf
	 * @return
	 */
	@GetMapping("/{cpf}")
	public ResponseEntity<Cliente> retornarCliente(@PathVariable("cpf") String cpf){
		log.info("###################################################");
		log.info("[INBOUND] Coletando dados do usuario");
		Optional<Cliente> clienteOptional = service.retornaClientePorCpf(cpf);
		if(clienteOptional.isEmpty()) {
			log.info("[PRIMARY SERVICE] Usuário não foi encontrado!");
			log.info("###################################################");
			return ResponseEntity.notFound().build();
		}
		Cliente cliente = clienteOptional.get();
	    return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}
	
	/**
	 * Retorna DTO para o cliente
	 * SpringBoot Security Permissions
	 * ROLES: [API_CLIENT, FRONTEND_CLIENT, DEBUGGER_DEV]
	 * @param cpf
	 * @return
	 */
	@GetMapping
	public Page<Cliente> retornarClientes(Pageable paginacao){
		return service.retornarListaClientes(paginacao);
		
	}
	
	@PatchMapping("/credencial/senha/alterar")
	public ResponseEntity<ClienteMensagemDTO> alterarSenha(@RequestBody ClienteFormSenha formularioCliente) {
		log.info("###################################################");
		log.info("[INBOUND] Coletando dados do usuario");
		
		ClienteMensagemDTO cliente = service.alterarSenhaCredencial(formularioCliente);
		if(cliente.getReturnStatus()) {
			return ResponseEntity.status(HttpStatus.OK).body(cliente);
		}else{
			log.error("[OUTBOUND] CONFLITO: Houve falha na operação!");
			log.error("###################################################");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(cliente);
		}
	}
	
	@PatchMapping("/contato/endereco/alterar")
	public ResponseEntity<ClienteMensagemDTO> alterarEndereco(@RequestBody ClienteEnderecoFormRecord enderecoForm) {
		log.info("###################################################");
		log.info("[INBOUND] Coletando dados do usuario");
		
		ClienteMensagemDTO cliente = service.alterarEnderecoCliente(enderecoForm);
		if(cliente.getReturnStatus()) {
			return ResponseEntity.status(HttpStatus.OK).body(cliente);
		}else {
			log.error("[OUTBOUND] CONFLITO: Houve falha na operação!");
			log.error("###################################################");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(cliente);
		}
	}
	
	@PatchMapping("/contato/email/alterar")
	public void alterarEmail() {
		
	}
	
	@PatchMapping("/contato/celular/alterar")
	public void alterarCelular() {
		
	}
}
