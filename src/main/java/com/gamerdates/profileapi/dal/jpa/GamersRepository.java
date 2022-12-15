package com.gamerdates.profileapi.dal.jpa;

import com.gamerdates.profileapi.dal.entities.Gamer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamersRepository extends JpaRepository<Gamer, Long> {


}
