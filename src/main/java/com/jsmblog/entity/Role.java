package com.jsmblog.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="roles")
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
public class Role {
	
	@Id
	private int id;
	
	private String roleName;
	
	@JsonBackReference
	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

}
