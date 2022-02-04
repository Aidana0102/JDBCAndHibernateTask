package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static peaksoft.util.Util.connection;


public class UserDaoJdbcImpl implements UserDao {
private User user1;

    public void createUsersTable() throws SQLException {
 String creator="Create table if not exists user "
                + "name VARCHAR(20) Primary key,"
                + "lastName varchar(25) ,"
                + "age int);";
        try {
            Connection con = Util.connection();
         Statement   statement = con.createStatement();
         statement.executeUpdate(creator);
            con.close();
            System.out.println("Table Created");
        }
        catch (SQLException e ) {
            System.out.println("An error has occured on Table Creation");
            e.getMessage();
        }
    }




    public void dropUsersTable() {
String delete="Drop table if exist user";
try (Connection con= Util.connection();
     Statement statement= con.createStatement()
    ){
    statement.executeUpdate(delete);
    System.out.println("Table user deleted");

} catch (SQLException e) {
    e.getMessage();
}


    }

    public void saveUser(String name, String lastName, byte age) {
String value="INSERT INTO user(name,lastName,age) VALUE(?,?,?)";
try (Connection connect=Util.connection();
     PreparedStatement statement= connect.prepareStatement(value)){
    statement.setString(1,name);
    statement.setString(2,lastName);
    statement.setInt(3,age);
    statement.executeUpdate();
} catch (SQLException e) {
    System.out.println(e.getMessage());
}

    }

    public void removeUserById(long id) {
   String zapros1="delete from user where id = ?";
   try {Connection connect=Util.connection();
        PreparedStatement statement= connect.prepareStatement(zapros1);
        statement.setLong(1,id);
    } catch (SQLException ex) {
       System.out.println(ex.getMessage());
   }
    }
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql2="Select * from user";
        try ( Connection connection=connection();
              Statement statement=connection.createStatement();
               ResultSet resultSet=statement.executeQuery(sql2);
               ){
            while ((resultSet.next())){
              resultSet.getString("name");
                resultSet.getString("lastName");
               resultSet.getInt("age");
               User obj1=new User(resultSet.getString("name") ,resultSet.getString("lastName"), (byte) resultSet.getInt("age"));
   userList.add(obj1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return userList ;
    }

    public void cleanUsersTable() {
String sql3="TRUNCATE table user";
try {Connection con=connection();
     Statement statement= con.createStatement();
    statement.executeUpdate(sql3);
    System.out.println("Table user cleaned ");
} catch (SQLException e) {
    System.out.println(e.getMessage());
}

    }
}