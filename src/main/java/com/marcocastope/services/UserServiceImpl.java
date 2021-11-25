package com.marcocastope.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.marcocastope.model.Role;
import com.marcocastope.model.User;
import com.marcocastope.repository.RoleRepository;
import com.marcocastope.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) {
		Role role = roleRepository.findByName("ROLE_ADMIN");
		user.setRole(role);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findByLoginAndPassword(String username, String password) {
		User user = findByUsername(username);
		if (user != null) {
			if (passwordEncoder.matches(password, user.getPassword())) {
				return user;
			}
		}
		return null;
	}

	@Override
	public void create(User entity) {
		userRepository.save(entity);
	}

	@Override
	public void update(User entity) {
		userRepository.save(entity);
	}

	@Override
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> find(Integer id) {
		return userRepository.findById(id);
	}

}
