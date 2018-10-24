package artur.rso.api.users.models;

import java.time.LocalDateTime;

import artur.rso.api.users.parameters.UserParameter;
import artur.rso.api.users.presenters.UserPresenter;

public class UserModel {
	
	private int id;
	private String name;
	private String email;
	private String password;
	private String role;
	private String cpf;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private int status;
	
	public UserModel() {}

	public UserModel(int id, String name, String email, String password, String role, String cpf,
			LocalDateTime createdAt, LocalDateTime updatedAt, int status) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.cpf = cpf;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.status = status;
	}
	
	public UserModel (UserParameter user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.role = user.getRole();
		this.cpf = user.getCpf();
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.status = user.getStatus();
	}
	
	public UserPresenter toPresenter() {
		UserPresenter user = new UserPresenter();
		
		user.setName(this.name);
		user.setEmail(this.email);
		user.setCpf(this.cpf);
		user.setRole(this.role);
		user.setCreatedAt(this.createdAt);
		user.setUpdatedAt(this.updatedAt);
		
		return user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("UserModel: "+ this.id + "\n");
		s.append("Name: " + this.name + "\n");
		s.append("Email: " + this.email + "\n");
		s.append("Password: " + this.password + "\n");
		s.append("Role: " + this.role + "\n");
		s.append("CPF: " + this.cpf + "\n");
		s.append("Created at: " + this.createdAt + "\n");
		s.append("Updated at: " + this.updatedAt + "\n");
		s.append("Status: " +this.status + "\n");
		s.append("\n");
		
		return s.toString();
	}
}
