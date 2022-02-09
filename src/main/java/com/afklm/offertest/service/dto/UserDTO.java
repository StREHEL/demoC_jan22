package com.afklm.offertest.service.dto;

import com.afklm.offertest.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

import javax.validation.constraints.Size;

/**
 * A DTO representing a user, with only the public attributes.
 */
@Getter
@Setter
@ToString
public class UserDTO {

    private Long id;

    @Size(max = 50)
    private String name;
    
    private java.time.LocalDate birthDate;
    
    private java.lang.Character gender;
    
    private String residCountryCode;
    
    private String phoneNumber;

    public UserDTO() {
        // Empty constructor needed for Jackson.
    }

    public UserDTO(User user) {
        this.id = user.getId();
        if ( user.getBirthDate() == null ) {
        	this.birthDate = null;
        } else {        	
        	this.birthDate = user.getBirthDate();
        }
        
        this.residCountryCode = user.getResidCountryCode();
        this.phoneNumber = user.getPhoneNumber();
        this.gender = user.getGender();
    }

}
