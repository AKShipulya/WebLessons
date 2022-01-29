package com.epam.testweb.command;

import com.epam.testweb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private final UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        boolean valid = service.login(login, password);
        if (valid) {
//            HttpSession session = req.getSession(true);
//            session.setAttribute("login", login);
//            session.setAttribute("role", "admin");
            req.getSession().setAttribute("user", "admin");
            return "WEB-INF/view/main.jsp";
        } else {
            req.setAttribute("errorMessage", "Invalid credentials");
            return "index.jsp";
        }
    }
}
