package by.itclass.model.db;

import by.itclass.model.entities.Image;
import by.itclass.model.entities.News;
import by.itclass.model.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateManager {
    private static SessionFactory sessionFactory;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static  SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void buildSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            sessionFactory = getConfiguration().buildSessionFactory();
        }
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null || !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/itclassdb");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Image.class);
        configuration.addAnnotatedClass(News.class);

        return configuration;
    }
}
