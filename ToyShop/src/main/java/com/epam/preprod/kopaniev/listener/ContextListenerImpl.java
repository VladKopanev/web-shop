package com.epam.preprod.kopaniev.listener;

import com.epam.preprod.kopaniev.repository.transaction.TransactionManagerImpl;
import com.epam.preprod.kopaniev.service.ServiceFactoriesImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

/**
 * Created by Vladyslav_Kopaniev on 11/13/2015.
 */
public class ContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        setServiceFactories(context);
        setTransactionManager(context);

        context.setAttribute("javax.servlet.context.tempdir", "WEB-INF/tmp");
    }


    private void setServiceFactories(ServletContext context) {
        context.setAttribute("factories", new ServiceFactoriesImpl());
    }

    private void setTransactionManager(ServletContext context) {
        DataSource ds;
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/toys_db");
        } catch (NamingException e) {
            e.printStackTrace();
            return;
        }
        context.setAttribute("transactionManager", new TransactionManagerImpl(ds));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //do nothing
    }
}
