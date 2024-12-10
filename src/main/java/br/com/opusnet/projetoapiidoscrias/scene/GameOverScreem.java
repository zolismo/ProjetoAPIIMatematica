package br.com.opusnet.projetoapiidoscrias.scene;

import br.com.opusnet.projetoapiidoscrias.controlls.GameLoop;
import br.com.opusnet.projetoapiidoscrias.controlls.screencontrol.GameOverController;
import br.com.opusnet.projetoapiidoscrias.controlls.screencontrol.HomeSceneControl;
import br.com.opusnet.projetoapiidoscrias.model.Controll;
import br.com.opusnet.projetoapiidoscrias.util.Updatable;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class GameOverScreem extends Scene implements Updatable {
    private GameOverController controller;
    private GameLoop gameLoop;
    private Stage stage;
    public GameOverScreem(Parent root, Stage stage, GameOverController controller) {
        super(root);
        if(controller instanceof GameOverController){
            this.controller = (GameOverController) controller;
        }else{
            throw new IllegalArgumentException();
        }
        this.stage = stage;
        FadeTransition ft2 = new FadeTransition();
        ft2.setDuration(Duration.millis(3000));
        ft2.setNode(this.controller.ac_start);
        ft2.setInterpolator(Interpolator.EASE_BOTH);
        ft2.setFromValue(0.0);
        ft2.setToValue(1.0);
        ft2.play();
        gameLoop = new GameLoop(this);
        new Thread(gameLoop).start();
    }

    @Override
    public void update() throws IOException {
        verifyRestart();
        verifyQuit();
    }

    @Override
    public void render() {

    }

    public void verifyRestart(){
        Platform.runLater(()->{
            if(controller.b_restart.isPressed()){
                FadeTransition ft = new FadeTransition();
                ft.setDuration(Duration.millis(1000));
                ft.setNode(controller.ac_start);
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setOnFinished((ActionEvent event) ->{
                    try{
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("start-screen.fxml"));
                        Parent root = fxmlLoader.load();

                        HomeSceneControl homeSceneControl = fxmlLoader.getController();
                        HomeScene homeScene = new HomeScene(root, stage,homeSceneControl);
                    }catch (RuntimeException | IOException e){

                    }
                });
                ft.play();
            }
        });
    }

    public void verifyQuit(){
        Platform.runLater(()->{

        });
    }

}
