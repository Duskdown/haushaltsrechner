package de.kstm.haushalt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.kstm.haushalt.model.User;
import de.kstm.haushalt.model.UserMonth;
import de.kstm.haushalt.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<User> getPersons() {
		return userService.getAllUsers();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> add(@RequestBody User user) {
		User newUser = userService.createOrModifyUser(user);
		HttpHeaders httpHeaders = ControllerHelper.buildHttpHeaderForNewResource("{id}", newUser.getId());

		return new ResponseEntity<User>(newUser, httpHeaders,
				HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{year}", method= RequestMethod.GET)
	public @ResponseBody List<UserMonth> getPersonsForYear(@PathVariable int year) {
		return userService.getAllUsers(year);
	}

	@RequestMapping(value="/{year}/{month}", method= RequestMethod.GET)
	public @ResponseBody List<User> getPersonsForYear(@PathVariable int year, @PathVariable int month) {
		return userService.getAllUsers();
	}
}
