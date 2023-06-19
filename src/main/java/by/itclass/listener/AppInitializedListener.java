package by.itclass.listener;

import by.itclass.model.db.HibernateManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

@WebListener
public class AppInitializedListener implements ServletContextListener {
    //После инициализации контекста приложения сервером приложений
    //будет вызван метод contextInitialized()
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Method contextInitialized");
        HibernateManager.buildSessionFactory();
    }

    //Перед удалением  контекста приложения сервером приложений
    //будет вызван метод contextDestroyed()
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Method  contextDestroyed");
        HibernateManager.closeSessionFactory();
    }

}
