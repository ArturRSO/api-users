package artur.rso.api.users.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import artur.rso.api.users.models.SessionModel;
import artur.rso.api.users.presenters.SessionPresenter;

public interface IAuthenticationService {
	
	public SessionModel login(String email, String password);
	public boolean logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication, String cookie);
	public String getRole(int id);
	public SessionPresenter getCurrentSession();
	public boolean registerSession(SessionModel session);
}
