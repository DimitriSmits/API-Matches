package com.gamerdates.profileapi.bl.services;

import com.gamerdates.profileapi.dal.entities.Profile;
import com.gamerdates.profileapi.dal.jpa.GamersRepository;
import com.gamerdates.profileapi.dal.jpa.ProfilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfilesRepository profilesRepository;

    @Autowired
    private GamersRepository gamersRepository;

    public Profile save(Profile profile){
        Profile updatedProfile = profilesRepository.save(profile);
        return updatedProfile;


    }

    public Profile getProfileById(Long id){

        Optional<Profile> profile = profilesRepository.findById(id);
        return  profile.get();
    }

    public Profile getProfileByGamerId(Long id){
        Profile profile = profilesRepository.findByGamerId(id);
        return  profile;
    }

}
