package peaksoft.service;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
  //UserDao obj=new UserDaoJdbcImpl();
UserDao obj=new UserDaoHibernateImpl();

    public void createUsersTable() throws SQLException {
        obj.createUsersTable();
    }

    public void dropUsersTable() {
 obj.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
obj.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
obj.removeUserById(id);
    }

    public List<User> getAllUsers() {

      return   obj.getAllUsers();
  }

    public void cleanUsersTable() {
        obj.cleanUsersTable();
    }
}
