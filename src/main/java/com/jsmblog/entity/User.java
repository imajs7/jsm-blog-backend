package com.jsmblog.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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


	@Builder.Default
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<BlogPost> blogPosts = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="role_id")
			)
	private Set<Role> roles;
	
	public void addRole(Role role) {
		this.roles.add(role);
	}

}
