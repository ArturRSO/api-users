package artur.rso.api.users.repositories;

import java.util.ArrayList;

import artur.rso.api.users.models.APIResponseModel;

public interface IBaseRepository {
	
	public APIResponseModel create(Object object);
	public ArrayList<Object> readAll();
	public ArrayList<Object> readActive();
	public ArrayList<Object> readInactive();
	public ArrayList<Object> readByName(String name, int status);
	public Object readById(int id, int status);
	public APIResponseModel update(Object object);
	public APIResponseModel delete(int id);
	public APIResponseModel deleteMany(ArrayList<Integer> ids);	
}
