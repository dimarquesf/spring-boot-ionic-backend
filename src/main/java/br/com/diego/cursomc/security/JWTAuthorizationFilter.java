package br.com.diego.cursomc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTUtil jwtUtil;
	private UserDetailsService userDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain chain) throws IOException, ServletException {
		
		String header = request.getHeader("Authorization");
		
		if (header != null && header.startsWith("Bearer")) { // verifica se header é diferente de nulo e começa com a palavra Bearer com espaço
		 UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7)); // mandar o valor que está na frente do Bearer descontando os 7 primeiros caracteres e retornar o valor do token no metodo
		 if (auth != null) {
			 SecurityContextHolder.getContext().setAuthentication(auth); 
		 }
		}
		chain.doFilter(request, response); // continua a requisão após os teste de if
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
	
		if (jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username); //buscar no banco de dados e instaciar o UsernamePassowird....
			return new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
		}
		
		return null;
	}

}
