package br.com.diego.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.diego.cursomc.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //retorna o usuário logado no sistema
	}
		catch (Exception e) {
			return null;
		}
	}

}
