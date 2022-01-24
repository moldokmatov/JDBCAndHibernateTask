package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        try (Connection conn = Util.connection()
            ) {
            Statement stmt=conn.createStatement();

            String sql = "CREATE TABLE users" +
                    "(id serial primary key  not NULL, " +
                    "name VARCHAR (225), " +
                    " lastName VARCHAR (225), " +
                    " age int  not null)";

            stmt.executeUpdate(sql);
            System.out.println("Таблица тузулду");
        } catch(SQLException e){
            e.printStackTrace();

        }
    }


    public void dropUsersTable() {
        try(Connection conn=Util.connection()) {
            Statement statement=conn.createStatement();
            String SQL="Drop table users" ;
            statement.executeUpdate(SQL);
            System.out.println();
            System.out.println(" Таблица очурулду ");
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "insert into users (name,lastName,age) VALUES(?,?,?)";

        try (Connection conn = Util.connection()) {
            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            System.out.println();
            System.out.println("Кошулду ");
            System.out.println();
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String SQL=" Delete  from users where id = ?";
        try(Connection conn =Util.connection()) {
            PreparedStatement preparedStatement=conn.prepareStatement(SQL);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Очурулду");
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> cities = new ArrayList<>();


        String SQL = "SELECT * FROM users";
        try (Connection conn = Util.connection();
             Statement stmt = conn.createStatement();

             ResultSet rs = stmt.executeQuery(SQL)) {
            User user=new User();
            while (rs.next()) {
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(rs.getByte(4));

                cities.add(user);
                System.out.println(user);

            }
        } catch (
                SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cities;

    }

    public void cleanUsersTable() {
        String SQL="Delete from users";
        try(Connection conn=Util.connection()) {
            Statement statement=conn.createStatement();
            statement.executeUpdate(SQL);
            System.out.println("  Маалыматтар очурулду  ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}