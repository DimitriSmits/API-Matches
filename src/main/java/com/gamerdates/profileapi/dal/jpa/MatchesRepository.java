package com.gamerdates.profileapi.dal.jpa;

import com.gamerdates.profileapi.dal.entities.Match;
import com.gamerdates.profileapi.dal.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchesRepository extends JpaRepository<Match, Long> {

    public List<Match> findAllByProfile1_IdAndLove(Long profile1Id, boolean love);
    public List<Match> findAllByProfile2_IdAndLove(Long profile1Id, boolean love);
    public List<Match> findAllByProfile1_Id(Long profile1Id);

}
