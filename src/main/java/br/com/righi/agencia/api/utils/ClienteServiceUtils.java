package br.com.righi.agencia.api.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import br.com.righi.agencia.api.dto.ClienteMensagemDTO;
import br.com.righi.agencia.api.entities.Cliente;
import br.com.righi.agencia.api.forms.ClienteCelularFormRecord;
import br.com.righi.agencia.api.forms.ClienteEmailFormRecord;
import br.com.righi.agencia.api.forms.ClienteEnderecoFormRecord;
import br.com.righi.agencia.api.forms.ClienteSenhaFormRecord;

@Component
@PropertySource("classpath:mensagens.properties")
public class ClienteServiceUtils {
	
	
	@Value("${email.regex.validation}")
	private String regexEmailValidation;
	
	@Autowired
	private InsereClienteMensagem mensagemInsert;
	
	@Autowired
	private AlterarSenhaMensagem mensagemSenhaUpdate;
	
	@Autowired
	private AlterarEnderecoMensagem mensagemEnderecoUpdate;
	
	@Autowired
	private AlterarEmailMensagem mensagemEmailUpdate;
	
	@Autowired
	private AlterarCelularMensagem mensagemCelularUpdate;

	public void criaRetornoInserirCliente(ClienteMensagemDTO cliente) {
		mensagemInsert.build(cliente);
	}
	
	public void criaRetornoAlterarSenha(ClienteMensagemDTO cliente) {
		mensagemSenhaUpdate.build(cliente);
	}
	
	public void criaRetornoAlteraEndereco(ClienteMensagemDTO cliente) {
		mensagemEnderecoUpdate.build(cliente);
	}
	
	public void criaRetornoAlteraEmail(ClienteMensagemDTO cliente) {
		mensagemEmailUpdate.build(cliente);
	}
	
	public void criaRetornoAlteraCelular(ClienteMensagemDTO cliente) {
		mensagemCelularUpdate.build(cliente);
	}
	
	public void bindSenha(ClienteSenhaFormRecord formulario, Cliente mongoDbCliente) {
		String novaSenha = BCrypt.hashpw(formulario.senha(), BCrypt.gensalt());
		mongoDbCliente.getCredencial().setSenha(novaSenha);
	}
	
	public void bindEndereco(Cliente mongoDbCliente, ClienteEnderecoFormRecord enderecoForm) {
		mongoDbCliente.getEndereco().setLogradouro(enderecoForm.logradouro());
		mongoDbCliente.getEndereco().setNumeroLogradouro(enderecoForm.numeroLogradouro());
		mongoDbCliente.getEndereco().setComplemento(enderecoForm.complemento());
		mongoDbCliente.getEndereco().setCidade(enderecoForm.cidade());
		mongoDbCliente.getEndereco().setUf(enderecoForm.uf());
		mongoDbCliente.getEndereco().setCep(enderecoForm.cep());
	}
	
	public void bindEmail(Cliente mongoDbCliente, ClienteEmailFormRecord enderecoForm) {
		mongoDbCliente.getContato().setEmail(enderecoForm.email());
	}
	
	public boolean emailValido(String email) {
        String regex = this.regexEmailValidation;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

	public void bindCelular(Cliente mongoDbCliente, ClienteCelularFormRecord enderecoForm) {
		mongoDbCliente.getContato().setCelular(enderecoForm.celular());
	}
}
