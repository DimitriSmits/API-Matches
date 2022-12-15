package com.gamerdates.profileapi.bl.services;

import com.gamerdates.profileapi.dal.entities.EGender;
import com.gamerdates.profileapi.dal.entities.Match;
import com.gamerdates.profileapi.dal.entities.Profile;
import com.gamerdates.profileapi.dal.jpa.GamersRepository;
import com.gamerdates.profileapi.dal.jpa.MatchesRepository;
import com.gamerdates.profileapi.dal.jpa.ProfilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    private ProfilesRepository profilesRepository;

    @Autowired
    private GamersRepository gamersRepository;

    @Autowired
    private MatchesRepository matchesRepository;

    public Match save(Match match){
        Match newMatch = matchesRepository.save(match);
        return newMatch;
    }
    public List<Match> getMatchesByProfileId1(Long profileId){
        // Get all the matches that have been liked by the gamer
        List<Match> ownLikes = matchesRepository.findAllByProfile1_IdAndLove(profileId,true);
        // Get all the matches that liked the gamer
        List<Match> otherLikes = matchesRepository.findAllByProfile2_IdAndLove(profileId,true);
        // List of all the correct matches
        List<Match> correctMatches = new ArrayList<>();

        for (Match matchLike : ownLikes){
            for(Match likedMatch : otherLikes){
                if (matchLike.getProfile2().id()==likedMatch.getProfile1().id()){
                    correctMatches.add(matchLike);

                }
            }

        }


        return correctMatches;
    }
    public List<Profile> getPotentialProfiles(Profile profile){
        List<Profile> prefProfiles = new ArrayList<>();
        if(profile.getPreferance()==EGender.BOTH){
            prefProfiles = profilesRepository.findAllByPreferanceNotLike(profile.getGender());
        }
        else{
            prefProfiles = profilesRepository.findAllByGenderAndPreferanceOrPreferance(profile.getPreferance(),profile.getGender(),EGender.BOTH);
        }
        //Filter likedMatches
        List<Match> likedMatches = matchesRepository.findAllByProfile1_Id(profile.getId());
        for (Match likedMatch : likedMatches){
           if(prefProfiles.contains(likedMatch.getProfile2())){
               prefProfiles.remove(likedMatch.getProfile2());
           }
        }
        Collections.shuffle(prefProfiles);
//        if(prefProfiles.size()>10){
//            return prefProfiles.subList(0,10);
//        }
        return prefProfiles.subList(0,1);

    }

}
