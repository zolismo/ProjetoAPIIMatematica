package br.com.opusnet.projetoapiidoscrias.controlls.screencontrol;

import br.com.opusnet.projetoapiidoscrias.model.Controll;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneTwoController implements Controll, Initializable {

    public static int movement = 0;

    @FXML
    public Text t_answer = new Text();

    @FXML
    public Button b_confirm = new Button();

    @FXML
    public Button b_delet = new Button();

    @FXML
    public Button b_add = new Button();

    @FXML
    public Button b_sub = new Button();

    @FXML
    public Button b_mult = new Button();

    @FXML
    public Button b_div = new Button();

    @FXML
    public Button b_char1 = new Button();

    @FXML
    public Button b_char2 = new Button();

    @FXML
    public Button b_char3 = new Button();

    @FXML
    public Button b_char4 = new Button();

    @FXML
    public ImageView iv_enemy = new ImageView();

    @FXML
    public Text t_level1 = new Text();

    @FXML
    public Text t_equacao = new Text();

    @FXML
    public Text t_res1 = new Text();


    @FXML
    public Text t_res2 = new Text();


    @FXML
    public Text t_res3 = new Text();


    @FXML
    public Text t_res4 = new Text();

    @FXML
    public Text t_life = new Text();

    @FXML
    public Pane p_level1 = new Pane();

    @FXML
    public ImageView  i_sky = new ImageView();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switch(movement){
            case 0:{
                TranslateTransition tt = new TranslateTransition();
                tt.setNode(iv_enemy);
                tt.setDuration(Duration.millis(1000));
                tt.setCycleCount(TranslateTransition.INDEFINITE);
                tt.setFromY(75);
                tt.setToY(-75);
                tt.setAutoReverse(true);
                tt.play();
                break;
            }
            case 1:{
                break;
            }
            case 2:{
                break;
            }
            case 3:{
                break;
            }
            case 4:{
                break;
            }
        }
    }
}
