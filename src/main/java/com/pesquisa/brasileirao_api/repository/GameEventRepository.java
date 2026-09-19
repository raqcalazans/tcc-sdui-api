package com.pesquisa.brasileirao_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pesquisa.brasileirao_api.model.GameEvent;

@Repository
public interface GameEventRepository extends JpaRepository<GameEvent, Integer> {
}