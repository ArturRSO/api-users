package artur.rso.api.users.repositories;

import artur.rso.api.users.models.SessionModel;

public interface IAuthenticationRepository {
	
	public SessionModel authenticateUser(String email, String password);
	public String getRole(int id);
	public boolean createSession(SessionModel session);

}
