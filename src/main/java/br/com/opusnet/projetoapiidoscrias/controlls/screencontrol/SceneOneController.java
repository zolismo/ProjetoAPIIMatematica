package br.com.opusnet.projetoapiidoscrias.controlls.screencontrol;

import br.com.opusnet.projetoapiidoscrias.model.Controll;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneOneController implements Controll, Initializable{

    public static int movement = 1;

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
    public AnchorPane ac_start = new AnchorPane();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switch(movement){
            case 1:{
                TranslateTransition tt = new TranslateTransition();
                tt.setNode(iv_enemy);
                tt.setDuration(Duration.millis(5000));
                tt.setCycleCount(TranslateTransition.INDEFINITE);
                tt.setFromX(75);
                tt.setToX(-75);
                tt.setAutoReverse(true);
                tt.play();
                break;
            }
            case 2:{
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
            case 3:{
                RotateTransition rt = new RotateTransition();
                rt.setNode(iv_enemy);
                rt.setDuration(Duration.millis(2500));
                rt.setCycleCount(TranslateTransition.INDEFINITE);
                rt.setFromAngle(-10);
                rt.setToAngle(10);
                rt.setAutoReverse(true);
                rt.play();
                break;
            }
            case 4:{
                Circle circle = new Circle(20);
                PathTransition transition = new PathTransition();
                transition.setNode(iv_enemy);
                transition.setDuration(Duration.millis(4000));
                transition.setPath(circle);
                transition.setCycleCount(PathTransition.INDEFINITE);
                transition.setInterpolator(Interpolator.LINEAR);
                transition.play();
                break;
            }
            case 5:{
                ScaleTransition sc = new ScaleTransition();
                sc.setNode(iv_enemy);
                sc.setDuration(Duration.millis(40000));
                sc.setToX(10);
                sc.setToY(10);
                sc.play();
                break;
            }
        }

    }
}
