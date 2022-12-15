package com.gamerdates.profileapi.mvc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamerdates.profileapi.bl.services.MatchService;
import com.gamerdates.profileapi.bl.services.ProfileService;
import com.gamerdates.profileapi.dal.entities.Gamer;
import com.gamerdates.profileapi.dal.entities.Match;
import com.gamerdates.profileapi.dal.entities.Profile;
import com.gamerdates.profileapi.jwt.authenticationtoken.JWTAuthenticationToken;
import com.gamerdates.profileapi.mvc.models.SuperModel;
import com.gamerdates.profileapi.mvc.models.auth.AuthFailedModel;
import com.gamerdates.profileapi.mvc.models.auth.AuthSuccessModel;
import com.gamerdates.profileapi.mvc.models.user.MatchCreateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
public class AuthRestController {

    private Map<String, GamerState> oAuth2StateTokens;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private MatchService matchService;

    @PostMapping("saveMatch")
    public ResponseEntity<SuperModel> saveMatch(@RequestBody MatchCreateModel matchCreateModel) throws JsonProcessingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth instanceof JWTAuthenticationToken)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        JWTAuthenticationToken token = (JWTAuthenticationToken) auth;
        Gamer gamer = token.getGamer();
        Match newMatch = new Match();
        newMatch.setProfile1(profileService.getProfileByGamerId(gamer.getId()));
        newMatch.setProfile2(profileService.getProfileById(matchCreateModel.getProfile2()));
        newMatch.setLove(matchCreateModel.getLove());
        matchService.save(newMatch);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("getMatches")
    public ResponseEntity<?> getMatches() throws JsonProcessingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth instanceof JWTAuthenticationToken)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        JWTAuthenticationToken token = (JWTAuthenticationToken) auth;
        Gamer gamer = token.getGamer();
        Profile profile = profileService.getProfileByGamerId(gamer.getId());
//        Profile profile1 = profileService.getProfileById(matchCreateModel.getProfile1());
//        Profile profile2 = profileService.getProfileById(matchCreateModel.getProfile2());

        List<Match> matches = matchService.getMatchesByProfileId1(profile.getId());
        List profiles = new ArrayList();
        for (Match match : matches){
            profiles.add(match.getProfile2());
        }

        return new ResponseEntity<>(profiles,HttpStatus.OK);
    }
    @GetMapping("getPrefProfiles")
    public ResponseEntity<?> getPrefProfiles() throws JsonProcessingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth instanceof JWTAuthenticationToken)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        JWTAuthenticationToken token = (JWTAuthenticationToken) auth;
        Gamer gamer = token.getGamer();

        Profile profile = profileService.getProfileByGamerId(gamer.getId());


        List<Profile> profiles = matchService.getPotentialProfiles(profile);

        return new ResponseEntity<>(profiles,HttpStatus.OK);
    }


}
