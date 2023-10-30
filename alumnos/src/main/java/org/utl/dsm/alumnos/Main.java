package org.utl.dsm.alumnos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vista-alumnos.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 600);
        stage.setTitle("Alumnos BD");
        stage.setScene(scene);
        stage.show();
    }

}