package br.com.opusnet.projetoapiidoscrias.controlls.screencontrol;

import br.com.opusnet.projetoapiidoscrias.model.Controll;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class GameOverController implements Controll {

    @FXML
    public AnchorPane ac_start = new AnchorPane();

    @FXML
    public Button b_restart = new Button();

    @FXML
    public Button b_quit = new Button();

}
