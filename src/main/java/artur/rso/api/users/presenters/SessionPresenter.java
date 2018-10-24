package artur.rso.api.users.presenters;

import java.time.LocalDateTime;

public class SessionPresenter {
	
	private String autheticationToken;
	private LocalDateTime loggedAt;
	
	public SessionPresenter() {}

	public SessionPresenter(String autheticationToken, LocalDateTime loggedAt) {
		this.autheticationToken = autheticationToken;
		this.loggedAt = loggedAt;
	}

	public String getAutheticationToken() {
		return autheticationToken;
	}

	public void setAutheticationToken(String autheticationToken) {
		this.autheticationToken = autheticationToken;
	}

	public LocalDateTime getLoggedAt() {
		return loggedAt;
	}

	public void setLoggedAt(LocalDateTime loggedAt) {
		this.loggedAt = loggedAt;
	}

	@Override
	public String toString() {
		
		StringBuilder s = new StringBuilder();
		s.append("Current session:\n");
		s.append("Current auth token: " + this.autheticationToken + "\n");
		s.append("Logged at: " + this.loggedAt + "\n");
		s.append("\n");
		
		return s.toString();
	}
}
