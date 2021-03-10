package ru.job4j.cinema.controller;

import ru.job4j.cinema.persistence.Account;
import ru.job4j.cinema.service.PsqlService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Account account = new Account(
                req.getParameter("username"),
                req.getParameter("phone")
        );
        PsqlService.instOf().buy(account, Integer.parseInt(req.getParameter("id")));
    }
}
