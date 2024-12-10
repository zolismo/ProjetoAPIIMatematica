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

public class SceneOne extends AbstractScene {


    public SceneOne(Parent root,Stage stage, SceneOneController controller){
        super(root,stage,controller,"x² - 500x + 62500 = 0",250,"200","90","50","20");
    }

    @Override
    public void setChangeScene() {
        System.out.println("Passou!!!!!");
        URL url = null;
        try {
            url = new File("src/main/resources/br/com/opusnet/projetoapiidoscrias/level2.fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        SceneTwo sceneTwo = null;

        try {
            SceneOneController.movement = 2;
            sceneTwo = new SceneTwo(fxmlLoader.load(),stage,fxmlLoader.getController());
            stage.setScene(sceneTwo);
        } catch (IOException e) {
            System.out.println("IOExcepition");
            throw new RuntimeException(e);
        }
    }

}


