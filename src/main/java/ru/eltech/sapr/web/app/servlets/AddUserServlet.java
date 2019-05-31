package ru.eltech.sapr.web.app.servlets;


import ru.eltech.sapr.web.app.exception.UserServiceException;
import ru.eltech.sapr.web.app.model.User;
import ru.eltech.sapr.web.app.model.UserType;
import ru.eltech.sapr.web.app.service.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService service = (UserService) getServletContext().getAttribute(UserService.SERVICE_NAME);

            String firstName = req.getParameter("first_name");
            String lastName = req.getParameter("last_name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            User user = service.createUser(firstName, lastName, email, password, UserType.User);
            // TODO: validate contact is created
            resp.sendRedirect("/");
        } catch (UserServiceException e) {
            resp.sendError(500, "Unable to create contact");
        }

    }
}
