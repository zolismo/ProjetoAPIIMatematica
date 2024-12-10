package br.com.opusnet.projetoapiidoscrias.view.scene;

import br.com.opusnet.projetoapiidoscrias.controlls.GameLoop;
import br.com.opusnet.projetoapiidoscrias.controlls.screencontrol.GameOverController;
import br.com.opusnet.projetoapiidoscrias.model.LifeGame;
import br.com.opusnet.projetoapiidoscrias.model.util.Updatable;
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
                gameLoop.stop();
                LifeGame.lifeGame = 3;
                FadeTransition ft = new FadeTransition();
                ft.setDuration(Duration.millis(1000));
                ft.setNode(controller.ac_start);
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setOnFinished((ActionEvent event) ->{
                    try{
                        URL url = null;
                        try {
                            url = new File("src/main/resources/br/com/opusnet/projetoapiidoscrias/level1.fxml").toURI().toURL();
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        }
                        gameLoop.stop();
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(url);
                        SceneOne sceneOne = null;

                        try {
                            sceneOne = new SceneOne(fxmlLoader.load(),stage,fxmlLoader.getController());
                            stage.setScene(sceneOne);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                });
                ft.play();
            }
        });
    }

    public void verifyQuit(){
        Platform.runLater(()->{
            if (controller.b_restart.isPressed()){
                System.exit(0);
            }
        });
    }

}
