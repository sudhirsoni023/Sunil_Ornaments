package com.ornaments.sunil.models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails{
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
 private String username;
 private String password;
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	return Collections.singleton(new SimpleGrantedAuthority("USER"));
}
@Override
public String getPassword() {
	
	return this.password;
}
@Override
public String getUsername() {
	
	return this.username;
}
 public void setPassword(String password)
 {
	 this.password = password;
 }
}
