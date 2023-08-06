package com.kuumca.dao;

import com.kuumca.db.MyConnection;
import com.kuumca.model.Data;

import java.io.*;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataDAO {
    public static List<Data> getAllFiles(String email) {
        Connection connection = MyConnection.getConnection();
        List<Data> files = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from data where email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            files = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String path = resultSet.getString(3);
                files.add(new Data(id, name, path));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }

    public  static int hideFile(Data file) {
        Connection connection = MyConnection.getConnection();
        File f = null;
        FileReader fr = null;
        int rowCount = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into data (name,path,email,bin_data)values (?,?,?,?)");
            preparedStatement.setString(1, file.getFileName());
            preparedStatement.setString(2, file.getPath());
            preparedStatement.setString(3, file.getEmail());

            f = new File(file.getPath());
            fr = new FileReader(f);
            preparedStatement.setCharacterStream(4, fr, f.length());
            rowCount = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
                f.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return rowCount;


    }
    public  static  void unhide(int id) {
        Connection connection = MyConnection.getConnection();
        FileWriter fw = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select path,bin_data from data where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String path = resultSet.getString(1);
            Clob c = resultSet.getClob(2);

            Reader r = c.getCharacterStream();
            fw = new FileWriter(path);
            int i;
            while ((i = r.read()) != -1) {
                fw.write((char) i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        try {
            preparedStatement = connection.prepareStatement("delete from data where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("File Successfully Un-hided");
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
