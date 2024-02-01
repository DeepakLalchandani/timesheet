package com.techsopi.entity;

import com.techsopi.model.ClientDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;


 @Entity
 @Data
 //@Builder
 @Table(name = "client")
 //@AllArgsConstructor
 //@NoArgsConstructor
 //@Getter
 //@Setter
  
  
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "currency")
    private String currency;

    @Column(name = "billing_method")
    private String billingMethod;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "country")
    private String country;

    @Column(name = "industry")
    private String industry;

    @Column(name = "company_size")
    private Integer companySize;

    @Column(name = "description")
    private String description;

	
	
}