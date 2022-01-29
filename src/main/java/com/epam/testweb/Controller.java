package com.epam.testweb;

import com.epam.testweb.command.Command;
import com.epam.testweb.command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String commandParam = req.getParameter("command");
            CommandFactory commandFactory = new CommandFactory();
            Command command = commandFactory.createCommand(commandParam);
            String page = command.execute(req, resp);
            dispatch(page, req, resp);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void dispatch(String page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
