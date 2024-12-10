package br.com.opusnet.projetoapiidoscrias.view;

import br.com.opusnet.projetoapiidoscrias.controlls.screencontrol.HomeSceneControl;

import br.com.opusnet.projetoapiidoscrias.model.LifeGame;
import br.com.opusnet.projetoapiidoscrias.view.scene.HomeScene;

import br.com.opusnet.projetoapiidoscrias.model.util.SizeScreen;
import br.com.opusnet.projetoapiidoscrias.view.scene.SceneOne;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        SizeScreen.valueWidth = screenBounds.getWidth();
        SizeScreen.valueHeight = screenBounds.getHeight();



           // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("start-screen.fxml"));
           // Parent root = fxmlLoader.load();

           // HomeSceneControl homeSceneControl = fxmlLoader.getController();
            //HomeScene homeScene = new HomeScene(root, stage,homeSceneControl);

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