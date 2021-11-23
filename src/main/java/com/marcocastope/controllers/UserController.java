package com.marcocastope.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcocastope.jwt.JwtProvider;
import com.marcocastope.model.User;
import com.marcocastope.model.request.AuthenticationRequest;
import com.marcocastope.model.request.RegistrationRequest;
import com.marcocastope.model.response.AuthenticationResponse;
import com.marcocastope.model.response.GenericResponse;
import com.marcocastope.services.UserService;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtProvider jwtProvider;

	@PostMapping("api/auth")
	public AuthenticationResponse auth(@RequestBody AuthenticationRequest request) {
		User user = userService.findByLoginAndPassword(request.getUsername(), request.getPassword());
		String token = jwtProvider.generateToken(user.getUsername());
		return new AuthenticationResponse(token, user);
	}

	@RequestMapping(value = "api/register", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest) {
		ResponseEntity<?> resp = null;
		try {
			User user = new User();
			user.setFirstname(registrationRequest.getFirstname());
			user.setLastname(registrationRequest.getLastname());
			user.setDni(registrationRequest.getDni());
			user.setUsername(registrationRequest.getUsername());
			user.setPassword(registrationRequest.getPassword());
			userService.saveUser(user);
			resp = new ResponseEntity<GenericResponse>(new GenericResponse("Usuario registrado con éxito."), HttpStatus.CREATED);
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@GetMapping("api/user-profile/{username}")
	public ResponseEntity<?> getUserProfile(@PathVariable("username") String username) {

		ResponseEntity<?> resp = null;
		try {
			if (username == null) {
				resp = new ResponseEntity<String>("invalid id", HttpStatus.BAD_REQUEST);
			}

			User user = userService.findByUsername(username);
			if (user == null) {
				resp = new ResponseEntity<String>("No se encotró el recurso", HttpStatus.NOT_FOUND);
			}
			resp = new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<String>("No data available", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@GetMapping("/admin/get")
	public String admin() {
		return "ADMIN";
	}

	@GetMapping("/user/get")
	public String user() {
		return "ADMIN";
	}

}
