package com.lucarauj.gameawards.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucarauj.gameawards.domain.model.Game;
import com.lucarauj.gameawards.domain.model.GameRepository;
import com.lucarauj.gameawards.service.exception.BusinessException;
import com.lucarauj.gameawards.service.exception.NoContentException;

@Service
public class GameServiceImp implements GameService {
	
	@Autowired
	private GameRepository repository;

	@Override
	public List<Game> findAll() {
		return repository.findAll(Sort.by(Direction.DESC, "votes"));		
	}

	@Override
	public Game findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NoContentException()); 
	}

	@Override
	public void insert(Game game) {
		if (Objects.nonNull(game.getId())) {
			throw new BusinessException("ID diferente de Null");
		}
		repository.save(game);		
	}

	@Override
	public void update(Long id, Game game) {
		Game gameDb = findById(id);
		if (gameDb.getId().equals(game.getId())) {
			repository.save(game);
		} else {
			throw new BusinessException("IDs diferentes");
		}
	}

	@Override
	public void delete(Long id) {
		Game gameDb = findById(id);
		repository.delete(gameDb);		
	}

	@Override
	public void vote(Long id) {
		Game gameDb = findById(id);
		gameDb.setVotes(gameDb.getVotes() + 1);
		update(id, gameDb);
	}

}
