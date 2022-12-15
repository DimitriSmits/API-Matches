package com.gamerdates.profileapi.bl.services;

import com.gamerdates.profileapi.dal.entities.Gamer;
import com.gamerdates.profileapi.dal.jpa.GamersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamerService {

    @Autowired
    private GamersRepository gamersRepository;

    public void addGamer(Gamer gamer) {
        gamersRepository.save(gamer);
    }

}
