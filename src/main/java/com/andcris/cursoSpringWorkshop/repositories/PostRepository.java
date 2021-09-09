package com.andcris.cursoSpringWorkshop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.andcris.cursoSpringWorkshop.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

}