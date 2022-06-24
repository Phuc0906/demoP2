package com.example.demop2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        fxmlLoader.getController();
        Group root = new Group();

        stage.setScene(scene);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to visible bounds of the main screen
        stage.setX(primaryScreenBounds.getWidth()/6);
        stage.setY(primaryScreenBounds.getHeight()/4);
        stage.setWidth(primaryScreenBounds.getWidth()*2/3);
        stage.setHeight(primaryScreenBounds.getHeight()*2/3);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}