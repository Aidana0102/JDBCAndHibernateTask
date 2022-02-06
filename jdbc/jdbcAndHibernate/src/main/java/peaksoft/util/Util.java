package peaksoft.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД


    private final static String url = "jdbc:postgresql://localhost:5432/7homework";
    private final static String user = "postgres";
    private final static String password = "1234";

    public  static Connection connection(){
        Connection connection=null;
        try{
            connection= DriverManager.getConnection(url,user,password);
            System.out.println("Connected succesfully");
        } catch (SQLException e) {
            System.out.println( e.getMessage());
        }
        return connection;
    }

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Properties prop = new Properties();

        prop.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        prop.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
        prop.setProperty("hibernate.connection.username", "postgres");
        prop.setProperty("hibernate.connection.password", "1234");
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        prop.setProperty("hibernate.show_sql", "true");
        prop.setProperty("hibernate.hbm2ddl.auto", "create");
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(peaksoft.model.User.class);
        cfg.setProperties(prop);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        System.out.println("Connected to hibernate");
        return cfg.buildSessionFactory(serviceRegistry);
    }
    public static SessionFactory getSession() {
        return sessionFactory;
    }
    public static void shutDown() {
        getSession().close();
    }


    







}
