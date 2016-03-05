package mse.user.image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = -7732439245303842314L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");

		if (id == null) {
			resp.sendError(400, "No user id provided");
			return;
		}

		// don't permit access to the local file system
		if (id.contains(".") || id.contains("/") || id.contains("\\")) {
			resp.sendError(400, "Invalid user id");
			return;
		}

		String homeDirectory = System.getProperty("user.home")
				+ "/user-management";

		File image = new File(homeDirectory + "/image-" + id);

		if (!image.exists()) {
			image = new File(homeDirectory + "/default.png");
		}

		byte[] bytes = Files.readAllBytes(Paths.get(image.toURI()));

		resp.setContentLength(bytes.length);
		resp.setContentType("image");

		resp.getOutputStream().write(bytes);
	}
}
