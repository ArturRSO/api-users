package artur.rso.api.users.controllers;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import artur.rso.api.users.enums.HttpResponseEnum;
import artur.rso.api.users.models.APIResponseModel;
import artur.rso.api.users.presenters.SessionPresenter;
import artur.rso.api.users.services.IAuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	private final IAuthenticationService authenticationService;

	@Autowired
	public AuthenticationController(IAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login() {

		SessionPresenter session = this.authenticationService.getCurrentSession();

		if (session != null) {

			return new ResponseEntity<>(session, HttpStatus.OK);

		} else {

			APIResponseModel apiResponse = new APIResponseModel(1, HttpResponseEnum.getHttpCodeById(1),
					HttpResponseEnum.getMessageById(1), LocalDate.now(), LocalTime.now());

			return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {

		String token = (String) request.getSession().getAttribute("authenticationToken");

		if (this.authenticationService.logout(request, null, null, token)) {

			APIResponseModel apiResponse = new APIResponseModel(9, HttpResponseEnum.getHttpCodeById(9),
					HttpResponseEnum.getMessageById(9), LocalDate.now(), LocalTime.now());

			return new ResponseEntity<>(apiResponse, HttpStatus.OK);

		} else {

			APIResponseModel apiResponse = new APIResponseModel(1, HttpResponseEnum.getHttpCodeById(1),
					HttpResponseEnum.getMessageById(1), LocalDate.now(), LocalTime.now());

			return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/recover_auth")
	public ResponseEntity<?> recoverPassword(String email) {
		
		return null;
	}
}
