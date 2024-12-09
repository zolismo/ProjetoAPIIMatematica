package br.com.opusnet.projetoapiidoscrias.scene;

import br.com.opusnet.projetoapiidoscrias.controlls.GameLoop;
import br.com.opusnet.projetoapiidoscrias.controlls.screencontrol.SceneOneController;
import br.com.opusnet.projetoapiidoscrias.model.LifeGame;
import br.com.opusnet.projetoapiidoscrias.model.ScreemInterface;
import br.com.opusnet.projetoapiidoscrias.util.Updatable;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SceneOne extends Scene implements Updatable, ScreemInterface {
    private final GameLoop gameLoop;
    private final SceneOneController controller;
    private final Stage stage;

    private boolean controllerPersonOneAnimation = true;
    private boolean controllerPersonTwoAnimation = true;
    private boolean controllerPersonThreeAnimation = true;
    private boolean controllerPersonFourAnimation = true;

    private double valueEnime = 250;
    private final String equacionEnime = "x² - 500x + 62500 = 0";
    private double respostPerson;
    private double[] valueSelected = {0, 0};
    private int buttonPressed = 0;

    private String buttonSelected = "     ";



    private boolean animationTriangleProcessed = true;
    private int animationTriangle = 0;

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
        controller.t_life.setText("Vidas: " + String.valueOf(LifeGame.lifeGame));

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
        handleButtonPress();
        handlePersonSelect();
        checkAnswer();
        verifyConfirm();
        verifyDelet();
    }

    private boolean modifyLife = false;

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
            //   controller.t_level1.setVisible(true);
            //   controller.t_level1.setText("Você perdeu");
            gameLoop.stop();
        }


    }
    private void startAnimationCircle() {
        new Thread(() -> {
            while (controllerPersonOneAnimation) {
                person1Animation();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();                  }
            }
        }).start();
    }

    private void startAnimationSquare() {
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
    private void startAnimationLosangle() {
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
    private void startAnimationTriangle() {
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



    public void atualizeLife() {
        if (modifyLife == true) {
            controller.t_life.setText("Vidas: " + String.valueOf(LifeGame.lifeGame));
            modifyLife = false;
        }
    }


    private Image loadImage(String imagePath) {
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

    private void person3Animation() {
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


    private void updateImage(String folder, String name, int index, int botao) {

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


    private void handleButtonPress() {
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

    private void handlePersonSelect() {
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

    private void setSelection(double value) {
        if (valueSelected[0] == 0) valueSelected[0] = value;
        else if (valueSelected[1] == 0) valueSelected[1] = value;
    }


    public void checkAnswer() {
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
                gameLoop.stop();
                LifeGame.lifeGame--;
                resetGameState();
            } else {
                    Platform.runLater(()->{
                        gameLoop.stop();
                        System.out.println("Passou!!!!!");
                        URL url = null;
                        try {
                            url = new File("src/main/resources/br/com/opusnet/projetoapiidoscrias/level2.fxml").toURI().toURL();
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        }
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(url);
                        //SceneTwoController sceneTwoController = fxmlLoader.getController();
                        SceneTwo sceneTwo = null;

                        try {
                            sceneTwo = new SceneTwo(fxmlLoader.load(),stage,fxmlLoader.getController());
                            stage.setScene(sceneTwo);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            }

        }
    }

    private void resetGameState() {
        respostPerson = 0;
        valueSelected = new double[]{0, 0};
        buttonPressed = 0;
    }


    private boolean verifyConfirmController = true;
    private boolean confirm = false;

    public void verifyConfirm() {
        if (verifyConfirmController) {
            if (controller.b_confirm.isPressed()) {
                confirm = true;
                verifyConfirmController = false;
            }
        } else {
            verifyConfirmController = true;
        }
    }

    private boolean verifyDeletController = true;
    private boolean delet = false;

    public void verifyDelet() {
        if (verifyDeletController) {
            if (controller.b_delet.isPressed()) {
                delet = true;
                verifyDeletController = false;
            }
        } else {
            verifyDeletController = true;
        }
    }



    private void person1Animation() {
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
    private void person2Animation() {
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


    private void person4Animation() {
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


    private void visibleAnswerUser(){
        Platform.runLater(()->{
            controller.t_answer.setText(valueSelected[0]+ buttonSelected + valueSelected[1]);
        });
    }


}
