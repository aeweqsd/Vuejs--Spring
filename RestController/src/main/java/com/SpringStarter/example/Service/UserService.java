package com.SpringStarter.example.Service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.SpringStarter.example.Domain.User;

@Service
public interface UserService extends UserDetailsService{
	
	public User readUser(String username);
	
	public void createUser(User user);
	
	Collection<GrantedAuthority> getAuthorities(String username);

	public void createAuthority(User user);
	
}
