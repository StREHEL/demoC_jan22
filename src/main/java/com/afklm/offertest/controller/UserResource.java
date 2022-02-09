package com.afklm.offertest.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.afklm.offertest.service.UserService;
import com.afklm.offertest.service.dto.UserDTO;
import com.afklm.offertest.service.errors.BadRequestAlertException;
import com.afklm.offertest.controller.UserResource;
import com.afklm.offertest.entity.User;
import com.afklm.offertest.repository.UserRepositoryI;

@RestController
public class UserResource {
	
	private final Logger log = LoggerFactory.getLogger(UserResource.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	UserRepositoryI userRepo;
	
	/**
	 * Creates an new user from passed data.
	 * @param userDTO
	 * @return
	 */
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
		log.debug("REST request to save User : {}", userDTO);
		
		if (userDTO.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID");
        } else {        	
        	try {
        		return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);			
        	} catch (Exception e) {
        		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        	}
        }
	}

	/**
	 * Attempt to retrieve a user from its id.
	 * @param id
	 * @return
	 */
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		Optional<User> user = userService.getSingleUser(id);

		if (user.isPresent()) {
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
}
