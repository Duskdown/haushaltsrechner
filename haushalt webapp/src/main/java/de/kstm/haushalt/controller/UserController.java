package de.kstm.haushalt.controller;

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

	@RequestMapping
	public @ResponseBody List<User> getPersons() {
		return (List<User>) userRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> add(@RequestBody User person) {
		User newUser = userRepository.save(person);

		HttpHeaders httpHeaders = ControllerHelper.buildHttpHeaderForNewResource("{id}", newUser.getId());

		return new ResponseEntity<User>(newUser, httpHeaders,
				HttpStatus.CREATED);
	}
}
