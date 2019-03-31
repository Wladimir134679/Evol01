package com.wdeath.game.evol01.editor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LauncherEditor extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edit/EditorStage.fxml"));
        loader.load();
        VBox p = loader.getRoot();


        Scene scene = new Scene(p);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Эволюция");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
