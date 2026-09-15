package com.selecaoglobocom.brasileirao_api.repository;

import com.selecaoglobocom.brasileirao_api.entity.GameEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameEventRepository extends JpaRepository<GameEvent, Integer> {
}