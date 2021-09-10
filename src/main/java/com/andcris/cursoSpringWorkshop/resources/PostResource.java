package com.andcris.cursoSpringWorkshop.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andcris.cursoSpringWorkshop.domain.Post;
import com.andcris.cursoSpringWorkshop.resources.util.URL;
import com.andcris.cursoSpringWorkshop.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

	@Autowired
	private PostService postService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = postService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitleContaining(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Post> posts = postService.findByTitleContainig(text);
		return ResponseEntity.ok().body(posts);
	}
	
	@RequestMapping(value="anysearch", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> findByAny(
			@RequestParam(value="text", defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue="") String minDate, 
			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> posts = postService.searchPosts(text, min, max);
		return ResponseEntity.ok().body(posts);
	}
}
