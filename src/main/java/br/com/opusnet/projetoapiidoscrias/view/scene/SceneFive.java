package br.com.opusnet.projetoapiidoscrias.view.scene;

import br.com.opusnet.projetoapiidoscrias.controlls.screencontrol.SceneOneController;
import br.com.opusnet.projetoapiidoscrias.model.AbstractScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SceneFive extends AbstractScene {
    public SceneFive(Parent root, Stage stage, SceneOneController sceneOneController) {
        super(root,stage,sceneOneController,"((85 + 23 - 10) / 2) * 3",147,"200","131","53","45");
    }


    @Override
    public void setChangeScene() {
        URL url = null;
        try {
            url = new File("src/main/resources/br/com/opusnet/projetoapiidoscrias/game-win.fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        GameWinScene gameWinScene = null;

        try {
            gameWinScene = new GameWinScene(fxmlLoader.load(),stage,fxmlLoader.getController());
            stage.setScene(gameWinScene);
        } catch (IOException e) {
            System.out.println("IOExcepition");
            throw new RuntimeException(e);
        }
    }
}
