package br.com.opusnet.projetoapiidoscrias.scene;

import br.com.opusnet.projetoapiidoscrias.controlls.screencontrol.GameOverController;
import br.com.opusnet.projetoapiidoscrias.model.Controll;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameOverScreem extends Scene {
    private GameOverController gameOverController;
    public GameOverScreem(Parent root, Stage stage, Controll gameOverController) {
        super(root);
        if(gameOverController instanceof GameOverController){
            this.gameOverController = (GameOverController) gameOverController;
        }else{
            throw new IllegalArgumentException();
        }
    }
}
