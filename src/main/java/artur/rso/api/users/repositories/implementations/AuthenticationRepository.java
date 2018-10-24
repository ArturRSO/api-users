package artur.rso.api.users.repositories.implementations;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import artur.rso.api.users.entities.SessionEntity;
import artur.rso.api.users.entities.UserEntity;
import artur.rso.api.users.models.SessionModel;
import artur.rso.api.users.repositories.IAuthenticationRepository;
import artur.rso.api.users.tools.TokenGenerator;

@Repository
@Transactional
public class AuthenticationRepository implements IAuthenticationRepository {

	@PersistenceContext
	EntityManager entityManager;

	private final TokenGenerator tokenGenerator;

	@Autowired
	public AuthenticationRepository(TokenGenerator tokenGenerator) {
		this.tokenGenerator = tokenGenerator;
	}

	@Override
	public SessionModel authenticateUser(String email, String password) {
		try {

			String query = "FROM UserEntity u WHERE email = :email AND password = :password AND status = 1";
			Query result = this.entityManager.createQuery(query).setParameter("email", email).setParameter("password",
					password);

			UserEntity user = (UserEntity) result.getSingleResult();

			if (user == null) {
				return null;
			}

			String token = this.tokenGenerator.generateToken();

			SessionModel session = new SessionModel(null, user.getId(), token, LocalDateTime.now(), null);

			return session;

		} catch (Exception e) {
			System.out.println("Authentication failed: " + e.getMessage());
			return null;
		}
	}

	@Override
	public String getRole(int id) {

		String query = "SELECT role FROM tb_user WHERE id = :id AND status = 1";
		Query result = this.entityManager.createNativeQuery(query).setParameter("id", id);

		String role = (String) result.getSingleResult();

		if (role == null) {
			return null;
		}

		return role;
	}

	@Override
	public boolean createSession(SessionModel session) {

		SessionEntity newSession = new SessionEntity(session);

		this.entityManager.persist(newSession);

		if (this.entityManager.contains(newSession)) {

			return true;
		}

		return false;
	}
}
