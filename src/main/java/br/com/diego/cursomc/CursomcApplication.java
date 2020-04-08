package br.com.diego.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.diego.cursomc.domain.Categoria;
import br.com.diego.cursomc.domain.Cidade;
import br.com.diego.cursomc.domain.Cliente;
import br.com.diego.cursomc.domain.Endereco;
import br.com.diego.cursomc.domain.Estado;
import br.com.diego.cursomc.domain.Produto;
import br.com.diego.cursomc.domain.enums.TipoCliente;
import br.com.diego.cursomc.repositories.CategoriaRepository;
import br.com.diego.cursomc.repositories.CidadeRepository;
import br.com.diego.cursomc.repositories.ClienteRepository;
import br.com.diego.cursomc.repositories.EnderecoRepository;
import br.com.diego.cursomc.repositories.EstadoRepository;
import br.com.diego.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3)); // Produtos associados com Categorias 
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1)); //Associações de Categorias com Produto ( mão dupla)
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

		Estado estado = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade = new Cidade(null, "Uberlandia", estado);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);
		
		estado.getCidades().addAll(Arrays.asList(cidade));
		estado2.getCidades().addAll(Arrays.asList(cidade2,cidade3));
		
		estadoRepository.saveAll(Arrays.asList(estado,estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade,cidade2,cidade3));
		
		Cliente cli1 = new Cliente(null, "Diego Marques", "di@hotmail.com", "11111111", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("1111-1111", "1-1111-1111"));
		
		Endereco e1 = new Endereco(null, "Rua 1", "1", "Casa 1", "Jardim 1", "11111-111", cli1, cidade);
		Endereco e2 = new Endereco(null, "Rua 2", "2", "Casa 2", "Jardim 2", "22222-222", cli1, cidade2);
		
		cli1.getEndereco().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		

	}

}
