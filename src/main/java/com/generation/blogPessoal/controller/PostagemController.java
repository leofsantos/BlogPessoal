package com.generation.blogPessoal.controller;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogPessoal.model.Postagem;
import com.generation.blogPessoal.repository.PostagemRepository;


@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAllPostagem(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id){
		return repository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable("titulo") String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
		
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@Valid @RequestBody Postagem postagem){
		if (repository.existsById(postagem.getTema().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	     return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping
	public ResponseEntity<Postagem> putPostagem(@Valid @RequestBody Postagem postagem){
		if (repository.existsById(postagem.getId())){
			if (repository.existsById(postagem.getTema().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}	
			else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Postagem> postagem = repository.findById(id);
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		repository.deleteById(id);
	}	
}
