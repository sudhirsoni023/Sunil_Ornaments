package com.ornaments.sunil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ornaments.sunil.Dao.UserRepository;
import com.ornaments.sunil.models.User;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
 
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = userRepository.findByUsername(username);
		if(u==null)
			throw new UsernameNotFoundException("User not found Sir");
		return u; 
	}

}
