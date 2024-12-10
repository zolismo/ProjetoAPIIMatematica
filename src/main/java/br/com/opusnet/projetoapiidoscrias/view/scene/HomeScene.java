package br.com.opusnet.projetoapiidoscrias.view.scene;

import br.com.opusnet.projetoapiidoscrias.controlls.GameLoop;
import br.com.opusnet.projetoapiidoscrias.controlls.screencontrol.HomeSceneControl;
import br.com.opusnet.projetoapiidoscrias.model.ScreemInterface;
import br.com.opusnet.projetoapiidoscrias.model.util.Updatable;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeScene extends Scene implements Updatable, ScreemInterface {
    private HomeSceneControl homeSceneControl;
    private GameLoop gameLoop;
    private Stage stage;

    public HomeScene(Parent root, Stage stage,HomeSceneControl homeSceneControl) throws FileNotFoundException {
        super(root);
        this.homeSceneControl = homeSceneControl;
        this.stage = stage;

        InputStream stream = new FileInputStream("src/main/resources/br/com/opusnet/projetoapiidoscrias/Char_Quadrado/Char_QuadradoSelecionado00.png");
        Image icon = new Image(stream);
        stage.getIcons().add(icon);

        stage.setScene(this);

        stage.setTitle("Valus");
        stage.show();
        gameLoop = new GameLoop(this);
        new Thread(gameLoop).start();
    }

    @Override
    public void update() throws IOException {

        Platform.runLater(()->{

            if (homeSceneControl.b_newgame.isPressed()) {

                FadeTransition ft = new FadeTransition();
                ft.setDuration(Duration.millis(1000));
                ft.setNode(homeSceneControl.ac_start);
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setOnFinished((ActionEvent event) ->{
                    URL url = null;
                    try {
                        url = new File("src/main/resources/br/com/opusnet/projetoapiidoscrias/level1.fxml").toURI().toURL();
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    gameLoop.stop();
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(url);
                    //  SceneOneController sceneOneController = fxmlLoader.getController();
                    SceneOne sceneOne = null;

                    try {
                        sceneOne = new SceneOne(fxmlLoader.load(),stage,fxmlLoader.getController());
                        stage.setScene(sceneOne);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                ft.play();



            }
        });

        if (homeSceneControl.b_quit.isPressed()) {
            System.exit(0);
        }

    }

    @Override
    public void render() {
        Platform.runLater(() -> {
            if (homeSceneControl.b_newgame.isPressed()) {
                homeSceneControl.b_newgame.setText("Iniciando Jogo...");
             }
        });
        System.out.println(homeSceneControl.b_newgame.getText());
        System.out.println("Renderizando HomeScene");
    }
}