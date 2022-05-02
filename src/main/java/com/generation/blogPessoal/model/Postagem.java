package com.generation.blogPessoal.model;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tb_postagem")
public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3,max = 255)
	private String titulo;
	
	@NotNull
	@Size(max=1000)
	private String texto;
	
	@UpdateTimestamp
	private LocalDateTime dia;
	
	private String foto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getDia() {
		return dia;
	}

	public void setDia(LocalDateTime dia) {
		this.dia = dia;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}	

}
