package mse.user.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mse.user.service.UserService;

@Named
@SessionScoped
public class IdentityController implements Serializable {

	private static final long serialVersionUID = 5755139117173743936L;

	private String username;

	private String password;

	private boolean loggedIn;

	@Inject
	private UserService userService;

	public String signup() {
		return "user?faces-redirect=true";
	}

	public String login() {
		if (userService.login(username, password)) {
			loggedIn = true;
			return "users?faces-redirect=true";
		} else {
			return "login-failed?faces-redirect=true";
		}
	}

	public String logout() {
		loggedIn = false;
		return "users?faces-redirect=true";
	}

	public boolean isUserAdmin(String username) {
		return "admin".equals(username);
	}

	public boolean isAdmin() {
		return loggedIn && isUserAdmin(username);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
