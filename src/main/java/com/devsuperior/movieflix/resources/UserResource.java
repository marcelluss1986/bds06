package com.devsuperior.movieflix.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
	@RequestMapping(method = RequestMethod.GET, value = "/profile")
	public ResponseEntity<UserDTO> getProfile(){
		UserDTO dto = service.getProfile();
		return ResponseEntity.ok().body(dto);
	}
}
