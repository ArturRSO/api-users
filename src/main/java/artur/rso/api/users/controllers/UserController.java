package artur.rso.api.users.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import artur.rso.api.users.enums.HttpResponseEnum;
import artur.rso.api.users.models.APIResponseModel;
import artur.rso.api.users.parameters.UserParameter;
import artur.rso.api.users.presenters.UserPresenter;
import artur.rso.api.users.services.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final IUserService userService;

	@Autowired
	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody UserParameter param) {

		APIResponseModel response = this.userService.createUser(param);

		return response.getHttpCode().equals("200 OK") ? new ResponseEntity<>(response, HttpStatus.OK)
				: new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllUsers() {

		ArrayList<UserPresenter> users = this.userService.readAllUsers();

		return users != null ? new ResponseEntity<>(users, HttpStatus.OK)
				: new ResponseEntity<>(new APIResponseModel(2, HttpResponseEnum.getHttpCodeById(2),
						HttpResponseEnum.getMessageById(2), LocalDate.now(), LocalTime.now()), HttpStatus.OK);
	}

	@GetMapping("/active")
	public ResponseEntity<?> getActiveUsers() {

		ArrayList<UserPresenter> users = this.userService.readActiveUsers();

		return users != null ? new ResponseEntity<>(users, HttpStatus.OK)
				: new ResponseEntity<>(new APIResponseModel(2, HttpResponseEnum.getHttpCodeById(2),
						HttpResponseEnum.getMessageById(2), LocalDate.now(), LocalTime.now()), HttpStatus.OK);
	}

	@GetMapping("/inactive")
	public ResponseEntity<?> getInactiveUsers() {

		ArrayList<UserPresenter> users = this.userService.readInactiveUsers();

		return users != null ? new ResponseEntity<>(users, HttpStatus.OK)
				: new ResponseEntity<>(new APIResponseModel(2, HttpResponseEnum.getHttpCodeById(2),
						HttpResponseEnum.getMessageById(2), LocalDate.now(), LocalTime.now()), HttpStatus.OK);
	}

	@GetMapping("/{name}/{status}")
	public ResponseEntity<?> getUserByName(@PathVariable("name") String name, @PathVariable("status") int status) {

		ArrayList<UserPresenter> users = this.userService.readUserByName(name, status);

		return users != null ? new ResponseEntity<>(users, HttpStatus.OK)
				: new ResponseEntity<>(new APIResponseModel(2, HttpResponseEnum.getHttpCodeById(2),
						HttpResponseEnum.getMessageById(2), LocalDate.now(), LocalTime.now()), HttpStatus.OK);
	}

	@GetMapping("/{id}/{status}")
	public ResponseEntity<?> getUserById(@PathVariable("id") int id, @PathVariable("status") int status) {

		UserPresenter user = this.userService.readUserById(id, status);

		return user != null ? new ResponseEntity<>(user, HttpStatus.OK)
				: new ResponseEntity<>(new APIResponseModel(2, HttpResponseEnum.getHttpCodeById(2),
						HttpResponseEnum.getMessageById(2), LocalDate.now(), LocalTime.now()), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> updatedUser(@RequestBody UserParameter param) {

		APIResponseModel response = this.userService.updateUser(param);

		if (response.getHttpResponseId() == 4 || response.getHttpResponseId() == 2) {

			return new ResponseEntity<>(response, HttpStatus.OK);

		} else {

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {

		APIResponseModel response = this.userService.deleteUser(id);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/delete/many")
	public ResponseEntity<?> deleteManyUsers(@RequestBody ArrayList<Integer> ids) {

		APIResponseModel response = this.userService.deleteManyUsers(ids);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
