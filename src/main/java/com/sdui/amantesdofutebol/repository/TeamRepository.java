package com.sdui.amantesdofutebol.repository;

import com.sdui.amantesdofutebol.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String> {
}
