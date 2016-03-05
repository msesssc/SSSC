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
import javax.servlet.http.Part;

import mse.user.model.User;
import mse.user.service.UserService;

@Named("userController")
@ViewScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = -8488837328418740496L;

	private User user;

	private String passwordConfirm;

	private Long id;

	@Inject
	private UserService userService;

	private String currentUsername;

	private Part image;

	public void onPageLoad() {
		if (id != null) {
			// edit existing user
			user = userService.loadUser(id);
			passwordConfirm = user.getPassword();
			currentUsername = user.getUsername();
		} else {
			// create new user
			user = new User();
		}
	}

	public String save() throws IOException {
		if (!passwordConfirm.equals(user.getPassword())) {
			// display error message
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage message = getMessage(facesContext,
					"password_comfirm_failed");
			FacesContext.getCurrentInstance().addMessage(
					"userForm:passwordConfirm", message);

			// redisplay the form
			return null;
		} else {
			userService.save(user);

			if (image != null) {
				String homeDirectory = System.getProperty("user.home");

				image.write(homeDirectory + "/user-management/image-"
						+ user.getId());
			}

			return "users?faces-redirect=true";
		}
	}

	public void validateUsername(FacesContext context, UIComponent component,
			Object object) {
		String username = (String) object;

		if (!username.equals(currentUsername)) {
			if (userService.checkForExistingUser(username)) {
				throw new ValidatorException(getMessage(context,
						"user_exists_error"));
			}
		}
	}

	public void validateImage(FacesContext context, UIComponent component,
			Object object) {
		Part image = (Part) object;

		if (image != null) {
			if (!image.getContentType().startsWith("image")) {
				throw new ValidatorException(getMessage(context,
						"image_invalid"));
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Part getImage() {
		return image;
	}

	public void setImage(Part image) {
		this.image = image;
	}

}
