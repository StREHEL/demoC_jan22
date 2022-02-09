package com.afklm.offertest.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "user")
@Entity
@Getter
@Setter
@ToString
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
    @Size(max = 50)
    @Column(name = "name", length = 50)
	private String name;
	
    @Column(name = "birth_date")
    private LocalDate birthDate = null;

    /**
     * Country of residence ISO code (2 ou 3 characters)
     */
    @NotNull(message = "ISO code for country residence is mandatory.")
    @Column(name = "resid_country_iso_code")
    @Pattern(regexp = "^[A-Z]{2,3}$", message = "The value of 'countryISO_Code' must be made of 2 or 3 uppercase alphabetic letters.")
    private String residCountryCode = null;
    
    
    @Column(name = "phone_number")
    @Pattern(regexp = "^(\\+33[1-9]\\.)?(\\d{1,2}\\.){3,4}(\\d{2})$", message = "The value of 'phone number' must be made with digits.")
    private String phoneNumber = null;
    
    /**
     * Allowable values are : 
     * F <-> Female
     * M <-> Male
     * U <-> No gender
     */
    @Column(name = "gender", length = 1)
    //TODO : Create a @Gender annotation in order to validate gender code inputs.
    private Character gender = null;
    
}
