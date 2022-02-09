package com.afklm.offertest.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afklm.offertest.repository.UserRepositoryI;
import com.afklm.offertest.service.dto.UserDTO;
import com.afklm.offertest.entity.User;
import com.afklm.offertest.config.Constants;

import com.afklm.offertest.service.errors.NotFrenchUserException;
import com.afklm.offertest.service.errors.NotAdultUserException;


@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepositoryI userRepository;
    
    public UserService(UserRepositoryI userRepository) {
    	this.userRepository = userRepository;
    }
    
    public User createUser(UserDTO userDTO) {
    	Instant eightteenYearsAgo = ZonedDateTime.now().minusYears(Constants.ADULT_AGE_YEARS).toInstant();
        LocalDate eightteenYearsBeforeDate = eightteenYearsAgo.atZone(ZoneId.systemDefault()).toLocalDate();
        
        User user = new User();
        user.setName(userDTO.getName());
        
        user.setBirthDate(userDTO.getBirthDate());
        if ( userDTO.getResidCountryCode() == null ) {
        	user.setResidCountryCode(Constants.DEFAULT_COUNTRY_ISO);
        	log.warn("IN UserService / CreateUser method, empty country code provided replaced by default one (\""+Constants.DEFAULT_COUNTRY_ISO+"\").");
        } else {
        	user.setResidCountryCode(userDTO.getResidCountryCode());
        }
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setGender(userDTO.getGender());

        // Business rules : user must be an adult French person.
        if ( userDTO.getBirthDate()==null || userDTO.getBirthDate().isAfter(eightteenYearsBeforeDate) ) { //Checks that user is adult.
        	log.error("In UserService / Attempt to create nimor user (not allowed)!");
        	throw new NotAdultUserException();
        } else if ( userDTO.getResidCountryCode()==null || Constants.FRANCE_ISO_CODES.contains(userDTO.getResidCountryCode())==false ) { //Checks that user's country of residence is France.
        	log.error("In UserService / Attempt to create non French user (not allowed)!");
        	throw new NotFrenchUserException();
        }
        
        userRepository.save(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }
    

    public Optional<User> getSingleUser(Long id) {
    	Optional<User> user = userRepository.findById(id);
    	
    	if (user.isEmpty()) {
    		log.error("There is no user for the id: {}", id);
    	} 
    	return user;
    }
    
}
