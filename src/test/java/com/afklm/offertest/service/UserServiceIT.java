package com.afklm.offertest.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.afklm.offertest.service.UserService;
import com.afklm.offertest.entity.User;
import com.afklm.offertest.service.dto.UserDTO;
import com.afklm.offertest.service.errors.NotAdultUserException;

import javax.validation.ConstraintViolationException;

public class UserServiceIT {
	
    private static final String DEFAULT_LOGIN = "johndoe";

    private static final String DEFAULT_EMAIL = "johndoe@localhost";

    private static final String DEFAULT_NAME = "smith";

    private static final LocalDate DEFAULT_BIRTHDATE_ADULT = LocalDate.of(2004, 01, 05);
    
    private static final String DEFAULT_RESIDENCE_VALID = "FRA";

    private static final String DEFAULT_RESIDENCE_INVALID = "DEU";
    
    private static final String PHONE_NUMBER_VALID = "+335.11.22.33.44";
    
    private static final String PHONE_NUMBER_INVALID = "5Z.11.22.33.44";
    
    @Autowired
    private UserService userService;
    
    private User user;
    
    @BeforeEach
    public void init() {
        user = new User();
        user.setName(DEFAULT_NAME);
        user.setBirthDate(DEFAULT_BIRTHDATE_ADULT);
        user.setResidCountryCode(DEFAULT_RESIDENCE_VALID);
    }
	
    /**
     * Attempting to create a non adult user must throw a specific exception.
     */
    @Test
    @Transactional
    void assertThatCreateUserWhosIsNotAdultThrowsException() {
        Instant seventeenYearsAgo = Instant.now().minus(17L, ChronoUnit.YEARS);
        LocalDate seventeenYearsBeforeDate = seventeenYearsAgo.atZone(ZoneId.systemDefault()).toLocalDate();
        
        user.setBirthDate(seventeenYearsBeforeDate);
        
        UserDTO userDTO = new UserDTO(user);
        assertThrows( NotAdultUserException.class, () -> {
        	userService.createUser(userDTO);
        });
    }
    
    /**
     * Attempting to create a non French user must throw a specific exception.
     */
    @Test
    @Transactional
    void assertThatCreateUserWhosIsNotFrenchThrowsException() {
    	user.setResidCountryCode(DEFAULT_RESIDENCE_INVALID);
    	
        UserDTO nonFrenchUserDTO = new UserDTO(user);
        assertThrows( NotAdultUserException.class, () -> {
        	userService.createUser(nonFrenchUserDTO);
        });
    }

    
    /**
     * Attempting to create user with must throw a specific exception.
     */
    @Test
    @Transactional
    void assertThatCreateUserWithInvalidPhoneThrowsException() {
    	
    	assertThrows( ConstraintViolationException.class, () -> {
    		user.setPhoneNumber(PHONE_NUMBER_INVALID);
        });    	
//        UserDTO invalidPhoneUserDTO = new UserDTO(user);
//        assertThrows( NotAdultUserException.class, () -> {
//        	userService.createUser(invalidPhoneUserDTO);
//        });
    }
}
