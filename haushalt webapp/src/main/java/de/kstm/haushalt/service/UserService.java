package de.kstm.haushalt.service;

import java.util.List;

import de.kstm.haushalt.model.User;

public interface UserService {
	User createOrModifyUser(User user);
	void deleteUser(User user);
	List<User> getAllUser();
}
