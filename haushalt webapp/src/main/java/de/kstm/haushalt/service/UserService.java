package de.kstm.haushalt.service;

import java.util.List;

import de.kstm.haushalt.model.User;
import de.kstm.haushalt.model.UserMonth;

public interface UserService {
	User createOrModifyUser(User user);
	void deleteUser(User user);
	List<User> getAllUsers();
	List<UserMonth> getAllUsers(int year);
	List<UserMonth> getAllUsers(int year, int month);
}
