package com.returnordermanag.authorizationService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.returnordermanag.authorizationService.Repository.OrdersRepository;
import com.returnordermanag.authorizationService.exception.BadCredentialException;
import com.returnordermanag.authorizationService.model.AuthenticationRequest;
import com.returnordermanag.authorizationService.model.AuthenticationResponse;
import com.returnordermanag.authorizationService.model.ProcessRequest;
import com.returnordermanag.authorizationService.service.JwtUtil;
import com.returnordermanag.authorizationService.service.MyUserDetailsService;
import com.returnordermanag.authorizationService.service.ValidateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//@CrossOrigin(origins="http://localhost:4200,http://localhost:8761,http://localhost:8765")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private ValidateService validateService;

	@Autowired
	private OrdersRepository ordersRepository;
	
	
	
	// Rest endpoint
	
	/*
	 * @RequestMapping({ "/hello" }) public String firstPage() { return
	 * "Hello World"; }
	 */

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws BadCredentialException {

		log.info("Login Authenticating");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialException();
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		
		List<ProcessRequest> orders = ordersRepository.findByUserName(authenticationRequest.getUsername());
		log.warn(authenticationRequest.getUsername()+"This is "+orders);
		return ResponseEntity.ok(new AuthenticationResponse(jwt, true,orders));
	}
	
	@GetMapping("/testing")
	public String testing() {
		return new String("Test success go ahead");
	}

	@GetMapping("/validate")
	public AuthenticationResponse getValidity(@RequestHeader("Authorization") final String token) {

		/*
		 * validating token extraction from authorization header>> check the validity of
		 * token>> return an athenticationResponse Instance with two attributes String
		 * jwtToken , Boolean valid;
		 * 
		 * 
		 */

		log.info("Validate token");
		return validateService.validate(token);
	}

}
