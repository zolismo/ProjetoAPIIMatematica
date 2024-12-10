package br.com.opusnet.projetoapiidoscrias.view;

import br.com.opusnet.projetoapiidoscrias.view.scene.HomeScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {

        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });
        stage.setResizable(false);
        
        try{
                    URL url = null;
                    try {
                        url = new File("src/main/resources/br/com/opusnet/projetoapiidoscrias/start-screen.fxml").toURI().toURL();
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(url);
                    HomeScene homeScene = null;

                    try {
                        homeScene = new HomeScene(fxmlLoader.load(),stage,fxmlLoader.getController());
                        stage.setScene(homeScene);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }catch (RuntimeException e){
                    System.out.println(e.getMessage());
                }



    }

    public static void main(String[] args) {
        launch(args);
    }
}