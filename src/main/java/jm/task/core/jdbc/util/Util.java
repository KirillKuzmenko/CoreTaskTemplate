package jm.task.core.jdbc.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.cfg.Configuration;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() throws SQLException,
            ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "User";
        String username = "root";
        String password = "admin";

        return getConnection(hostName, dbName, username, password);
    }

    public static Connection getConnection(String hostName, String dbName,
                                           String username, String password)
            throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + "/" + dbName
                + "?serverTimezone=Europe/Moscow&useSSL=false";

        return DriverManager.getConnection(connectionURL, username, password);
    }

    private static final SessionFactory sessionFactory = configureSessionFactory();

    private static ServiceRegistry serviceRegistry;

    /**
     * Создание фабрики
     * @return {@link SessionFactory}
     * @throws org.hibernate.HibernateException
     */
    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url",
                        "jdbc:mysql://localhost/User/?serverTimezone=Europe/Moscow&useSSl=false")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection,password", "admin")
                .setProperty("hibernate.connection.pool_size", "1")
                .setProperty("hibernate.connection.autocommit", "false")
                .setProperty("hibernate.cache.provider_class",
                        "org.hibernate.cache.NoCacheProvider")
                .setProperty("hibernate.cache.use_second_level_cache", "false")
                .setProperty("hibernate.cache.use_query_cache", "false")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.current_session_context_class", "thread")
                .addPackage("jm.task.core.jdbc")
                .addAnnotatedClass(jm.task.core.jdbc.model.User.class);
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    /**
     * Получить фабрику сессий
     * @return {@link SessionFactory}
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
