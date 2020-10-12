package jm.task.core.jdbc.dao;

import com.mysql.jdbc.Statement;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    //private Session session = null;

    public UserDaoHibernateImpl() {
        //session = Util.getSessionFactory().openSession();
    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS User(Id INT PRIMARY KEY AUTO_INCREMENT," +
                " Name VARCHAR(20), lastName VARCHAR(20), Age INT);";

        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS User;";

        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        User user = new User(name, lastName, age);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        User user = (User) session.load(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
