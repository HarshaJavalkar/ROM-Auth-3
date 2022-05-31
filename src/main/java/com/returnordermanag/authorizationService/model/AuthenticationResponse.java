package com.returnordermanag.authorizationService.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

	private String jwtToken;
	private Boolean valid;
	private List<ProcessRequest> orders;

}
