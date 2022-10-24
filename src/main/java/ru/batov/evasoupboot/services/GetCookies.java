package ru.batov.evasoupboot.services;

import java.sql.*;
import java.util.HashMap;


public class GetCookies {

    public HashMap<String, String> getCookies(){
        HashMap<String, String> cookies = new HashMap<>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:/TEMP/db/MainBaseMse.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement statement = connection.prepareStatement("select name, VALUE from cookies")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                cookies.put(resultSet.getString(1), resultSet.getString(2));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return cookies;
        }
        return cookies;


    }
}
