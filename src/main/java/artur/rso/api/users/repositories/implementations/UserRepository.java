package artur.rso.api.users.repositories.implementations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import artur.rso.api.users.entities.UserEntity;
import artur.rso.api.users.enums.HttpResponseEnum;
import artur.rso.api.users.models.APIResponseModel;
import artur.rso.api.users.models.UserModel;
import artur.rso.api.users.repositories.IUserRepository;

@Repository
@Transactional
public class UserRepository implements IUserRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public APIResponseModel create(Object object) {
		if (object instanceof UserModel) {
			UserEntity newUser = new UserEntity((UserModel) object);

			this.entityManager.persist(newUser);

			if (this.entityManager.contains(newUser)) {
				
				return new APIResponseModel(3, HttpResponseEnum.getHttpCodeById(3), HttpResponseEnum.getMessageById(3),
						LocalDate.now(), LocalTime.now());
			}

			return new APIResponseModel(1, HttpResponseEnum.getHttpCodeById(1), HttpResponseEnum.getMessageById(1),
					LocalDate.now(), LocalTime.now());
		}

		return new APIResponseModel(1, HttpResponseEnum.getHttpCodeById(1), HttpResponseEnum.getMessageById(1), LocalDate.now(),
				LocalTime.now());
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object> readAll() {
		String query = "FROM UserEntity u ORDER BY id";
		Query result = this.entityManager.createQuery(query);
		ArrayList<Object> users = new ArrayList<>();
		((ArrayList<UserEntity>) result.getResultList()).forEach((user) -> users.add(user.toModel()));

		if (users.isEmpty()) {
			return null;
		}

		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object> readActive() {
		String query = "FROM UserEntity u WHERE u.status = 1 ORDER BY id";
		Query result = this.entityManager.createQuery(query);
		ArrayList<Object> users = new ArrayList<>();
		((ArrayList<UserEntity>) result.getResultList()).forEach((user) -> users.add(user.toModel()));

		if (users.isEmpty()) {
			return null;
		}

		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object> readInactive() {
		String query = "FROM UserEntity u WHERE u.status = 0 ORDER BY id";
		Query result = this.entityManager.createQuery(query);
		ArrayList<Object> users = new ArrayList<>();
		((ArrayList<UserEntity>) result.getResultList()).forEach((user) -> users.add(user.toModel()));

		if (users.isEmpty()) {
			return null;
		}

		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object> readByName(String name, int status) {
		String query = "FROM UserEntity u WHERE u.status = :status AND u.name LIKE :n  ORDER BY id";
		Query result = this.entityManager.createQuery(query).setParameter("n", "%" + name + "%").setParameter("status",
				status);
		ArrayList<Object> users = new ArrayList<>();
		((ArrayList<UserEntity>) result.getResultList()).forEach((user) -> users.add(user.toModel()));

		if (users.isEmpty()) {
			return null;
		}

		return users;
	}

	@Override
	public Object readById(int id, int status) {
		try {
			String query = "FROM UserEntity u WHERE u.status = :status AND u.id = :id";
			Query result = this.entityManager.createQuery(query).setParameter("id", id).setParameter("status", status);
			UserEntity user = (UserEntity) result.getSingleResult();

			return user.toModel();

		} catch (NoResultException e) {

			return null;
		}
	}

	@Override
	public APIResponseModel update(Object object) {
		if (object instanceof UserModel) {
			UserModel u = (UserModel) object;
			UserEntity userEntity = this.entityManager.find(UserEntity.class, u.getId());

			if (userEntity != null) {
				UserEntity updatedUser = new UserEntity(u);
				updatedUser.setId(userEntity.getId());
				updatedUser.setStatus(userEntity.getStatus());

				if (this.entityManager.merge(updatedUser) != null) {

					return new APIResponseModel(4, HttpResponseEnum.getHttpCodeById(4), HttpResponseEnum.getMessageById(4),
							LocalDate.now(), LocalTime.now());

				} else {
					return new APIResponseModel(1, HttpResponseEnum.getHttpCodeById(1), HttpResponseEnum.getMessageById(1),
							LocalDate.now(), LocalTime.now());
				}
			}

			return new APIResponseModel(2, HttpResponseEnum.getHttpCodeById(2), HttpResponseEnum.getMessageById(2),
					LocalDate.now(), LocalTime.now());
		}

		return new APIResponseModel(1, HttpResponseEnum.getHttpCodeById(1), HttpResponseEnum.getMessageById(1), LocalDate.now(),
				LocalTime.now());
	}

	@Override
	public APIResponseModel delete(int id) {
		UserEntity user = this.entityManager.find(UserEntity.class, id);

		if (user != null) {
			if (user.getStatus() == 1) {
				user.setStatus(0);

				return new APIResponseModel(5, HttpResponseEnum.getHttpCodeById(5), HttpResponseEnum.getMessageById(5),
						LocalDate.now(), LocalTime.now());
			}

			return new APIResponseModel(7, HttpResponseEnum.getHttpCodeById(7), HttpResponseEnum.getMessageById(7),
					LocalDate.now(), LocalTime.now());
		}

		return new APIResponseModel(2, HttpResponseEnum.getHttpCodeById(2), HttpResponseEnum.getMessageById(2), LocalDate.now(),
				LocalTime.now());
	}

	@Override
	public APIResponseModel deleteMany(ArrayList<Integer> ids) {

		ArrayList<String> alreadyInactive = new ArrayList<>();
		ArrayList<Integer> notFound = new ArrayList<>();

		ids.forEach(id -> {
			UserEntity user = this.entityManager.find(UserEntity.class, id);
			if (user != null) {
				if (user.getStatus() == 1) {
					user.setStatus(0);
				} else {
					alreadyInactive.add(user.getName());
				}
			} else {
				notFound.add(id);
			}
		});
		
		if (alreadyInactive.isEmpty() && notFound.isEmpty()) {
			
			return new APIResponseModel(6, HttpResponseEnum.getHttpCodeById(6), HttpResponseEnum.getMessageById(6), LocalDate.now(),
					LocalTime.now());
			
		} else {
			
			StringBuilder s = new StringBuilder();
			
			if (!notFound.isEmpty()) {
				s.append("Ao todo, "+ notFound.size() + " não foram encontrados, portanto não foram inativados.\n");
				s.append("\n");
			}
			
			if (!alreadyInactive.isEmpty()){
				s.append("Os seguintes usuários já se encontram inativos:\n");
				s.append(alreadyInactive);
				s.append("\n");
			}
			
			APIResponseModel r = new APIResponseModel();
			
			r.setHttpResponseId(0);
			r.setHttpCode("200 OK");	
			r.setMessage(s.toString());
			r.setDate(LocalDate.now());
			r.setTime(LocalTime.now());
			
			return r;
		}
	}
}
