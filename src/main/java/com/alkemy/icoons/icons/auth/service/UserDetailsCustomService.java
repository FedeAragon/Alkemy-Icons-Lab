package com.alkemy.icoons.icons.auth.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alkemy.icoons.icons.auth.dto.UserDTO;
import com.alkemy.icoons.icons.auth.entity.UserEntity;
import com.alkemy.icoons.icons.auth.repository.UserRepository;

@Service
public class UserDetailsCustomService implements UserDetailsService{
	
	@Autowired
	private UserRepository UserRepository;
	
	/*
	@Autowired
	private EmailService emailService;
	*/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = UserRepository.findByUsername(username);
		if (userEntity == null){
			throw new UsernameNotFoundException("Username or password not found");
		}
		return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
	}
	
	public boolean save(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(userDTO.getUsername());
		userEntity.setPassword(userDTO.getPassword());
		userEntity = this.UserRepository.save(userEntity);
		/*if(userEntity != null) {
			emailService.sendWelcomeEmailTo(userEntity.getUsername);
		}*/
		return userEntity != null;
	}

}
