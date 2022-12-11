package com.lucarauj.gameawards.controller.games;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lucarauj.gameawards.controller.BaseRestController;
import com.lucarauj.gameawards.domain.model.Game;
import com.lucarauj.gameawards.service.GameService;

@CrossOrigin
@RestController
public class GameRestController extends BaseRestController {
	
	@Autowired
	private GameService businessLeyer;
	
	@GetMapping("games")
	public ResponseEntity<List<Game>> findAll() {
		return ResponseEntity.ok(businessLeyer.findAll());
	}
	
	@GetMapping("games/{id}")
	public ResponseEntity<Game> findById(@PathVariable Long id) {
		return ResponseEntity.ok(businessLeyer.findById(id));
	}
	
	@PostMapping("games")
	public ResponseEntity<Game> insert(@RequestBody Game game) {
		businessLeyer.insert(game);
		return ResponseEntity.ok(game);
	}
	
	@PutMapping("games/{id}")
	public ResponseEntity<Game> update(@PathVariable Long id, @RequestBody Game game) {
		businessLeyer.update(id, game);
		return ResponseEntity.ok(game);
	}
	
	@DeleteMapping("games/{id}")
	public ResponseEntity<Game> delete(@PathVariable Long id) {
		businessLeyer.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PatchMapping("games/{id}/vote")
	public ResponseEntity<Game> update(@PathVariable Long id) {
		businessLeyer.vote(id);
		return ResponseEntity.ok().build();
	}
}
