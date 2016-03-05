package mse.user.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mse.user.model.Article;

@Stateless
public class ArticleService {

	@PersistenceContext
	private EntityManager entityManager;

	public Article loadArticle(Long id) {
		return entityManager.find(Article.class, id);
	}

	public void save(Article article) {
		entityManager.merge(article);
	}

	public void delete(Article article) {
		Query query = entityManager.createNamedQuery("Article.delete");
		query.setParameter("id", article.getId());
		query.executeUpdate();
	}

	public boolean checkForExistingTitle(String title) {
		Query query = entityManager.createNamedQuery("Article.exist");
		query.setParameter("title", title);
		return !query.getResultList().isEmpty();
	}
}
