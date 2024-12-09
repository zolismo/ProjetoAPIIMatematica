package br.com.opusnet.projetoapiimatematica;

import br.com.opusnet.projetoapiimatematica.controlls.screencontrol.HomeSceneControl;

import br.com.opusnet.projetoapiimatematica.scene.HomeScene;

import br.com.opusnet.projetoapiimatematica.util.NavigatorScene;
import br.com.opusnet.projetoapiimatematica.util.SizeScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        SizeScreen.valueWidth = screenBounds.getWidth();
        SizeScreen.valueHeight = screenBounds.getHeight();

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("start-screen.fxml"));
            Parent root = fxmlLoader.load();

            HomeSceneControl homeSceneControl = fxmlLoader.getController();
            HomeScene homeScene = new HomeScene(root, stage,homeSceneControl);

     //       NavigatorScene.navigatorScene(stage, HomeScene.class,"start-screen.fxml");

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}