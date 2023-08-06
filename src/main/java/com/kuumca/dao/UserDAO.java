package com.kuumca.dao;

import com.kuumca.db.MyConnection;
import com.kuumca.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static boolean isExist(String email){
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where email=?");
            preparedStatement.setString(1,email);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public  static  int saveUser(User user){
        Connection connection = MyConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into user (name,email) values (?,?)");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());

            int rowCount=preparedStatement.executeUpdate();
            return rowCount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
