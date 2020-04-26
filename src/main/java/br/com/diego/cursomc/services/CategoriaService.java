package br.com.diego.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diego.cursomc.domain.Categoria;
import br.com.diego.cursomc.repositories.CategoriaRepository;
import br.com.diego.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired //instaciada automaticamente pelo Spring
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
			Optional<Categoria> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
}

