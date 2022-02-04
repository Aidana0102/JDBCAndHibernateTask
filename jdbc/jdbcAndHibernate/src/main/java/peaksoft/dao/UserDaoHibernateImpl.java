package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.HibernateUtil;
import peaksoft.util.sessionFactory;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {


    }

    @Override
    public void createUsersTable() {
        try {
            Session session = HibernateUtil.getSession().openSession();
            session.beginTransaction();
            session.getTransaction().commit();
            session.close();
            System.out.println("creat table successfully");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }





    @Override
    public void dropUsersTable() {
        Session session = HibernateUtil.getSession().openSession();
        session.beginTransaction();
        session.createQuery("DROP TABLE IF EXISTS USERS");
        session.getTransaction().commit();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = HibernateUtil.getSession().openSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();


    }

    @Override
    public void removeUserById(long id) {
        try {

            Session session = HibernateUtil.getSession().openSession();
            session.beginTransaction();
       User user = (User) session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("deleted " + user);

        } finally {
            peaksoft.util.HibernateUtil.getSession().close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = HibernateUtil.getSession().openSession();
        session.beginTransaction();

        List<User> users = session.createQuery("from Student ").list();
        session.getTransaction().commit();
        session.close();
        System.out.println("Finded : " + users.size() + "users");
        return users;

    }

        @Override
        public void cleanUsersTable() {
            try {
                Session session = HibernateUtil.getSession().openSession();
                session.beginTransaction();
                session.createQuery("delete  user").executeUpdate();
                session.getTransaction().commit();
                session.close();
                System.out.println("Cleaned successfully");
            }catch (Exception e ){
                System.out.println(e.getMessage());
            }
        }
}
