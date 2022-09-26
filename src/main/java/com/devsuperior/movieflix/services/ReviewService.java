package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	ReviewRepository repository;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	AuthService authService;
	
	
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) throws MethodArgumentNotValidException{
		
		User user = authService.authenticated();
		Review entity = new Review();
		entity.setText(dto.getText());
		Movie movie = movieRepository.getOne(dto.getMovieId());
		entity.setMovie(movie);
		entity.setUser(user);
		entity = repository.save(entity);
		return new ReviewDTO(entity);
	}
}
