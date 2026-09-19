package com.pesquisa.brasileirao_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pesquisa.brasileirao_api.model.Game;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g FROM Game g " +
           "LEFT JOIN FETCH g.homeTeam " +
           "LEFT JOIN FETCH g.awayTeam " +
           "LEFT JOIN FETCH g.events")
    List<Game> findAllWithTeamsAndEvents();
}
