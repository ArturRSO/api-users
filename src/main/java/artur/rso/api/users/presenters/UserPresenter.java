package artur.rso.api.users.presenters;

import java.time.LocalDateTime;

public class UserPresenter {

	private String name;
	private String email;
	private String role;
	private String cpf;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public UserPresenter() {}

	public UserPresenter(String name, String email, String role, String cpf, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.name = name;
		this.email = email;
		this.role = role;
		this.cpf = cpf;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("UserPresenter:\n");
		s.append("Name: " + this.name + "\n");
		s.append("Email: " + this.email + "\n");
		s.append("Role: " + this.role + "\n");
		s.append("CPF: " + this.cpf + "\n");
		s.append("Created at: " + this.createdAt + "\n");
		s.append("Updated at: " + this.updatedAt + "\n");
		s.append("\n");
		
		return s.toString();
	}
}
