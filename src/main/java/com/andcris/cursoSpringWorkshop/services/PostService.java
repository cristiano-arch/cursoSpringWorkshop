package com.andcris.cursoSpringWorkshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andcris.cursoSpringWorkshop.domain.Post;
import com.andcris.cursoSpringWorkshop.repositories.PostRepository;
import com.andcris.cursoSpringWorkshop.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		Optional<Post> obj = postRepository.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Post.class.getName()));
	}
	
	public List<Post> findByTitleContainig(String text) {
		return postRepository.findByTitleContainingIgnoreCase(text);
	}
}
