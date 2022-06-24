package com.example.demop2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HelloController implements Initializable {


    @FXML
    ImageView vrLogo;

    @FXML
    TextField usName;

    @FXML
    PasswordField usPass;

    @FXML
    Button loginButton;

    @FXML
    Label notice;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vrLogo.setFitHeight(300);
        vrLogo.setFitWidth(190);
        vrLogo.setImage(new Image("file:src/main/java/com/example/demop2/Pictures/logo2.png"));
        loginButton.setText("Đăng Nhập");
        notice.setText("");
    }

    public void buttonPress(ActionEvent event) {
        DatabaseConnection db = null;
        try {
            db = new DatabaseConnection();
        }catch (Exception ex) {
            notice.setText("Kiểm tra kết nối mạng");
            System.out.println("Cannot connect");
        }

        String usEnterName = usName.getText();
        String usEnterPass = usPass.getText();




        boolean isSuccess = false;
        String role = "";
        try {
            String query = "SELECT * FROM userVR WHERE name = " + "'" + usEnterName + "'" + " AND password = " + "'" + usEnterPass + "'";

            String query3 = "SELECT * FROM USERVR";
            Statement statement = db.getConnectionDB().createStatement();

            ResultSet resultSet = statement.executeQuery(query3);
            System.out.println(query);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                if ((name.compareTo(usEnterName) == 0) && (password.compareTo(usEnterPass) == 0)) {
                    isSuccess = true;
                    System.out.println(usEnterName.charAt(0));
                    if (id.charAt(0) == 'A') {
                        role = "Quản Trị";
                    }else if (id.charAt(0) == 'T') {
                        role = "Giáo Viên";
                    }
                    break;
                }
                System.out.println(id + " - " + name + " - " + password);
            }


        }catch (Exception ex) {
            System.out.println("Error");
        }

        if (isSuccess) {
            notice.setText("Đăng Nhập Thành Công - " + role);
            System.out.println("IN");
        }else {
            notice.setText("Sai tên đăng nhập hoặc mật khẩu");
        }



    }
}