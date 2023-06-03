package br.com.righi.agencia.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.righi.agencia.api.dto.TokenDTO;
import br.com.righi.agencia.api.forms.AuthForm;
import br.com.righi.agencia.api.services.TokenJWTService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenJWTService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> loginProvider(@RequestBody @Valid AuthForm dadosAutenticacao) {
		var authToken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.senha());
		manager.authenticate(authToken);
		var mensagemRetorno = tokenService.geraRetornoToken(dadosAutenticacao.login());
		
		return ResponseEntity.ok(mensagemRetorno);
	}

}
