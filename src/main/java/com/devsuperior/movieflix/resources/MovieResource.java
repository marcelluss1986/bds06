package com.devsuperior.movieflix.resources;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

	@Autowired
	private MovieService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
		try {
			MovieDTO obj = service.findById(id);
			return ResponseEntity.ok().body(obj);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException ("Entity not Found!");
		}
	}
	
	@GetMapping
	public ResponseEntity<Page<MovieDTO>> find(
			@RequestParam(value = "genreId", defaultValue = "0")Long genreId,
			Pageable pageable){
		Page<MovieDTO> list = service.findAllPageable(genreId, pageable);
		list.getContent();
		return ResponseEntity.ok().body(list);
	}

}
