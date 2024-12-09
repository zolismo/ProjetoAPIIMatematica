package br.com.opusnet.projetoapiidoscrias.scene;

import br.com.opusnet.projetoapiidoscrias.controlls.GameLoop;
import br.com.opusnet.projetoapiidoscrias.controlls.screencontrol.SceneOneController;
import br.com.opusnet.projetoapiidoscrias.model.LifeGame;
import br.com.opusnet.projetoapiidoscrias.model.ScreemInterface;
import br.com.opusnet.projetoapiidoscrias.util.Updatable;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SceneOne extends Scene implements Updatable, ScreemInterface {
    private final GameLoop gameLoop;
    private final SceneOneController controller;
    private final Stage stage;

    private double valueEnime = 250;
    private final String equacionEnime = "x² - 500x + 62500 = 0";
    private double respostPerson;
    private double[] valueSelected = {0, 0};
    private int buttonPressed = 0;
    private boolean win = false;
    private boolean sentinel = true;

    private boolean animationTriangleProcessed = true;
    private int animationTriangle = 0;
    private Image imageTriangle;

    private boolean animationLosangreProcessed = true;
    private int animationLosangle = 0;

    private boolean animationSquareProcessed = true;
    private int animationSquare = 0;

    private boolean animationCircleProcessed = true;
    private int animationCircle = 0;


    private boolean buttonProcessed = false;
    private boolean personSelectionProcessed = false;

    private boolean animationEnimeProcessed = true;
    private int animationEnime = 0;
    private Map<String, Image> imageCache = new HashMap<>();

    public SceneOne(Parent root, Stage stage, SceneOneController controller) {
        super(root);
        this.stage = stage;
        this.controller = controller;
        controller.t_equacao.setText(equacionEnime);


        setValuesPerson();
        controller.t_equacao.setText(equacionEnime);
        gameLoop = new GameLoop(this);
        new Thread(gameLoop).start();
    }

    public void setValuesPerson() {
        controller.t_res1.setText("200");
        controller.t_res2.setText("90");
        controller.t_res3.setText("50");
        controller.t_res4.setText("20");
    }

    @Override
    public void update() {
        System.out.println("Value 1: " + valueSelected[0]);
        System.out.println("Value 2: " + valueSelected[1]);
        System.out.println("Button Pressed: " + buttonPressed);
        System.out.println("Resposta calculada: " + respostPerson);
        handleButtonPress();
        handlePersonSelect();
        checkAnswer();
    }

    @Override
    public void render() {
        Platform.runLater(() -> {
            animationEnime();
            animationTriangle();
            animationCircle();
            animationSquare();
            animationLosangle();

            if (LifeGame.lifeGame == 0) {
                controller.t_level1.setVisible(true);
                controller.t_level1.setText("Você perdeu");
                gameLoop.stop();
            }

            if(win) {
                controller.t_level1.setVisible(true);
                controller.t_level1.setText("Você ganhou");
                gameLoop.stop();
            }

            moveEnime();
        });
    }

    private void animationSquare() {
        if (animationSquareProcessed && animationSquare < 58) {
            animationSquare++;
            updateImage("Char_Quadrado", "QuadradoSelecionado", animationSquare, 2);
        } else if (animationSquare >= 30) {
            animationSquareProcessed = false;
            animationSquare = 0;
        } else {
            animationSquareProcessed = true;
            animationSquare = 0;
        }
    }

    private void animationLosangle() {
        if (animationLosangreProcessed && animationLosangle < 3) {
            animationLosangle++;
            updateImage("Char_LosangoSelecionado", "Losango", animationLosangle, 3);
        } else if (animationLosangle >= 30) {
            animationLosangreProcessed = false;
            animationLosangle = 0;
        } else {
            animationLosangreProcessed = true;
            animationLosangle = 0;
        }
    }

    private void animationTriangle() {
        if (animationTriangleProcessed && animationTriangle < 30) {
            animationTriangle++;
            updateImage("Char_Triangulo", "TrianguloSelecionado", animationTriangle, 4);
        } else if (animationTriangle >= 30) {
            animationTriangleProcessed = false;
            animationTriangle = 0;
        } else {
            animationTriangleProcessed = true;
            animationTriangle = 0;
        }
    }

    private void animationCircle() {
        if (animationCircleProcessed && animationCircle < 33) {
            animationCircle++;
            updateImage("Char_CirculoSelecionado", "Circulo", animationCircle, 1);
        } else if (animationCircle >= 33) {
            animationCircleProcessed = false;
            animationCircle = 0;
        } else {
            animationCircleProcessed = true;
            animationCircle = 0;
        }
    }

    private void animationEnime() {
        if (animationEnimeProcessed && animationEnime < 7) {

            String enimePath = String.format("src/main/resources/br/com/opusnet/projetoapiidoscrias/Enemy_Boss/Enemy_Boss"+ animationEnime + ".png");
            animationEnime++;
            
            Image image = imageCache.get(enimePath);
            if (image == null) {
                File file = new File(enimePath);
                if (file.exists()) {
                    image = new Image(file.toURI().toString());
                    imageCache.put(enimePath, image);
                    controller.iv_enemy1.setImage(image);
                } else {
                    System.out.println("Imagem não encontrada: " + enimePath);
                }
            }

        } else if (animationEnime >= 7) {
            animationEnimeProcessed = false;
            animationEnime = 0;
        } else {
            animationEnimeProcessed = false;
            animationEnime = 0;
        }
    }



    private void updateImage(String folder, String name, int index, int botao) {

        String imagePath = String.format("src/main/resources/br/com/opusnet/projetoapiidoscrias/%s/Char_%s%02d.png", folder, name, index);

        Image image = imageCache.get(imagePath);
        if (image == null) {
            File file = new File(imagePath);
            if (file.exists()) {
                image = new Image(file.toURI().toString());
                imageCache.put(imagePath, image);
            } else {
                System.out.println("Imagem não encontrada: " + imagePath);
                return;
            }
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        switch (botao) {
            case 1:
                if (controller.b_char1 != null) {
                    controller.b_char1.setGraphic(imageView);
                }
                break;


            case 2:
                if (controller.b_char2 != null) {
                    controller.b_char2.setGraphic(imageView);
                }
                break;

            case 3:
                if (controller.b_char3 != null) {
                    controller.b_char3.setGraphic(imageView);
                }
                break;

            case 4:
                if (controller.b_char4 != null) {
                    controller.b_char4.setGraphic(imageView);
                }
                break;
        }
    }

    private void handleButtonPress() {
        if (!buttonProcessed) {
            if (controller.b_add.isPressed()) {
                buttonPressed = 1;
                buttonProcessed = true;
            } else if (controller.b_sub.isPressed()) {
                buttonPressed = 2;
                buttonProcessed = true;
            } else if (controller.b_mult.isPressed()) {
                buttonPressed = 3;
                buttonProcessed = true;
            } else if (controller.b_div.isPressed()) {
                buttonPressed = 4;
                buttonProcessed = true;
            }
        }

        // Reseta o estado de 'buttonProcessed' se nenhum botão de operação estiver pressionado
        if (!controller.b_add.isPressed() && !controller.b_sub.isPressed() && !controller.b_mult.isPressed() && !controller.b_div.isPressed()) {
            buttonProcessed = false;
        }
    }

    private void handlePersonSelect() {
        if (!personSelectionProcessed) {
            if (controller.b_char1.isPressed()) {
                setSelection(Double.parseDouble(controller.t_res1.getText()));
                personSelectionProcessed = true;  // Marca que a seleção foi processada
            } else if (controller.b_char2.isPressed()) {
                setSelection(Double.parseDouble(controller.t_res2.getText()));
                personSelectionProcessed = true;
            } else if (controller.b_char3.isPressed()) {
                setSelection(Double.parseDouble(controller.t_res3.getText()));
                personSelectionProcessed = true;
            } else if (controller.b_char4.isPressed()) {
                setSelection(Double.parseDouble(controller.t_res4.getText()));
                personSelectionProcessed = true;
            }
        }

        if (!controller.b_char1.isPressed() && !controller.b_char2.isPressed() && !controller.b_char3.isPressed() && !controller.b_char4.isPressed()) {
            personSelectionProcessed = false;
        }
    }

    private void setSelection(double value) {
        if (valueSelected[0] == 0) valueSelected[0] = value;
        else if (valueSelected[1] == 0) valueSelected[1] = value;
    }

    private void moveEnime() {
        double enemyY = controller.iv_enemy1.getY();

        if (sentinel && enemyY >= 50) sentinel = false;
        else if (!sentinel && enemyY <= -200) sentinel = true;

        controller.iv_enemy1.setY(enemyY + (sentinel ? 8 : -8));
    }

    public void checkAnswer() {
        if (valueSelected[0] != 0 && valueSelected[1] != 0 && buttonPressed != 0) {
            switch (buttonPressed) {
                case 1 -> respostPerson = valueSelected[0] + valueSelected[1];
                case 2 -> respostPerson = valueSelected[0] - valueSelected[1];
                case 3 -> respostPerson = valueSelected[0] * valueSelected[1];
                case 4 -> respostPerson = valueSelected[0] / valueSelected[1];
            }

            if (respostPerson != valueEnime) {
                LifeGame.lifeGame--;
                resetGameState();
            } else {
                win = true;
            }
        }
    }

    private void resetGameState() {
        respostPerson = 0;
        valueSelected = new double[]{0, 0};
        buttonPressed = 0;
    }

}
