package br.com.diego.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diego.cursomc.domain.Cliente;
import br.com.diego.cursomc.repositories.ClienteRepository;
import br.com.diego.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired // instaciada automaticamente pelo Spring
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

}
