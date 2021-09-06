package com.andcris.cursoSpringWorkshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andcris.cursoSpringWorkshop.domain.User;
import com.andcris.cursoSpringWorkshop.repositories.UserRepository;
import com.andcris.cursoSpringWorkshop.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
	}
}
