package peaksoft.dao;

import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {


    }

    @Override
    public void createUsersTable() {
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            session.getTransaction().commit();
            session.close();
            System.out.println("created table successfully");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }





    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users");
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }}

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void removeUserById(long id) {
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            if(user!= null){
            session.delete(user);}
            session.getTransaction().commit();
            session.close();
            System.out.println("removed User By Id successfully" + user);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList= new ArrayList<>();
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            userList = session.createQuery("from User").list();
            session.getTransaction().commit();
            session.close();
            System.out.println("Finded : " + userList.size() + "users");
            return userList;
        }catch (Exception r ){
            System.out.println(r.getMessage());
            return userList;
        }

    }
        @Override
        public void cleanUsersTable() {
            try {
                Session session =Util.getSession().openSession();
                session.beginTransaction();
                session.createQuery("delete from User").executeUpdate();
                session.getTransaction().commit();
                session.close();
                System.out.println("Cleaned successfully");
            }catch (Exception e ){
                System.out.println(e.getMessage());
            }
        }
}
