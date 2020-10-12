package jm.task.core.jdbc.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {
        String str = "CREATE TABLE IF NOT EXISTS User(Id INT PRIMARY KEY AUTO_INCREMENT," +
                " Name VARCHAR(20), lastName VARCHAR(20), Age INT);";

        try (Connection con = (Connection) Util.getConnection();
             Statement stat = (Statement) con.createStatement()) {

            stat.executeUpdate(str);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String str = "DROP TABLE IF EXISTS User;";

        try (Connection con = (Connection) Util.getConnection();
             Statement stat = (Statement) con.createStatement()) {

            stat.executeUpdate(str);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String str = "INSERT User(Name, lastName, age) VALUES(?, ?, ?);";

        try (Connection con = (Connection) Util.getConnection();
             PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(str)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String str = "DELETE FROM User WHERE Id = ?";

        try (Connection con = (Connection) Util.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(str)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String str = "SELECT Id, Name, lastName, age FROM User;";

        try (Connection con = (Connection) Util.getConnection();
            Statement stat = (Statement) con.createStatement()) {

            ResultSet resultSet = stat.executeQuery(str);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("Id"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        String str = "DELETE FROM User;";

        try (Connection con = (Connection) Util.getConnection();
            Statement stat = (Statement) con.createStatement()) {
            stat.executeUpdate(str);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
