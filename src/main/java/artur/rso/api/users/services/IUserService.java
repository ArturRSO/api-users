package artur.rso.api.users.services;

import java.util.ArrayList;

import artur.rso.api.users.models.APIResponseModel;
import artur.rso.api.users.parameters.UserParameter;
import artur.rso.api.users.presenters.UserPresenter;

public interface IUserService {

	public APIResponseModel createUser(UserParameter user);
	public ArrayList<UserPresenter> readAllUsers();
	public ArrayList<UserPresenter> readActiveUsers();
	public ArrayList<UserPresenter> readInactiveUsers();
	public ArrayList<UserPresenter> readUserByName(String name, int status);
	public UserPresenter readUserById(int id, int status);
	public APIResponseModel updateUser(Object object);
	public APIResponseModel deleteUser(int id);
	public APIResponseModel deleteManyUsers(ArrayList<Integer> ids);
}
