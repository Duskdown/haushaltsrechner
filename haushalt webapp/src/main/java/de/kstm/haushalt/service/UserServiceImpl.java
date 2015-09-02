package de.kstm.haushalt.service;

import java.util.List;

import de.kstm.haushalt.model.User;
import de.kstm.haushalt.repository.UserRepository;

public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User createOrModifyUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
}
