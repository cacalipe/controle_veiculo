package com.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dto.UserResponseDTO;
import com.projeto.entity.User;
import com.projeto.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<String> registerUser(@RequestBody User user){
		try {
			userService.registerUser(user);
			return new ResponseEntity<String>("Criado com sucesso!", HttpStatus.CREATED);
		} catch (Exception e) {
			String erro = "erro: " + e;
			return new ResponseEntity<String>(erro, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<String> searchById(@PathVariable Long id){
		try {
			User user = userService.searchById(id);
			return new ResponseEntity<String>(UserResponseDTO.transformInDTO(user).toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Valor não aceito.", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
}
