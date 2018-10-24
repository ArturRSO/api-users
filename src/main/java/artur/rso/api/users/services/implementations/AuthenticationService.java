package artur.rso.api.users.services.implementations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import artur.rso.api.users.models.SessionModel;
import artur.rso.api.users.presenters.SessionPresenter;
import artur.rso.api.users.repositories.IAuthenticationRepository;
import artur.rso.api.users.services.IAuthenticationService;
import artur.rso.api.users.tools.SHAEncrypter;

@Service
public class AuthenticationService implements IAuthenticationService {

	private final IAuthenticationRepository authenticationRepository;
	private final SHAEncrypter encoder;

	@Autowired
	public AuthenticationService(IAuthenticationRepository authenticationRepository, SHAEncrypter encoder) {
		this.authenticationRepository = authenticationRepository;
		this.encoder = encoder;
	}

	@Override
	public SessionModel login(String email, String password) {

		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();

		String encodedPassowrd = this.encoder.encrypt(password);

		SessionModel session = this.authenticationRepository.authenticateUser(email, encodedPassowrd);

		if (session != null) {
			session.setSessionId(sessionId);

			if (this.registerSession(session)) {

				RequestContextHolder.currentRequestAttributes().setAttribute("session", session,
						RequestAttributes.SCOPE_SESSION);

				System.out.println("CURRENT SESSION: ");
				System.out.println(session.toString());

				return session;
			}
		}
		
		return null;
	}

	@Override
	public boolean logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication,
			String token) {

		if (token != null) {
			new SecurityContextLogoutHandler().logout(request, null, null);

			return true;

		} else {

			return false;
		}
	}

	@Override
	public String getRole(int id) {

		return this.authenticationRepository.getRole(id);
	}

	@Override
	public SessionPresenter getCurrentSession() {

		SessionModel session = (SessionModel) RequestContextHolder.currentRequestAttributes().getAttribute("session",
				RequestAttributes.SCOPE_SESSION);

		return session.toPresenter();
	}

	@Override
	public boolean registerSession(SessionModel session) {

		return this.authenticationRepository.createSession(session);
	}
}
