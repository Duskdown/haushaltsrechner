package de.kstm.haushalt.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.kstm.haushalt.model.User;
import de.kstm.haushalt.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public @ResponseBody List<User> getPersons() {
		return (List<User>) userRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> add(@RequestBody User person) {
		User newUser = userRepository.save(person);

		HttpHeaders httpHeaders = null;
		try {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("{id}").buildAndExpand(newUser.getId()).toUri();
			httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(location);
		} catch (IllegalStateException e) {

		}

		return new ResponseEntity<User>(newUser, httpHeaders,
				HttpStatus.CREATED);
	}
}
