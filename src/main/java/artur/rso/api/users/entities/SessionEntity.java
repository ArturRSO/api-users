package artur.rso.api.users.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import artur.rso.api.users.models.SessionModel;

@Entity(name = "SessionEntity")
@Table(name = "tb_session")
public class SessionEntity {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "session_id")
	private String sessionId;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "authentication_token")
	private String authenticationToken;
	
	@Column(name = "logged_at")
	private LocalDateTime loggedAt;
	
	@Column(name = "logout_at")
	private LocalDateTime logoutAt;

	public SessionEntity() {}

	public SessionEntity(int id, String sessionId, int userId, String authenticationToken, LocalDateTime loggedAt, LocalDateTime logoutAt) {
		this.id = id;
		this.sessionId = sessionId;
		this.userId = userId;
		this.authenticationToken = authenticationToken;
		this.loggedAt = loggedAt;
		this.logoutAt = logoutAt;
	}
	
	public SessionEntity(SessionModel session) {
		this.id = session.getId();
		this.sessionId = session.getSessionId();
		this.userId = session.getUserId();
		this.authenticationToken = session.getAutheticationToken();
		this.loggedAt = session.getLoggedAt();
		this.logoutAt = session.getLogoutAt();
	}
	
	public SessionModel toPresenter() {
		SessionModel session = new SessionModel();
		session.setId(this.id);
		session.setSessionId(this.sessionId);
		session.setUserId(this.userId);
		session.setAutheticationToken(this.authenticationToken);
		session.setLoggedAt(this.loggedAt);
		session.setLogoutAt(this.logoutAt);
		
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

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
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
		s.append("Session:\n");
		s.append("ID: " + this.id + "\n");
		s.append("Session ID: " + this.sessionId + "\n");
		s.append("User ID: " + this.userId + "\n");
		s.append("Auth token: " + this.authenticationToken + "\n");
		s.append("Logged at: " + this.loggedAt + "\n");
		s.append("Logout at: " + this.logoutAt + "\n");
		s.append("\n");
		
		return s.toString();
	}	
}
