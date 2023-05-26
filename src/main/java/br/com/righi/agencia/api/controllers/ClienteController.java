package br.com.righi.agencia.api.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.righi.agencia.api.dto.ClienteDTO;
import br.com.righi.agencia.api.entities.Cliente;
import br.com.righi.agencia.api.forms.ClienteForm;
import br.com.righi.agencia.api.services.ClienteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@PostMapping
	public ResponseEntity<ClienteDTO> cadastrar(@RequestBody ClienteForm formularioCliente) {
		log.info("###################################################");
		log.info("[INBOUND] Coletando dados do usuario");
		log.info("###################################################");
		
		ClienteDTO retorno = service.cadastrarCliente(formularioCliente);
		if(retorno.getSucesso()) {
			return ResponseEntity.status(HttpStatus.OK).body(retorno);
		}else{
			return ResponseEntity.status(HttpStatus.CONFLICT).body(retorno);
		}
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<Cliente> retornarCliente(@PathVariable("cpf") String cpf){
		log.info("###################################################");
		log.info("[INBOUND] Coletando dados do usuario");
		log.info("###################################################");
		Optional<Cliente> clienteOptional = service.retornaClientePorCpf(cpf);
		if(clienteOptional.isEmpty()) return ResponseEntity.notFound().build();
		Cliente cliente = clienteOptional.get();
	    return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}
	
	@GetMapping
	public Page<Cliente> retornarClientes(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
		
		Pageable pageable = PageRequest.of(page, size);
		return service.retornarListaClientes(pageable);
		
	}
}
