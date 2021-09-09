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
import com.andcris.cursoSpringWorkshop.dto.CommentDTO;
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
		User u4 = new User(null, "And", "and@gmail.com");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
		
		Post p1 = new Post(null, sdf.parse("09/08/1930"), "Partiu viagem", "Vou viajar para Antártida. Abraços!", new AuthorDTO(u2));
		Post p2 = new Post(null, sdf.parse("09/08/1922"), "Bom dia", "Acordei mais um hoje!", new AuthorDTO(u3));
		Post p3 = new Post(null, sdf.parse("09/08/1922"), "Olá", "Estou em Arkham!", new AuthorDTO(u3));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!",sdf.parse("09/09/2021"), new AuthorDTO(u1));
		CommentDTO c2 = new CommentDTO("Obrigado!",sdf.parse("09/08/1930"), new AuthorDTO(u2));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!",sdf.parse("09/09/2021"), new AuthorDTO(u4));
		CommentDTO c4 = new CommentDTO("Você também!",sdf.parse("09/08/1922"), new AuthorDTO(u3));
		CommentDTO c5 = new CommentDTO("Bela cidade!",sdf.parse("09/09/2021"), new AuthorDTO(u1));
		CommentDTO c6 = new CommentDTO("Lar da Miskatonic Unversity!",sdf.parse("09/09/2021"), new AuthorDTO(u4));
		
		p1.getComments().addAll(Arrays.asList(c1, c2));
		p2.getComments().addAll(Arrays.asList(c3, c4));
		p3.getComments().addAll(Arrays.asList(c5, c6));
		
		postRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		u2.getPosts().addAll(Arrays.asList(p1));
		u3.getPosts().addAll(Arrays.asList(p2, p3));
		
		userRepository.saveAll(Arrays.asList(u2, u3));
	}
}
