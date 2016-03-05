package mse.user.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mse.user.model.Article;
import mse.user.service.ArticleService;

@Named("articleController")
@ViewScoped
public class ArticleController implements Serializable {

	private static final long serialVersionUID = -7223727144990610368L;

	private Article article;
	private String currentArticleTitle;
	private Long id;

	@Inject
	private ArticleService articleService;

	public void onPageLoad() {
		if (id != null) {
			article = articleService.loadArticle(id);
			// currentArticleTitle = article.getTitle();
		} else {
			article = new Article();
		}
	}

	public String save() throws IOException {

		articleService.save(article);
		return "article?faces-redirect=true";
	}

	public void validateTitle(FacesContext context, UIComponent component,
			Object object) {
		String title = (String) object;

		if (!title.equals(currentArticleTitle)) {
			if (articleService.checkForExistingTitle(title)) {
				throw new ValidatorException(getMessage(context,
						"user_exists_error"));
			}
		}
	}

	private FacesMessage getMessage(FacesContext facesContext, String key) {
		// get client locale
		Locale userLocale = facesContext.getViewRoot().getLocale();

		ResourceBundle bundle = ResourceBundle.getBundle(
				"mse.user.messages.messages", userLocale);
		String errorText = bundle.getString(key);

		FacesMessage message = new FacesMessage(errorText);
		return message;
	}

	public String getCurrentArticleTitle() {
		return currentArticleTitle;
	}

	public void setCurrentArticleTitle(String currentArticleTitle) {
		this.currentArticleTitle = currentArticleTitle;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}
