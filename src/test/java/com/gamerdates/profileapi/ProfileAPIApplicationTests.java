package com.gamerdates.profileapi;

import com.gamerdates.profileapi.bl.services.ProfileService;
import com.gamerdates.profileapi.config.AuthAPITestConfiguration;
import com.gamerdates.profileapi.dal.jpa.GamersRepository;

import com.gamerdates.profileapi.dal.jpa.ProfilesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = AuthAPIApplicationTestsBootstrapper.class)
@ActiveProfiles("utest")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Import(AuthAPITestConfiguration.class)
class ProfileAPIApplicationTests {

//	@Autowired
//	private ProfileService profileService;
//
//	@Autowired
//	private ProfilesRepository profilesRepository;

	@Test
	void testContextLoads() {
	}

//	@Test
//	void testTryLoginWithNonExistingPatientId() {
//		// Arrange
//		String username = "Naam";
//		String password = "Wachtwoord";
//
//		// Act
//		Optional<String> token = gamerService.loginByCredentials(username,password);
//
//		// Assert
//		Assert.isTrue(!token.isPresent(), "Token is present, even though there should be no patient with ID 1.");
//	}
//
//	@Test
//	void testLoginWithExistingPatientId(){
//		// Arrange
//		Gamer gamer = new Gamer("Naam", "Wachtwoord");
//		gamer = gamersRepository.save(gamer);
//		String username = gamer.getUsername();
//		String password = gamer.getPassword();
//
//		// Act
//		Optional<String> token = gamerService.loginByCredentials(username,password);
//
//		// Assert
//		Assert.isTrue(token.isPresent(), "Token is not present, even though there should be a patient with ID " + gamer.getId() + ".");
//	}

}
