package com.selecaoglobocom.brasileirao_api.repository;

import com.selecaoglobocom.brasileirao_api.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
}