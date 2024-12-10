package br.com.opusnet.projetoapiidoscrias.view.scene;

import br.com.opusnet.projetoapiidoscrias.controlls.screencontrol.SceneOneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SceneTwo extends AbstractScene{
    public SceneTwo(Parent root, Stage stage, SceneOneController controll) {
        super(root,stage,controll,"windows",900,"500", "300","400", "100");
    }


    @Override
    public void setChangeScene() {

        System.out.println("Passou!!!!!");
        URL url = null;
        try {
            url = new File("src/main/resources/br/com/opusnet/projetoapiidoscrias/level3.fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        SceneThree sceneThree = null;

        try {

            sceneThree = new SceneThree(fxmlLoader.load(),stage,fxmlLoader.getController());
            stage.setScene(sceneThree);
        } catch (IOException e) {
            System.out.println("IOExcepition");
            throw new RuntimeException(e);
        }
    }
}
