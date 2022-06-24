package com.example.demop2;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class DatabaseConnection {
    private String url = "jdbc:postgresql://ec2-34-200-35-222.compute-1.amazonaws.com:5432/db1lgj4b8ab4h6";
    private String user = "qoyjfvejpkbctd";
    private String password = "c0e2149cb58e5f2751b9b8930034846b9ab021347874d291713532562c46394d";
    private Connection con;

    public Connection getConnectionDB() {
        return this.con;
    }

    public DatabaseConnection() throws SQLException {
        this.con = DriverManager.getConnection(this.url, user, password);

    }


    public void checkConnection() {
        try {
            Connection con = DriverManager.getConnection(this.url, user, password);
            String query = "SELECT * FROM USERVR";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");

                System.out.println(id + " - " + name + " - " + password);
            }

            System.out.println("Connect success");
        }catch (Exception ex) {
            System.out.println("Connect Fail");
            System.out.println(ex.getMessage());
        }
    }

    private static Connection getConnection() throws SQLException, URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        return DriverManager.getConnection(dbUrl, username, password);
    }
}
