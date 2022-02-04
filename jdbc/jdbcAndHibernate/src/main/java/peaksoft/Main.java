package peaksoft;

import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        try {
            userService.createUsersTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        userService.saveUser("Jack","Jeky", (byte) 18);
        userService.saveUser("Anna","Kavin", (byte) 8);
        userService.saveUser("Jackson","liza", (byte) 45);
        userService.saveUser("Elza","Maven", (byte) 33);

        userService.getAllUsers();


     userService.removeUserById(3);
   userService.cleanUsersTable();
  userService.dropUsersTable();
    }
}
