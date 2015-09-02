package de.kstm.haushalt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.kstm.haushalt.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
