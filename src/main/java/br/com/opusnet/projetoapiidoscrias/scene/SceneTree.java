package br.com.opusnet.projetoapiidoscrias.scene;

import br.com.opusnet.projetoapiidoscrias.model.ScreemInterface;
import br.com.opusnet.projetoapiidoscrias.util.Updatable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneTree extends Scene implements Updatable, ScreemInterface {
    public SceneTree(Parent root) {
        super(root);
    }

    @Override
    public void update() throws IOException {

    }

    @Override
    public void render() {

    }
}
