package ru.eltech.sapr.web.app.servlets;

import ru.eltech.sapr.web.app.dao.H2MoneyBagDao;
import ru.eltech.sapr.web.app.dao.H2TransactionDao;
import ru.eltech.sapr.web.app.dao.H2UserDao;
import ru.eltech.sapr.web.app.ConnectionManager;
import ru.eltech.sapr.web.app.service.MoneyBagService;
import ru.eltech.sapr.web.app.service.TransactionService;
import ru.eltech.sapr.web.app.service.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;

@WebListener
public class ServiceInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            DataSource dataSource = ConnectionManager.createDataSource();
            UserService userService = new UserService(new H2UserDao(dataSource));
            TransactionService transactionService = new TransactionService(new H2TransactionDao(dataSource));
            MoneyBagService moneyBagService = new MoneyBagService(new H2MoneyBagDao(dataSource));
            sce.getServletContext().setAttribute(userService.SERVICE_NAME, userService);
            sce.getServletContext().setAttribute(transactionService.SERVICE_NAME, transactionService);
            sce.getServletContext().setAttribute(moneyBagService.SERVICE_NAME, moneyBagService);
        } catch (IOException e) {
            throw new RuntimeException("Unable to initialize contacts service");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
