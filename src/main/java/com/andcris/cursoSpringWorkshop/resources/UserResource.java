package com.andcris.cursoSpringWorkshop.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andcris.cursoSpringWorkshop.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		List<User> list = new ArrayList<>();
		User u1 = new User("1", "Dyer", "dyer@dyer.com");
		User u2 = new User("2", "Danforth", "dan@forth.com");
		list.addAll(Arrays.asList(u1, u2));
		return ResponseEntity.ok().body(list);
	}
}
