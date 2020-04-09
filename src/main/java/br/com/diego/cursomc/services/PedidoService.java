package br.com.diego.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diego.cursomc.domain.Pedido;
import br.com.diego.cursomc.repositories.PedidoRepository;
import br.com.diego.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired //instaciada automaticamente pelo Spring
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
			Optional<Pedido> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		
	}
}

