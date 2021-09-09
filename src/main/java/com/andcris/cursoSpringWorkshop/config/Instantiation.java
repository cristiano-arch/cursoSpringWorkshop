package com.andcris.cursoSpringWorkshop.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.andcris.cursoSpringWorkshop.domain.Post;
import com.andcris.cursoSpringWorkshop.domain.User;
import com.andcris.cursoSpringWorkshop.dto.AuthorDTO;
import com.andcris.cursoSpringWorkshop.repositories.PostRepository;
import com.andcris.cursoSpringWorkshop.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User u1 = new User(null, "Cris", "cris@gmail.com");
		User u2 = new User(null, "William Dyer", "wdyer@gmail.com");
		User u3 = new User(null, "Herbert West", "hwest@gmail.com");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Post p1 = new Post(null, sdf.parse("09/08/1930"), "Partiu viagem", "Vou viajar para Antártida. Abraços!", new AuthorDTO(u2));
		Post p2 = new Post(null, sdf.parse("09/08/1922"), "Bom dia", "Acordei mais um hoje!", new AuthorDTO(u3));
		Post p3 = new Post(null, sdf.parse("09/08/1922"), "Olá", "Estou em Arkham!", new AuthorDTO(u3));
		
		postRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		u2.setPosts(Arrays.asList(p1));
		u3.setPosts(Arrays.asList(p2, p3));
		
		userRepository.saveAll(Arrays.asList(u2, u3));
	}
}
