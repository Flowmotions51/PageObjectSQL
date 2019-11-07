package helpers;

import model.LoginData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private final String url;
    private Connection connection;

    DatabaseHelper(String url, String driver) throws ClassNotFoundException, SQLException {
        this.url = url;
        Class.forName(driver);
        connection = DriverManager.getConnection(url);
    }


    public void disconnect() throws SQLException {
        connection.close();
    }

    public List<LoginData> getData() throws SQLException {

        List<LoginData> loginData = new ArrayList<>();

        String query=String.format("select * from cian");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()){
            loginData.add(new LoginData()
                    .setLogin(rs.getString("login"))
                    .setPassword(rs.getString("password")));
        }
        rs.close();
        statement.close();
        return loginData;
    }


}
