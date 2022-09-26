package com.devsuperior.movieflix.dto;

import java.util.ArrayList;
import java.util.List;

import com.devsuperior.movieflix.entities.Genre;



public class GenreDTO {

	private Long id;
	private String name;
	
	private List<MovieDTO> movies = new ArrayList<>();
	
	public GenreDTO() {
		
	}

	public GenreDTO(Long id, String name, List<MovieDTO> movies) {
		super();
		this.id = id;
		this.name = name;
		this.movies = movies;
	}
	
	public GenreDTO(Genre entity) {
		id = entity.getId();
		name = entity.getName();
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MovieDTO> getMovies() {
		return movies;
	}
	
}
