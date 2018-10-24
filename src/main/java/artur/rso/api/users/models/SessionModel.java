package artur.rso.api.users.models;

import java.time.LocalDateTime;

import artur.rso.api.users.presenters.SessionPresenter;

public class SessionModel {
	
	private int id;
	private String sessionId;
	private int userId;
	private String autheticationToken;
	private LocalDateTime loggedAt;
	private LocalDateTime logoutAt;
	
	public SessionModel() {}

	public SessionModel(String sessionId, int userId, String autheticationToken, LocalDateTime loggedAt, LocalDateTime logoutAt) {
		this.sessionId = sessionId;
		this.userId = userId;
		this.autheticationToken = autheticationToken;
		this.loggedAt = loggedAt;
		this.logoutAt = logoutAt;
	}
	
	public SessionPresenter toPresenter() {
		
		SessionPresenter session = new SessionPresenter();
		session.setAutheticationToken(this.autheticationToken);
		session.setLoggedAt(this.loggedAt);
		
		return session;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public LocalDateTime getLogoutAt() {
		return logoutAt;
	}

	public void setLogoutAt(LocalDateTime logoutAt) {
		this.logoutAt = logoutAt;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Session: " + this.id + "\n");
		s.append("Session ID: " + this.sessionId + "\n");
		s.append("User ID: " + this.userId + "\n");
		s.append("Current auth token: " + this.autheticationToken + "\n");
		s.append("Logged at: " + this.loggedAt + "\n");
		s.append("Logout at: " + this.logoutAt + "\n");
		s.append("\n");
		
		return s.toString();
	}
}
