package br.com.opusnet.projetoapiidoscrias.view.scene;

import br.com.opusnet.projetoapiidoscrias.controlls.screencontrol.SceneOneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SceneThree extends AbstractScene {
    public SceneThree(Parent root, Stage stage, SceneOneController sceneOneController) {
        super(root,stage,sceneOneController," 900 / 30 / 15 = ? ", 2, "50", "65", "2","25");
    }


    @Override
    public void setChangeScene() {
        gameLoop.stop();
        System.out.println("Passou!!!!!");
        URL url = null;
        try {
            url = new File("src/main/resources/br/com/opusnet/projetoapiidoscrias/level4.fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        SceneFour sceneFour = null;

        try {
            sceneFour = new SceneFour(fxmlLoader.load(),stage,fxmlLoader.getController());
            stage.setScene(sceneFour);
        } catch (IOException e) {
            System.out.println("IOExcepition");
            throw new RuntimeException(e);
        }
    }
}
