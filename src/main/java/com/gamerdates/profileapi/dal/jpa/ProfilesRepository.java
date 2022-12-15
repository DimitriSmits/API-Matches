package com.gamerdates.profileapi.dal.jpa;

import com.gamerdates.profileapi.dal.entities.EGender;
import com.gamerdates.profileapi.dal.entities.Match;
import com.gamerdates.profileapi.dal.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfilesRepository extends JpaRepository<Profile, Long> {



    public Profile findByGamerId(Long id);
    // get all profiles by gender and filter their preferance
    public List<Profile> findAllByGenderAndPreferanceOrPreferance(EGender gender,EGender preferance,EGender preferanceboth);
    // get all profiles and filter their preferance
    public List<Profile> findAllByPreferanceNotLike(EGender preferance);
}
