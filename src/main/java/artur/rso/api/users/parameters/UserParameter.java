package artur.rso.api.users.parameters;

public class UserParameter {
	
	private String name;
	private String email;
	private String password;
	private String role;
	private String cpf;
	private int status;
	
	public UserParameter() {}

	public UserParameter(String name, String email, String password, String role, String cpf, int status) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.cpf = cpf;
		this.status = status;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
