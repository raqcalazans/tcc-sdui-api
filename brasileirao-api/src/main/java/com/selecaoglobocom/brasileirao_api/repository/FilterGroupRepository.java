package com.selecaoglobocom.brasileirao_api.repository;

import com.selecaoglobocom.brasileirao_api.entity.FilterGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FilterGroupRepository extends JpaRepository<FilterGroup, Integer> {
    @Query("SELECT fg FROM FilterGroup fg LEFT JOIN FETCH fg.games ORDER BY fg.displayOrder ASC")
    List<FilterGroup> findAllWithGamesOrderByDisplayOrderAsc();
}