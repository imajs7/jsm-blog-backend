package com.jsmblog.payload;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.jsmblog.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min = 5, message = "length of username must be 6 characters or more")
	private String userName;
	
	@NotEmpty
	@Size(min = 3, message = "length of first name must be 3 characters or more")
	private String firstName;
	
	private String lastName;
	
	private String userBio;
	
	// Email
	@NotEmpty
	@Email(message = "email id format is invalid")
	private String primaryEmail;
	
	@Email(message = "email id format is invalid")
	private String secondaryEmail;
	
	// contacts
	@NotEmpty
	@Pattern(regexp = "^\\d{10}$", message =  "contact number must contain digits only")
	private String primaryContact;

	@Pattern(regexp = "^\\d{10}$", message =  "contact number must contain digits only")
	private String secondaryContact;
	
	// address
	private String billingAddressLine1;
	private String billingAddressLine2;
	
	@NotEmpty
	@Size(min = 3, message = "length of city name must be 3 characters or more")
	private String billingCity;
	
	@NotEmpty
	@Size(min = 3, message = "length of state name must be 3 characters or more")
	private String billingState;
	
	@NotEmpty
	@Size(min = 3, message = "length of country name must be 3 characters or more")
	private String billingCountry;
	
	@NotEmpty
	@Pattern(regexp = "^\\d{6}$", message =  "pincode must contain six digits only")
	private String billingPincode;
	
	// e-commerce related
	private String shippingAddressLine1;
	private String shippingAddressLine2;
	
	@Size(min = 3, message = "length of city name must be 3 characters or more")
	private String shippingCity;
	
	@Size(min = 3, message = "length of state name must be 3 characters or more")
	private String shippingState;
	
	@Size(min = 3, message = "length of country name must be 3 characters or more")
	private String shippingCountry;
	
	@Pattern(regexp = "^\\d{6}$", message =  "pincode must contain six digits only")
	private String shippingPincode;
	
	// security related
	private Set<Role> roles;

}
