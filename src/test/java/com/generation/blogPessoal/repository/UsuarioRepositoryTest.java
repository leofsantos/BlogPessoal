package com.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;



import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogPessoal.model.Usuario;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

    @Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll //____STARTA UMA INFORMAÇÃO ANTES DE ENTRAR NO TESTE
	void start(){

		//____VERIFICANDO SE TEM ALGO NO BANCO DE DADOS VIRUTAL E CASO HAJA, APAGATUDO
		usuarioRepository.deleteAll();
		
		
		//___ENTRANDO NO REPOSITÓRIO E SALVANDO INFORMAÇÕES

		usuarioRepository.save(new Usuario(0L, "João da Silva", "joao@email.com.br", "13465278", "https://i.imgur.com/FETvs2O.jpg","normal"));
		
		usuarioRepository.save(new Usuario(0L, "Manuela da Silva", "manuela@email.com.br", "13465278", "https://i.imgur.com/NtyGneo.jpg","adm"));
		
		usuarioRepository.save(new Usuario(0L, "Adriana da Silva", "adriana@email.com.br", "13465278", "https://i.imgur.com/mB3VM2N.jpg","ong"));

        usuarioRepository.save(new Usuario(0L, "Paulo Antunes", "paulo@email.com.br", "13465278", "https://i.imgur.com/JR7kUFU.jpg","normal"));

	}

	@Test//___INDICA QUE É UM TESTE
	@DisplayName("Retorna 1 usuario") //____DÁ NOME AO TESTE
	public void deveRetornarUmUsuario() {

		//___BUSCA NO REPOSITÓRIO UM USUÁRIO DE EMAIL "USER@EMAIL.COM.BR"
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");

		//___CASO A BUSCA SEJA CONDIZENTE COM O DATANBASE: RUN OK
		//___CASO A BUSCA NÃO SEJA CONDIZENTE COM O DATABASE: ERROR
		//___CASO A INFORMAÇÃO SOLICITADA NO "FRONT" NÃO SEJA CONDIZENTE COM O BANCO: FAILURE
		assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));
	}

	@Test//___ANOTAÇÃO DE TESTE
	@DisplayName("Retorna 3 usuarios")//___NOME DO TESTE
	public void deveRetornarTresUsuarios() {

		//___FAZ UMA BUSCA NO REPOSITÓRIO PELO MÉTODO DE BUSCA PELO NOME QUE CONTENHA "NOME"
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");

		
		//___DA VARIÁVEL "LISTADEUSUARIOS" BUSQUE "N" USUARIOS COM NOME QUE CONTENHA "NOME"
		//___CASO EXISTAM "N" USUÁRIOS COM NOME "NOME": RUN
		//___CASO EXISTEM "X" USUÁRIOS COM NOME "NOME": FAILURE
		assertEquals(3, listaDeUsuarios.size());

		//___CONFIRME SE NA POSIÇÃO GET(0) EXISTE O NOME "NOME_0"
		assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));

		//___CONFIRME SE NA POSIÇÃO GET(1) EXISTE O NOME "NOME_1"
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Manuela da Silva"));

		//___CONFIRME SE NA POSIÇÃO GET(2) EXISTE O NOME "NOME_2"
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Adriana da Silva"));
		
	}
	
	@Test
	@DisplayName("")

	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
}