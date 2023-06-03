package br.com.righi.agencia.api.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.annotation.Nonnull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Document("cliente")
public class Cliente implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 4593398441387436761L;

	@Id
	private String id;
	
	@Nonnull private Pessoa pessoa;
	@Nonnull private Documento documento;
	@Nonnull private Endereco endereco;
	@Nonnull private Contato contato;
	@Nonnull private Credencial credencial;
    
    private Boolean isAtivo = false;
    
    @Deprecated
    public Cliente() {}

	public Cliente(Pessoa pessoa, Documento documento, Endereco endereco, 
			Contato contato, Credencial credencial) {
		this.pessoa = pessoa;
		this.documento = documento;
		this.endereco = endereco;
		this.contato = contato;
		this.credencial = credencial;
		this.isAtivo = true;
	}
	
	public void setCredencial(Credencial credencial) {
		this.credencial = credencial;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return this.credencial.getSenha();
	}

	@Override
	public String getUsername() {
		return this.credencial.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
    
}
