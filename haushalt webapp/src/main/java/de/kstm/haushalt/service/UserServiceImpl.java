package de.kstm.haushalt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.kstm.haushalt.model.User;
import de.kstm.haushalt.model.UserMonth;
import de.kstm.haushalt.repository.UserMonthRepository;
import de.kstm.haushalt.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private UserMonthRepository userMonthRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void setUserMonthRepository(UserMonthRepository userMonthRepository) {
		this.userMonthRepository = userMonthRepository;
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
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<UserMonth> getAllUsers(int year) {
		return userMonthRepository.findAllByYear(year);
	}

	@Override
	public List<UserMonth> getAllUsers(int year, int month) {
		return userMonthRepository.findAllByYearAndMonth(year, month);
	}
}
