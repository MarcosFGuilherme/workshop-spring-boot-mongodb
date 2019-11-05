package com.educandoweb.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.educandoweb.workshopmongo.domain.Post;
import com.educandoweb.workshopmongo.domain.User;
import com.educandoweb.workshopmongo.dto.AuthorDTO;
import com.educandoweb.workshopmongo.repositories.PostRepository;
import com.educandoweb.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository useRepository;
	@Autowired
	private PostRepository postRepository; 
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		useRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		useRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!",new AuthorDTO( maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO( maria));
		Post post3 = new Post(null, sdf.parse("25/03/2018"), "Boa noite", "Faremos happyhour hoje as 17:00hs. Abraços!", new AuthorDTO( alex));
		Post post4 = new Post(null, sdf.parse("27/03/2018"), "Beleza Galera", "Faremos almoco de despedida para Jhon hoje. Abraços!", new AuthorDTO( bob));
		
		postRepository.saveAll(Arrays.asList(post1,post2,post3,post4));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		alex.getPosts().addAll(Arrays.asList(post3));
		bob.getPosts().addAll(Arrays.asList(post4));
		
		useRepository.saveAll(Arrays.asList(maria,alex,bob));
	}
}
