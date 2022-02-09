package com.afklm.offertest.service.dto;

import com.afklm.offertest.entity.User;
import java.time.LocalDate;

import javax.validation.constraints.Size;

/**
 * A DTO representing a user, with only the public attributes.
 */
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	
	public java.lang.Character getGender() {
		return gender;
	}

	public void setGender(java.lang.Character gender) {
		this.gender = gender;
	}

	public String getResidCountryCode() {
		return residCountryCode;
	}

	public void setResidCountryCode(String residCountryCode) {
		this.residCountryCode = residCountryCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	// prettier-ignore
    @Override
    public String toString() {
        return "UserDTO{" +
            "id='" + id + '\'' +
            ", login='" + name + '\'' +
            ", birthDate='" + birthDate + '\'' +
            ", coutry_ISO='" + residCountryCode + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", gender='" + gender + '\'' +
            "}";
    }
}
