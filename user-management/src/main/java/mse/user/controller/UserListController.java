package mse.user.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mse.user.model.User;
import mse.user.service.UserService;

@Named
@ViewScoped
public class UserListController implements Serializable {

	private static final long serialVersionUID = 3387236069407707457L;
	private static final int MAX_PAGES = 5;

	private List<User> users;

	@Inject
	private UserService userService;

	private Integer currentPage;

	private Long pages;

	public String onPageLoad() {
		if (currentPage == null) {
			return "users?page=1&faces-redirect=true";
		}

		pages = userService.getPageCount();
		System.out.println("------ boza ------" + pages);
		loadUsers();
		return null;
	}

	public void delete(User user) {
		userService.delete(user);
		loadUsers();
	}

	public List<Integer> getPagerItems() {
		List<Integer> result = new ArrayList<Integer>();

		int start = -1;
		int end = -1;
		int pagesToShow = Math.min(MAX_PAGES, pages.intValue());

		start = currentPage - pagesToShow / 2;

		if (start <= 0) {
			start = 1;
		} else if (pages.intValue() - start < pagesToShow) {
			start = pages.intValue() - pagesToShow + 1;
		}

		end = start + pagesToShow - 1;

		for (int i = start; i <= end; i++) {
			result.add(i);
		}

		return result;
	}

	private void loadUsers() {
		users = userService.getUsers(currentPage);
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Long getPages() {
		return pages;
	}

}
