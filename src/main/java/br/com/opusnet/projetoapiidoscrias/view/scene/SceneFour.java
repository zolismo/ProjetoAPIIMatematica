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

public class SceneFour extends AbstractScene {
    public SceneFour(Parent root, Stage stage, SceneOneController sceneOneController) {
        super(root,stage,sceneOneController," 4 x 15 x 5 x 2",600," 200","100","6","3");
    }


    @Override
    public void setChangeScene() {
        gameLoop.stop();
        System.out.println("Passou!!!!!");
        URL url = null;
        try {
            url = new File("src/main/resources/br/com/opusnet/projetoapiidoscrias/level5.fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        SceneFive sceneFive = null;

        try {
            sceneFive = new SceneFive(fxmlLoader.load(),stage,fxmlLoader.getController());
            stage.setScene(sceneFive);
        } catch (IOException e) {
            System.out.println("IOExcepition");
            throw new RuntimeException(e);
        }
    }
}
