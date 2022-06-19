package com.jsmblog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.jsmblog.utility.Status;
import com.jsmblog.utility.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String userName;
	private String firstName;
	private String lastName;
	private String userBio;
	
	// Email
	private String primaryEmail;
	private String secondaryEmail;
	
	// contacts
	private String primaryContact;
	private String secondaryContact;
	
	// address
	private String billingAddressLine1;
	private String billingAddressLine2;
	private String billingCity;
	private String billingState;
	private String billingCountry;
	private String billingPincode;
	
	// e-commerce related
	private String shippingAddressLine1;
	private String shippingAddressLine2;
	private String shippingCity;
	private String shippingState;
	private String shippingCountry;
	private String shippingPincode;

	// security related
	private String password;
	private String token;
	private String securityQuestion;
	private String securityAnswer;
	private UserRole role;
	private Status status;
	private int power;
	
	@Builder.Default
	@OneToMany(mappedBy = "user")
	private List<BlogPost> blogPosts = new ArrayList<>();
	
}
