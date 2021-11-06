package com.alkemy.icoons.icons.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.icoons.icons.auth.dto.AuthenticationRequest;
import com.alkemy.icoons.icons.auth.dto.AuthenticationResponse;
import com.alkemy.icoons.icons.auth.dto.UserDTO;
import com.alkemy.icoons.icons.auth.service.JwtUtils;
import com.alkemy.icoons.icons.auth.service.UserDetailsCustomService;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
	private UserDetailsCustomService userCustomService;
	private AuthenticationManager authenticationManager;
	private JwtUtils jwtUtils;

	@Autowired
	public UserAuthController(UserDetailsCustomService userCustomService, AuthenticationManager authenticationManager,
			JwtUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.userCustomService = userCustomService;
		this.jwtUtils = jwtUtils;
	}

	@PostMapping("/singup")
	public ResponseEntity<AuthenticationResponse> singUp(@Valid @RequestBody UserDTO user) throws Exception {
		this.userCustomService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/singin")
	public ResponseEntity<AuthenticationResponse> singIn(@RequestBody AuthenticationRequest authRequest) throws Exception{
		UserDetails userDetails;
		
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
			);
			userDetails = (UserDetails) auth.getPrincipal();
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		final String jwt = jwtUtils.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
