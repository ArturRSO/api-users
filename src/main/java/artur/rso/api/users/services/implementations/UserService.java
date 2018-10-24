package artur.rso.api.users.services.implementations;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import artur.rso.api.users.models.APIResponseModel;
import artur.rso.api.users.models.UserModel;
import artur.rso.api.users.parameters.UserParameter;
import artur.rso.api.users.presenters.UserPresenter;
import artur.rso.api.users.repositories.IUserRepository;
import artur.rso.api.users.services.IUserService;
import artur.rso.api.users.tools.SHAEncrypter;

@Service
public class UserService implements IUserService{
	
	private final IUserRepository userRepository;
	private final SHAEncrypter hashEncoder;
	
	@Autowired
	public UserService(IUserRepository userRepository, SHAEncrypter shaEncrypter) {
		this.userRepository = userRepository;
		this.hashEncoder = shaEncrypter;
	}

	@Override
	public APIResponseModel createUser(UserParameter user) {
		UserModel newUser = new UserModel(user);
		
		String encodedPassword = this.hashEncoder.encrypt(newUser.getPassword());
		newUser.setPassword(encodedPassword);
		
		return this.userRepository.create(newUser);
	}

	@Override
	public ArrayList<UserPresenter> readAllUsers() {
		ArrayList<UserPresenter> users = new ArrayList<>();
		ArrayList<Object> result = this.userRepository.readAll();
		result.forEach((user) -> users.add(UserModel.class.cast(user).toPresenter()));
		
		return users != null ? users : null;
	}

	@Override
	public ArrayList<UserPresenter> readActiveUsers() {
		ArrayList<UserPresenter> users = new ArrayList<>();
		ArrayList<Object> result = this.userRepository.readActive();
		result.forEach((user) -> users.add(UserModel.class.cast(user).toPresenter()));
		
		return users != null ? users : null;
	}

	@Override
	public ArrayList<UserPresenter> readInactiveUsers() {
		ArrayList<UserPresenter> users = new ArrayList<>();
		ArrayList<Object> result = this.userRepository.readInactive();
		result.forEach((user) -> users.add(UserModel.class.cast(user).toPresenter()));
		
		return users != null ? users : null;
	}

	@Override
	public ArrayList<UserPresenter> readUserByName(String name, int status) {
		ArrayList<UserPresenter> users = new ArrayList<>();
		ArrayList<Object> result = this.userRepository.readByName(name, status);
		result.forEach((user) -> users.add(UserModel.class.cast(user).toPresenter()));
		
		return users != null ? users : null;
	}

	@Override
	public UserPresenter readUserById(int id, int status) {
		Object user = this.userRepository.readById(id, status);
		
		return user != null ? UserModel.class.cast(user).toPresenter() : null;
	}

	@Override
	public APIResponseModel updateUser(Object user) {
		UserModel updatedUser = new UserModel(UserParameter.class.cast(user));
		
		String encodedPassword = this.hashEncoder.encrypt(updatedUser.getPassword());
		updatedUser.setPassword(encodedPassword);
		
		return this.userRepository.update(UserModel.class.cast(updatedUser));
	}

	@Override
	public APIResponseModel deleteUser(int id) {
		
		return this.userRepository.delete(id);
	}

	@Override
	public APIResponseModel deleteManyUsers(ArrayList<Integer> ids) {

		return this.userRepository.deleteMany(ids);
	}

}
