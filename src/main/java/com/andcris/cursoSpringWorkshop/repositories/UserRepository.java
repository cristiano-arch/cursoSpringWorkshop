package com.andcris.cursoSpringWorkshop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.andcris.cursoSpringWorkshop.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
