package ru.job4j.cinema.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cinema.persistence.Account;
import ru.job4j.cinema.persistence.Place;
import ru.job4j.cinema.service.PsqlService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.sql.SQLException;


public class PaymentServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        Account account = new Account(
                req.getParameter("username"),
                req.getParameter("phone")
        );
        PsqlService.instOf().addAccount(account);
        Place place = PsqlService.instOf().findByIdPlace(Integer.parseInt(req.getParameter("id")));
        try {
            account.setIdA(PsqlService.instOf().findByNumber(account.getPhoneNumber()).getIdA());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        place.setAccountId(account);
        try {
            PsqlService.instOf().addOrders(place);
            PsqlService.instOf().buy(account, Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath() + "/index.html");
        } catch (Exception e) {
            req.setAttribute("error", "Выбранное вами место уже занято, пожалуйста выберите другое - "
                    + " вернитесь на главную страницу и выберите свободное место!");
            req.getRequestDispatcher("payment.jsp").forward(req, resp);
            LOG.error(e.getMessage(), e);
        }
    }
}
