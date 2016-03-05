package mse.user.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import mse.user.model.User;

@Stateless
public class UserService {

	private static final Integer PAGE_SIZE = 5;

	@PersistenceContext
	private EntityManager entityManager;

	public void save(User user) {
		entityManager.merge(user);
	}

	public User loadUser(Long id) {
		return entityManager.find(User.class, id);
	}

	public void delete(User user) {
		Query query = entityManager.createNamedQuery("User.delete");
		query.setParameter("id", user.getId());
		query.executeUpdate();
	}

	public List<User> getUsers() {
		return entityManager.createNamedQuery("User.list", User.class)
				.getResultList();
	}

	public List<User> getUsers(Integer page) {
		TypedQuery<User> createQuery = entityManager.createNamedQuery(
				"User.list", User.class);
		Integer first = (page - 1) * PAGE_SIZE;

		createQuery.setFirstResult(first);
		createQuery.setMaxResults(PAGE_SIZE);

		return createQuery.getResultList();
	}

	public Long getPageCount() {
		Long count = entityManager.createNamedQuery("User.count", Long.class)
				.getSingleResult();
		Long pages = count / PAGE_SIZE;

		if (count % PAGE_SIZE > 0) {
			pages++;
		}

		return pages;
	}

	public boolean checkForExistingUser(String username) {
		Query query = entityManager.createNamedQuery("User.exist");
		query.setParameter("username", username);
		return !query.getResultList().isEmpty();
	}

	public boolean login(String username, String password) {
		Query query = entityManager.createNamedQuery("User.login");
		query.setParameter("username", username);
		query.setParameter("password", password);
		return !query.getResultList().isEmpty();
	}

}
