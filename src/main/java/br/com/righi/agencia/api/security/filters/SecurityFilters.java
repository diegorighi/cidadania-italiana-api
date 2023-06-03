package br.com.righi.agencia.api.security.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.righi.agencia.api.repositories.ClienteRepository;
import br.com.righi.agencia.api.services.TokenJWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilters extends OncePerRequestFilter {

	@Autowired
	private TokenJWTService tokenService;
	
	@Autowired
	private ClienteRepository repository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		var tokenJWT = recuperarToken(request);
		if(tokenJWT != null) {
			var subject = tokenService.getSubject(tokenJWT);
			var usuario = repository.findByLogin(subject);
			
			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}

	private String recuperarToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if(authHeader != null) {
			return authHeader.replace("Bearer ", "");
		}
		return null;
	}


}
