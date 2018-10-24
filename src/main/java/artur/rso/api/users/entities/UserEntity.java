package artur.rso.api.users.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import artur.rso.api.users.models.UserModel;

@Entity(name = "UserEntity")
@Table(name = "tb_user")
public class UserEntity {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "status")
	private int status;
	
	public UserEntity() {}

	public UserEntity(int id, String name, String email, String password, String role, String cpf, LocalDateTime createdAt, LocalDateTime updatedAt, int status) {
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
	
	public UserEntity(UserModel user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.role = user.getRole();
		this.cpf = user.getCpf();
		this.createdAt = user.getCreatedAt();
		this.updatedAt = user.getUpdatedAt();
		this.status = user.getStatus();
	}
	
	public UserModel toModel() {
		UserModel user = new UserModel();
		user.setId(this.id);
		user.setName(this.name);
		user.setEmail(this.email);
		user.setPassword(this.password);
		user.setRole(this.role);
		user.setCpf(this.cpf);
		user.setCreatedAt(this.createdAt);
		user.setUpdatedAt(this.updatedAt);
		user.setStatus(this.status);
		
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
		s.append("UserEntity: "+ this.id + "\n");
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
