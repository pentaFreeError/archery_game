package fr.ensicaen.ecole.archery.view;

import java.util.Objects;

import fr.ensicaen.ecole.archery.presenter.GamePresenter;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class WelcomePageView {
    private final CustomButtonView _playButton;
    private final CustomButtonView _shopButton;
    private final AnchorPane _welcomePane;
    private ImageView _logoView;
    private ImageView _background;
    private Scene _welcomeScene;
    private final GamePresenter _gamePresenter;
    private final Label _arrowLabel;
    private final HBox _easyBox;
    private final HBox _normalBox;
    private final HBox _hardBox;
    private final VBox _difficultyMenu;

    public WelcomePageView(GamePresenter gamePresenter) {
        _gamePresenter = gamePresenter;

        _welcomePane = new AnchorPane();
        _playButton = new CustomButtonView("Play");
        _shopButton = new CustomButtonView("Shop");

        loadBackground();
        loadLogo();

        _arrowLabel = new Label("→");
        _arrowLabel.setTextFill(Color.RED);
        _arrowLabel.setFont(new Font(30));
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(10);
        shadow.setOffsetX(5);
        shadow.setOffsetY(5);
        _arrowLabel.setEffect(shadow);
        _arrowLabel.setVisible(true);

        _welcomePane.getChildren().addAll(_background, _logoView);

        _normalBox = createBoxLevel("Normal", 100, 170, 300, 120);
        _hardBox = createBoxLevel("Hard", 100, 240, 300, 120);
        _easyBox = createBoxLevel("Easy", 100, 100, 300, 50);
        _difficultyMenu = createDifficultyBox();

        waitingToLaunchGame();
        addCopyrightText();
        createScene();
    }

    private void loadBackground() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/fr/ensicaen/ecole/archery/images/Forest.jpg")));
        _background = new ImageView(image);
        _background.setFitWidth(1080);
        _background.setFitHeight(720);
    }

    private void loadLogo() {
        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/fr/ensicaen/ecole/archery/images/logo.png")));
        _logoView = new ImageView(logo);
        _logoView.setFitWidth(300);
        _logoView.setFitHeight(300);
        _logoView.setX(540 - 150);
    }

    public void addCopyrightText() {
        Font minecraftFont = Font.loadFont(getClass().getResourceAsStream("/fr/ensicaen/ecole/archery/fonts/MinecraftRegular-Bmg3.otf"), 14);

        Label copyrightLabel = new Label("Copyright Roger pere et fils. Do not distribute!");
        copyrightLabel.setFont(minecraftFont);
        copyrightLabel.setStyle("-fx-text-fill: white;");
        AnchorPane.setRightAnchor(copyrightLabel, 0.0);
        AnchorPane.setBottomAnchor(copyrightLabel, 0.0);
        _welcomePane.getChildren().add(copyrightLabel);
    }

    public HBox createBoxLevel(String label, double x, double y, double width, double height) {
        Label levelLabel = new Label(label);
        Font font = Font.loadFont(getClass().getResourceAsStream("/fr/ensicaen/ecole/archery/fonts/MinecraftRegular-Bmg3.otf"), 36);
        levelLabel.setFont(font);
        levelLabel.setStyle("-fx-text-fill: white;");

        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(10);
        shadow.setOffsetX(5);
        shadow.setOffsetY(5);
        levelLabel.setEffect(shadow);

        HBox levelBox = new HBox(10);
        levelBox.setPrefWidth(width + 50);
        levelBox.setPrefHeight(height);
        levelBox.setLayoutX(x);
        levelBox.setLayoutY(y);

        levelBox.getChildren().addAll(_arrowLabel, levelLabel);

        levelLabel.setOnMouseClicked(event -> {
            showArrow(levelBox);
            _gamePresenter.levelPressed(label);
        });

        _welcomePane.getChildren().add(levelBox);
        return levelBox;
    }

    private void showArrow(HBox selectedLevel) {
        _arrowLabel.setVisible(true);

        if (_arrowLabel.getParent() != null) {
            ((HBox) _arrowLabel.getParent()).getChildren().remove(_arrowLabel);
        }

        selectedLevel.getChildren().add(0, _arrowLabel);
    }

    public void waitingToLaunchGame() {
        FadeTransition transition = createFadeTransition(_playButton, 1, 1, 0);
        FadeTransition transition1 = createFadeTransition(_logoView, 1, 1, 0);
        FadeTransition transition2 = createFadeTransition(_shopButton, 1, 1, 0);
        FadeTransition transition3 = createFadeTransition(_easyBox, 1, 1, 0);
        FadeTransition transition4 = createFadeTransition(_normalBox, 1, 1, 0);
        FadeTransition transition5 = createFadeTransition(_hardBox, 1, 1, 0);
        FadeTransition transition6 = createFadeTransition(_arrowLabel, 1, 1, 0);
        FadeTransition transition7 = createFadeTransition(_difficultyMenu, 1, 1, 0);


        _playButton.setOnAction(event -> {
            transition.play();
            transition1.play();
            transition2.play();
            transition3.play();
            transition4.play();
            transition5.play();
            transition6.play();
            transition7.play();
            transition7.setOnFinished(event1 -> _gamePresenter.handlePlayButton());
        }
        );
        _shopButton.setOnAction(event -> {
            transition.play();
            transition1.play();
            transition2.play();
            transition3.play();
            transition4.play();
            transition5.play();
            transition6.play();
            transition7.play();
            transition7.setOnFinished(event1 -> _gamePresenter.handleShopButton());
        }
        );

    }

    private FadeTransition createFadeTransition(Node node, double durationInSeconds, double fromValue, double toValue) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(durationInSeconds), node);
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        fadeTransition.setInterpolator(Interpolator.LINEAR); 
        return fadeTransition;
    }

    private void createScene() {
        AnchorPane.setTopAnchor(_playButton, 360.0 - 15.0);
        AnchorPane.setLeftAnchor(_playButton, 520.0 - 80.0);

        AnchorPane.setTopAnchor(_shopButton, 360.0 + 85.0);
        AnchorPane.setLeftAnchor(_shopButton, 520.0 - 80.0);

        _welcomePane.getChildren().add(_playButton);
        _welcomePane.getChildren().add(_shopButton);

        _welcomeScene = new Scene(_welcomePane, 1080, 720);
    }

    public void setWelcomePageScene(Stage stage) {
        _playButton.setOpacity(1.0);
        _logoView.setOpacity(1.0);
        _shopButton.setOpacity(1.0);
        _easyBox.setOpacity(1.0);
        _normalBox.setOpacity(1.0);
        _hardBox.setOpacity(1.0);
        _arrowLabel.setOpacity(1.0);
        _difficultyMenu.setOpacity(1.0);
        stage.setScene(_welcomeScene);
    }

    private VBox createDifficultyBox() {
        VBox difficultyBox = new VBox(20);
        difficultyBox.setPrefWidth(250);
        difficultyBox.setPrefHeight(150);
        difficultyBox.setLayoutX(100);
        difficultyBox.setLayoutY(100);

        difficultyBox.setStyle(
                "-fx-background-color: rgba(30, 30, 30, 0.8);"
                        + "-fx-border-color: #FFD700;"
                        + "-fx-border-width: 5;"
                        + "-fx-border-radius: 15;"
                        + "-fx-background-radius: 15;"
                        + "-fx-padding: 10;"
        );

        DropShadow boxShadow = new DropShadow();
        boxShadow.setColor(Color.DARKGOLDENROD);
        boxShadow.setRadius(15);
        boxShadow.setOffsetX(5);
        boxShadow.setOffsetY(5);
        difficultyBox.setEffect(boxShadow);

        difficultyBox.getChildren().addAll(_easyBox, _normalBox, _hardBox);

        _welcomePane.getChildren().add(difficultyBox);

        return difficultyBox;
    }
}


