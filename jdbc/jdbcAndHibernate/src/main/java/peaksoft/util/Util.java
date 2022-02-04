package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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





}
