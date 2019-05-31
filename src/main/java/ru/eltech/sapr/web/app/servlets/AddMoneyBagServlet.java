package ru.eltech.sapr.web.app.servlets;

import ru.eltech.sapr.web.app.exception.UserServiceException;
import ru.eltech.sapr.web.app.model.MoneyBag;
import ru.eltech.sapr.web.app.model.MoneyBagType;
import ru.eltech.sapr.web.app.service.MoneyBagService;
import ru.eltech.sapr.web.app.service.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddMoneyBagServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            MoneyBagService service = (MoneyBagService) getServletContext().getAttribute(MoneyBagService.SERVICE_NAME);

            MoneyBagType t;
            String type = req.getParameter("money_bag_type");
            float cash = Float.parseFloat(req.getParameter("cash"));
            String code = req.getParameter("code");
            int i = Integer.parseInt(req.getParameter("user_id"));
            if (type == "Cash")
            {
                t = MoneyBagType.Cash;
            }
            else if (type == "Deposit")
            {
                t = MoneyBagType.Deposit;
            }
            else
            {
                t = MoneyBagType.Card;
            }
            MoneyBag moneyBag = service.createMoneyBags(code, cash, t, i);
            // TODO: validate contact is created
            resp.sendRedirect("/");
        } catch (UserServiceException e) {
            resp.sendError(500, "Unable to create contact");
        }

    }
}
