package com.sdui.brasileirao_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdui.brasileirao_api.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
}