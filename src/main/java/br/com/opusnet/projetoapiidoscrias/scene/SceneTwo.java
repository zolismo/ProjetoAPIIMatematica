package br.com.opusnet.projetoapiidoscrias.scene;

import br.com.opusnet.projetoapiidoscrias.model.Controll;
import br.com.opusnet.projetoapiidoscrias.model.ScreemInterface;
import br.com.opusnet.projetoapiidoscrias.util.Updatable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneTwo extends Scene implements Updatable, ScreemInterface {
    public SceneTwo(Parent root, Stage stage, Controll controll) {
        super(root);
    }

    @Override
    public void update() throws IOException {

    }

    @Override
    public void render() {

    }
}
