package br.com.opusnet.projetoapiidoscrias.scene;

import br.com.opusnet.projetoapiidoscrias.controlls.GameLoop;
import br.com.opusnet.projetoapiidoscrias.controlls.screencontrol.SceneOneController;
import br.com.opusnet.projetoapiidoscrias.model.LifeGame;
import br.com.opusnet.projetoapiidoscrias.model.ScreemInterface;
import br.com.opusnet.projetoapiidoscrias.util.Updatable;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractScene extends Scene implements Updatable, ScreemInterface {
    protected GameLoop gameLoop = new GameLoop();
    protected final SceneOneController controller;
    protected final Stage stage;

    protected boolean controllerPersonOneAnimation = true;
    protected boolean controllerPersonTwoAnimation = true;
    protected boolean controllerPersonThreeAnimation = true;
    protected boolean controllerPersonFourAnimation = true;

    protected double valueEnime;
    protected final String equacionEnime;
    protected double respostPerson;
    protected double[] valueSelected = {0, 0};
    protected int buttonPressed = 0;

    protected String buttonSelected = "     ";

    protected boolean animationTriangleProcessed = true;
    protected int animationTriangle = 0;

    protected boolean animationLosangreProcessed = true;
    protected int animationLosangle = 0;

    protected boolean animationSquareProcessed = true;
    protected int animationSquare = 0;

    protected boolean animationCircleProcessed = true;
    protected int animationCircle = 0;


    protected boolean buttonProcessed = false;
    protected boolean personSelectionProcessed = false;
    private String path;


    protected Map<String, Image> imageCache = new HashMap<>();

    public AbstractScene(Parent root, Stage stage, SceneOneController controller, String equacionEnime, double valueEnime, String value1,String value2,String value3,String value4) {
        super(root);
        this.path = path;
        this.stage = stage;
        this.controller = controller;
        this.equacionEnime = equacionEnime;
        this.valueEnime = valueEnime;

        controller.t_equacao.setText(equacionEnime);
        controller.t_life.setText("Vidas: " + String.valueOf(LifeGame.lifeGame));

        setValuesPerson(value1,value2,value3,value4);
        controller.t_equacao.setText(equacionEnime);
        gameLoop = new GameLoop(this);
        new Thread(gameLoop).start();
    }

    protected void setValuesPerson(String value1, String value2,String value3,String value4) {
        controller.t_res1.setText(value1);
        controller.t_res2.setText(value2);
        controller.t_res3.setText(value3);
        controller.t_res4.setText(value4);
    }

    @Override
    public void update() {
        System.out.println("Value 1: " + valueSelected[0]);
        System.out.println("Value 2: " + valueSelected[1]);
        System.out.println("Button Pressed: " + buttonPressed);
        handleButtonPress();
        handlePersonSelect();
        checkAnswer();
        verifyConfirm();
        verifyDelet();
    }

    protected boolean modifyLife = false;

    @Override
    public void render() {

        visibleAnswerUser();

        controller.b_char1.setOnMouseEntered((e) -> {
            controllerPersonOneAnimation = true;
            animationCircleProcessed = true;
            animationCircle = 0;
            startAnimationCircle();
        });

        controller.b_char1.setOnMouseExited((e) -> {
            controllerPersonOneAnimation = false;
            animationCircleProcessed = false;
        });


        controller.b_char2.setOnMouseEntered((e) -> {
            controllerPersonTwoAnimation = true;
            animationSquareProcessed = true;
            animationSquare = 0;
            startAnimationSquare();

        });

        controller.b_char2.setOnMouseExited((e) -> {
            controllerPersonTwoAnimation = false;
            animationSquareProcessed = false;
        });

        controller.b_char3.setOnMouseEntered((e) -> {
            controllerPersonThreeAnimation = true;
            animationLosangreProcessed = true;
            animationLosangle = 0;
            startAnimationLosangle();
        });

        controller.b_char3.setOnMouseExited((e) -> {

            controllerPersonThreeAnimation = false;
            animationLosangreProcessed = false;
        });

        controller.b_char4.setOnMouseEntered((e) -> {

            controllerPersonThreeAnimation = true;
            animationLosangreProcessed = true;
            animationLosangle = 0;
            startAnimationTriangle();
        });

        controller.b_char4.setOnMouseExited((e) -> {

            controllerPersonThreeAnimation = false;
            animationLosangreProcessed = false;
        });

        atualizeLife();


        if (LifeGame.lifeGame == 0) {
            gameLoop.stop();
            Platform.runLater(()->{
                
            });
        }


    }
    protected void startAnimationCircle() {
        new Thread(() -> {
            while (controllerPersonOneAnimation) {
                person1Animation();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    protected void startAnimationSquare() {
        new Thread(() -> {
            while (controllerPersonTwoAnimation) {
                person2Animation();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
    protected void startAnimationLosangle() {
        new Thread(() -> {
            while (controllerPersonThreeAnimation) {
                person3Animation();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
    protected void startAnimationTriangle() {
        new Thread(() -> {
            while (controllerPersonFourAnimation) {
                person4Animation();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }



    protected void atualizeLife() {
        if (modifyLife == true) {
            controller.t_life.setText("Vidas: " + String.valueOf(LifeGame.lifeGame));
            modifyLife = false;
        }
    }


    protected Image loadImage(String imagePath) {
        Image image = imageCache.get(imagePath);
        if (image == null) {
            File file = new File(imagePath);
            if (file.exists()) {
                image = new Image(file.toURI().toString());
                if (imageCache.size() >= 20) {
                    imageCache.entrySet().iterator().next();
                }
                imageCache.put(imagePath, image);
            } else {
                System.out.println("Imagem não encontrada: " + imagePath);
            }
        }
        return image;
    }

    protected void person3Animation() {
        if (controllerPersonThreeAnimation) {
            if (animationLosangreProcessed) {
                if (animationLosangle < 3) {
                    animationLosangle++;
                    updateImage("Char_Losango", "Losango", animationLosangle, 3);
                } else {
                    animationLosangreProcessed = false;
                    animationLosangle = 0;
                }
            }
        }
    }


    protected void updateImage(String folder, String name, int index, int botao) {

        String imagePath = String.format("src/main/resources/br/com/opusnet/projetoapiidoscrias/%s/Char_%s%02d.png", folder, name, index);

        Image image = loadImage(imagePath);

        if (image == null) {
            return;
        }


        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        Platform.runLater(() -> {

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
        });
    }


    protected void handleButtonPress() {
        if (!buttonProcessed) {
            if (controller.b_add.isPressed()) {
                buttonPressed = 1;
                buttonProcessed = true;
                buttonSelected = " + ";
            } else if (controller.b_sub.isPressed()) {
                buttonPressed = 2;
                buttonProcessed = true;
                buttonSelected = " - ";
            } else if (controller.b_mult.isPressed()) {
                buttonPressed = 3;
                buttonProcessed = true;
                buttonSelected = " x ";
            } else if (controller.b_div.isPressed()) {
                buttonPressed = 4;
                buttonProcessed = true;
                buttonSelected = " / ";
            }
        }

        if (!controller.b_add.isPressed() && !controller.b_sub.isPressed() && !controller.b_mult.isPressed() && !controller.b_div.isPressed()) {
            buttonProcessed = false;
        }
    }

    protected void handlePersonSelect() {
        if (!personSelectionProcessed) {
            if (controller.b_char1.isPressed()) {
                System.out.println("Pessoa1Selecionada");
                setSelection(Double.parseDouble(controller.t_res1.getText()));
                personSelectionProcessed = true;
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

    protected void setSelection(double value) {
        if (valueSelected[0] == 0) valueSelected[0] = value;
        else if (valueSelected[1] == 0) valueSelected[1] = value;
    }


    protected void checkAnswer() {
        if (valueSelected[0] != 0 && valueSelected[1] != 0 && buttonPressed != 0 && confirm) {
            System.out.println("bifewbfilweqol");
            confirm = false;
            modifyLife = true;
            switch (buttonPressed) {
                case 1 -> respostPerson = valueSelected[0] + valueSelected[1];
                case 2 -> respostPerson = valueSelected[0] - valueSelected[1];
                case 3 -> respostPerson = valueSelected[0] * valueSelected[1];
                case 4 -> respostPerson = valueSelected[0] / valueSelected[1];
            }
            System.out.println("Resposta calculada: " + respostPerson);

            if (respostPerson != valueEnime) {
                LifeGame.lifeGame--;
                resetGameState();
            } else {
                Platform.runLater(()->{
                    gameLoop.stop();

                    FadeTransition ft = new FadeTransition();
                    ft.setDuration(Duration.millis(1000));
                    ft.setNode(controller.ac_start);
                    ft.setInterpolator(Interpolator.EASE_BOTH);
                    ft.setFromValue(1.0);
                    ft.setToValue(0.0);
                    ft.setOnFinished((ActionEvent event) ->{
                        setChangeScene();
                    });
                    ft.play();
                });
            }

        }
    }

    protected void resetGameState() {
        buttonSelected = "   ";
        respostPerson = 0;
        buttonPressed = 0;
        personSelectionProcessed = false;
        buttonProcessed = false;
        valueSelected = new double[]{0, 0};
        verifyConfirmController = true;
    }

    protected boolean verifyConfirmController = true;
    protected boolean confirm = false;

    protected void verifyConfirm() {
        if (verifyConfirmController) {
            if (controller.b_confirm.isPressed()) {
                confirm = true;
                verifyConfirmController = false;
            }
        } else {
            verifyConfirmController = true;
        }
    }

    protected boolean verifyDeletController = true;
    protected void verifyDelet() {
        if (verifyDeletController) {
            if (controller.b_delet.isPressed()) {
                resetGameState();
                verifyDeletController = false;
            }
        } else {
            verifyDeletController = true;
        }
    }



    protected void person1Animation() {
        if (controllerPersonOneAnimation) {
            if (animationCircleProcessed) {
                if (animationCircle < 32) {
                    animationCircle++;
                    updateImage("Char_Circulo", "CirculoSelecionado", animationCircle, 1);
                } else {
                    animationCircleProcessed = false;
                    animationCircle = 0;
                }
            }
        }
    }
    protected void person2Animation() {
        if (controllerPersonTwoAnimation) {
            if (animationSquareProcessed) {
                if (animationSquare < 57) {
                    animationSquare++;
                    updateImage("Char_Quadrado", "QuadradoSelecionado", animationSquare, 2);
                } else {
                    animationSquareProcessed = false;
                    animationSquare = 0;
                }
            }
        }
    }


    protected void person4Animation() {
        if (controllerPersonFourAnimation) {
            if (animationTriangleProcessed) {
                if (animationTriangle < 29) {
                    animationTriangle++;
                    updateImage("Char_Triangulo", "TrianguloSelecionado", animationTriangle, 4);
                } else {
                    animationTriangleProcessed = false;
                    animationTriangle = 0;
                }
            }
        }
    }


    protected void visibleAnswerUser(){
        Platform.runLater(()->{
            controller.t_answer.setText(valueSelected[0]+ buttonSelected + valueSelected[1]);
        });
    }

    public abstract void setChangeScene();

}
