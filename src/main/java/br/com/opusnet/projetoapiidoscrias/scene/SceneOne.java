package br.com.opusnet.projetoapiidoscrias.scene;

import br.com.opusnet.projetoapiidoscrias.controlls.GameLoop;
import br.com.opusnet.projetoapiidoscrias.controlls.screencontrol.SceneOneController;
import br.com.opusnet.projetoapiidoscrias.model.LifeGame;
import br.com.opusnet.projetoapiidoscrias.model.ScreemInterface;
import br.com.opusnet.projetoapiidoscrias.util.Updatable;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
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
        System.out.println("Resposta calculada: " + respostPerson);
        handleButtonPress();
        handlePersonSelect();
        checkAnswer();
        verifyConfirm();
        verifyDelet();
    }

    private boolean modifyLife = false;

    @Override
    public void render() {
        person1Animation();
        person2Animation();
        person3Animation();
        person4Animation();
    //    animationEnime2();

        Platform.runLater(() -> {
            // animationBackground();
            atualizeLife();

            //animationEnime();
            //animationTriangle();
            animationCircle();
            // animationSquare();
            //animationLosangle();

            if (LifeGame.lifeGame == 0) {
                //   controller.t_level1.setVisible(true);
                //   controller.t_level1.setText("Você perdeu");
                gameLoop.stop();
            }

            if (win) {
                controller.t_level1.setVisible(true);
                controller.t_level1.setText("Você ganhou");
                gameLoop.stop();
            }

            moveEnime();
        });
    }

    public void atualizeLife() {
        if (modifyLife == true) {
            controller.t_life.setText("Vidas: " + String.valueOf(LifeGame.lifeGame));
            modifyLife = false;
        }
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
            updateImage("Char_Circulo", "CirculoSelecionado", animationCircle, 1);
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

            String enimePath = String.format("src/main/resources/br/com/opusnet/projetoapiidoscrias/Enemy_Boss/Enemy_Boss" + animationEnime + ".png");
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

        Platform.runLater(()->{


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

        if (sentinel && enemyY >= 10) sentinel = false;
        else if (!sentinel && enemyY <= -200) sentinel = true;

        controller.iv_enemy1.setY(enemyY + (sentinel ? 8 : -8));
    }

    public void checkAnswer() {
        if (valueSelected[0] != 0 && valueSelected[1] != 0 && buttonPressed != 0 && confirm) {
            confirm = false;
            modifyLife = true;
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

/*
    private boolean skyAnimationProssed = true;
    private int skyAnimation = 0;

    public void animationBackground() {

        if (skyAnimationProssed && skyAnimation < 21) {

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(20), event -> {
                animationSkyFrames();
            }));
                timeline.play();
                timeline.setCycleCount(Timeline.INDEFINITE);


        } else if (skyAnimation >= 21) {
            skyAnimationProssed = false;
            skyAnimation = 0;
        } else {
            skyAnimationProssed = true;
            skyAnimation = 0;
        }

    }

    public void animationSkyFrames(){
        String skyPath;
        if(skyAnimation<10)
            skyPath = String.format("src/main/resources/br/com/opusnet/projetoapiidoscrias/Ceu_Animacao/Ceu_Animado0" + skyAnimation + ".png");
        else
            skyPath = String.format("src/main/resources/br/com/opusnet/projetoapiidoscrias/Ceu_Animacao/Ceu_Animado" + skyAnimation + ".png");

        skyAnimation++;

        Image image = imageCache.get(skyPath);
        if (image == null) {
            File file = new File(skyPath);
            if (file.exists()) {
                image = new Image(file.toURI().toString());
                imageCache.put(skyPath, image);
            } else {
                System.out.println("Imagem não encontrada: " + skyPath);
                return;
            }
        }

        controller.i_sky.setImage(image);
        controller.i_sky.setFitWidth(controller.p_level1.getWidth());
        controller.i_sky.setFitHeight(controller.p_level1.getHeight());
        controller.i_sky.setPreserveRatio(false);
    }

 */

    private boolean controllerPersonOneAnimation = true;
    private boolean controllerPersonTwoAnimation = true;
    private boolean controllerPersonThreeAnimation = true;
    private boolean controllerPersonFourAnimation = true;

    private void person1Animation() {
        if (controllerPersonOneAnimation == true) {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    while (controllerPersonFourAnimation) {
                        try {

                            if (animationTriangleProcessed) {
                                if (animationTriangle < 32) {

                                    animationTriangle++;

                                    updateImage("Char_Circulo", "CirculoSelecionado", animationTriangle, 4);
                                } else {

                                    animationTriangleProcessed = false;
                                    animationTriangle = 0;
                                }
                            } else {

                                animationTriangleProcessed = true;
                                animationTriangle = 0;
                            }


                            Thread.sleep(50);
                        } catch (InterruptedException e) {

                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                    return null;
                }

            };
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
             controllerPersonOneAnimation = false;
        }
    }
    private void person2Animation() {
        if (controllerPersonTwoAnimation == true) {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    while (controllerPersonFourAnimation) {
                        try {

                            if (animationTriangleProcessed) {
                                if (animationTriangle < 57) {

                                    animationTriangle++;

                                    updateImage("Char_Quadrado", "QuadradoSelecionado", animationSquare, 2);
                                } else {

                                    animationTriangleProcessed = false;
                                    animationTriangle = 0;
                                }
                            } else {

                                animationTriangleProcessed = true;
                                animationTriangle = 0;
                            }


                            Thread.sleep(50);
                        } catch (InterruptedException e) {

                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                    return null;
                }

            };
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
            controllerPersonTwoAnimation = false;
        }
    }
    private void person3Animation() {
        if (controllerPersonThreeAnimation == true) {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    while (controllerPersonFourAnimation) {
                        try {

                            if (animationTriangleProcessed) {
                                if (animationTriangle < 3) {

                                    animationTriangle++;

                                    updateImage("Char_Losango", "Losango", animationLosangle, 3);
                                } else {

                                    animationTriangleProcessed = false;
                                    animationTriangle = 0;
                                }
                            } else {

                                animationTriangleProcessed = true;
                                animationTriangle = 0;
                            }


                            Thread.sleep(50);
                        } catch (InterruptedException e) {

                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                    return null;
                }
            };
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
             controllerPersonThreeAnimation = false;
        }
    }


    private void person4Animation() {
        if (!controllerPersonFourAnimation) {
            return;
        }
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {

                while (controllerPersonFourAnimation) {
                    try {

                        if (animationTriangleProcessed) {
                            if (animationTriangle < 29) {

                                animationTriangle++;

                                updateImage("Char_Triangulo", "TrianguloSelecionado", animationTriangle, 4);
                            } else {

                                animationTriangleProcessed = false;
                                animationTriangle = 0;
                            }
                        } else {

                            animationTriangleProcessed = true;
                            animationTriangle = 0;
                        }


                        Thread.sleep(50);
                    } catch (InterruptedException e) {

                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                return null;
            }
        };


        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }


    private boolean controllerEnimeAnimation = true;

    private void animationEnime2() {
        if (controllerEnimeAnimation == true) {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    if (animationEnimeProcessed && animationEnime < 7) {

                        String enimePath = String.format("src/main/resources/br/com/opusnet/projetoapiidoscrias/Enemy_Boss/Enemy_Boss" + animationEnime + ".png");
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

                    return null;
                }

            };
        }
    }


}
