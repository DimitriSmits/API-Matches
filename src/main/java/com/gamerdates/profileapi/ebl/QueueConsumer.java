package com.gamerdates.profileapi.ebl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamerdates.profileapi.bl.services.GamerService;
import com.gamerdates.profileapi.bl.services.ProfileService;
//import com.pharmapartners.settingsapi.bl.services.PatientsService;
//import com.pharmapartners.settingsapi.bl.services.SettingsService;
//import com.pharmapartners.settingsapi.dal.entities.Patient;
//import com.pharmapartners.settingsapi.dal.entities.Settings;
//import com.pharmapartners.settingsapi.mvc.models.SettingsModel;

import com.gamerdates.profileapi.dal.entities.Gamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @Autowired
    GamerService gamerService;

    @Autowired
    ProfileService profileService;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public void receiveMessageB(String message) {
        logger.info("Received (String) " + message);
        processMessageB(message);
    }

    public void receiveMessageB(byte[] message) {
        String strMessage = new String(message);
        logger.info("Received (No String) " + strMessage);
        processMessageB(strMessage);
    }

    private void processMessageB(String message) {
        try {
            Gamer gamer = new ObjectMapper().readValue(message, Gamer.class);
            gamerService.addGamer(gamer);
        } catch (JsonParseException e) {
            logger.warn("Bad JSON in message: " + message);
        } catch (JsonMappingException e) {
            logger.warn("Cannot map JSON to User: " + message);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
