package com.pesquisa.brasileirao_api.repository;

import com.pesquisa.brasileirao_api.model.Fan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FanRepository extends JpaRepository<Fan, Long> {
    // O Spring Data JPA gere automaticamente os métodos save(), findById(), etc.
}

