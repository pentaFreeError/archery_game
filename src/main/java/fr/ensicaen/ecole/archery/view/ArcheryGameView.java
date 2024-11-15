package fr.ensicaen.ecole.archery.view;

import fr.ensicaen.ecole.archery.data.Map;
import fr.ensicaen.ecole.archery.presenter.GamePresenter;
import fr.ensicaen.ecole.archery.presenter.IView;
import java.util.ArrayList;
import java.util.Objects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


public class ArcheryGameView implements IView {
    private Canvas _canvas;
    private BoxView _scoreView;
    private BoxView _nbArrowsView;
    private BowView _bowView;
    private GamePresenter _gamePresenter;
    private Pane _root;
    private PowerBarView _powerBar;
    private final WelcomePageView _welcomePageView;
    private final ShopPageView _shopPageView;
    private final Stage _primaryStage;
    private Scene _scene;
    private StackPane _overlay;
    private final Image _cercleImage;
    private final ArrayList<ImageView> _trajectoryImages;


    public ArcheryGameView(Canvas canvas, Pane root, Stage primaryStage, WelcomePageView welcomePageView, ShopPageView shopPageView, Scene scene) {
        _canvas = canvas;
        _root = root;
        _welcomePageView = welcomePageView;
        _primaryStage = primaryStage;
        _scene = scene;
        _cercleImage = new Image(Objects.requireNonNull(getClass().getResource("/fr/ensicaen/ecole/archery/images/image3.png")).toString());
        _trajectoryImages = new ArrayList<>();
        _shopPageView = shopPageView;
    }
    
    @Override
    public void setPresenter(GamePresenter gamePresenter) {
        _gamePresenter = gamePresenter;
    }
    
    @Override
    public void drawCircle(double x, double y, double size) {
        ImageView imageView = new ImageView(_cercleImage);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        imageView.setX(x - size / 2);
        imageView.setY(y - size / 2);
        imageView.setMouseTransparent(true);
        _trajectoryImages.add(imageView);
        _root.getChildren().add(imageView);
    }

    @Override
    public void deleteTrajectory() {
        for(ImageView image : _trajectoryImages) {
            _root.getChildren().remove(image);
        }
    }


        @Override
    public void changeScore(int newScore) {
        _scoreView.changeValue(newScore);
    }
    
    @Override
    public void drawTarget(double centerX, double centerY, double radius, int numberOfZones) {
        ArcheryTargetView _targetView = new ArcheryTargetView(_canvas, centerX, centerY, radius, numberOfZones);
        _targetView.drawTarget();
    }
    
    @Override
    public void drawScoreZone(double width, double height, double x, double y) {
        _scoreView = new BoxView(_canvas, width, height, x, y, "Score");
        _scoreView.drawRectangle();
        _scoreView.changeValue(0);
    }

    @Override
    public void drawNumberArrows(double width, double height, double x, double y) {
        _nbArrowsView = new BoxView(_canvas, width, height, x, y, "Arrows");
        _nbArrowsView.drawRectangle();
        _nbArrowsView.changeValue(0);
    }

    @Override
    public void drawBow(double width, double height, double x, double y, String type) {
        _bowView = new BowView(x, y, width, height, _root, _canvas, _gamePresenter, type);
    }

    @Override
    public void drawHomeButton() {
        BoxButton _homeButton = new BoxButton(_root, 20, 650, 30, 30, "/fr/ensicaen/ecole/archery/images/icons8-home-50.png");
        _homeButton.getButton().setOnAction(event -> _gamePresenter.handleHomeButton());
    }

    @Override
    public void drawRetryButton() {
        BoxButton _retryButton = new BoxButton(_root, 100, 650, 30, 30, "/fr/ensicaen/ecole/archery/images/retry.png");
        _retryButton.getButton().setOnAction(event -> _gamePresenter.handleRetryButton());
    }

    @Override
    public void changeNumberArrows(int nbArrows) {
        _nbArrowsView.changeValue(nbArrows);
    }

    @Override
    public void drawPowerBar(double width, double height, double x, double y) {
        _powerBar = new PowerBarView(width, height, x, y, _canvas);
    }

    @Override
    public void updateBow(double angleX, double scaleFactor) {
        _bowView.updateBow(angleX, scaleFactor);
    }

    @Override
    public void updatePowerBar(double power) {
        _powerBar.setPower(power);
    }

    @Override
    public void stopChargingTimer() {
        _bowView.stopChargingTimer();
    }
    
    @Override
    public void startChargingTimer() {
        _bowView.startChargingTimer();
    }

    @Override
    public void setBowPowerImage(double angle, double chargePower) {
        _bowView.setBowPowerImage(angle, chargePower);
    }

    @Override
    public void setWelcomePageScene() {
        _welcomePageView.setWelcomePageScene(_primaryStage);
    }

    public static void drawBackground(String imagePath, double sizeX, double sizeY, Pane root) {
        new BackgroundView(root, imagePath, sizeX, sizeY); 
    }

    @Override
    public void displayGameView(Map map, String bowType, double zoomFactor) {
        _root = new AnchorPane();
        drawBackground("/fr/ensicaen/ecole/archery/images/Forest.jpg", 1080, 720, _root);
        _canvas = new Canvas(1080, 720);
        _root.getChildren().addAll(_canvas);
        AnchorPane.setTopAnchor(_canvas, 0.0);
        AnchorPane.setLeftAnchor(_canvas, 0.0);
    
        _scene = new Scene(_root, 1080, 720);

        drawNumberArrows(map.box.nbArrows.width, map.box.nbArrows.height, map.box.nbArrows.position.x, map.box.nbArrows.position.y);
        drawScoreZone(map.box.scoreZone.width, map.box.scoreZone.height, map.box.scoreZone.position.x, map.box.scoreZone.position.y);
        drawTarget(map.target.position.x, map.target.position.y, map.target.radius * zoomFactor, map.target.nbPart);
        drawPowerBar(map.bow.powerBar.width, map.bow.powerBar.height, map.bow.powerBar.position.x, map.bow.powerBar.position.y);
        drawBow(map.bow.width, map.bow.height, map.bow.position.x, map.bow.position.y, bowType);
        _bowView.setPresenter(_gamePresenter);
        drawHomeButton();
        drawRetryButton();
        _primaryStage.setScene(_scene);
    }

    @Override
    public void displayEndPopUp(int score) {
        VBox completePopupLayout = new VBox(20);
        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(0, 0, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        completePopupLayout.setBackground(background);
        
        HBox popupLayout = new HBox(10);
        popupLayout.setAlignment(Pos.CENTER);
        completePopupLayout.setAlignment(Pos.CENTER);

        Text gameOverText = new Text("Score : " + score);
        gameOverText.setFill(Color.GREEN);
        gameOverText.setFont(Font.loadFont(getClass().getResourceAsStream("/fr/ensicaen/ecole/archery/fonts/MinecraftRegular-Bmg3.otf"), 42));
        gameOverText.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");

        TextFlow textFlow = new TextFlow(gameOverText);
        textFlow.setStyle("-fx-text-alignment: center;");

        BoxButton boxButton1 = new BoxButton(popupLayout, 0, 0, 50, 50, "/fr/ensicaen/ecole/archery/images/icons8-home-50.png");
        boxButton1.getButton().setOnAction(event -> {_gamePresenter.handleHomeButton();_root.getChildren().remove(_overlay);});
        BoxButton boxButton2 = new BoxButton(popupLayout, 200, 0, 50, 50, "/fr/ensicaen/ecole/archery/images/retry.png");
        boxButton2.getButton().setOnAction(event -> {_gamePresenter.handleRetryButton();_root.getChildren().remove(_overlay);});

        completePopupLayout.getChildren().addAll(textFlow, popupLayout);

        _overlay = new StackPane();
        _overlay.prefWidthProperty().bind(_root.widthProperty());
        _overlay.prefHeightProperty().bind(_root.heightProperty());
        StackPane.setAlignment(completePopupLayout, Pos.CENTER);
        _overlay.getChildren().add(completePopupLayout);
        _root.getChildren().add(_overlay);
    }

    @Override
    public void setShopPage() {
        _shopPageView.setShopPageScene(_primaryStage);
    }

    @Override
    public void changeMoneyValue(int value) {
        _shopPageView.changeMoneyValue(value);
    }

    @Override
    public void displayShopErrorMessage() {
        _shopPageView.displayShopErrorMessage();
    }

    @Override
    public void hideShopErrorMessage() {
        _shopPageView.hideShopErrorMessage();
    }

    @Override
    public void setSoldRogerBow() {
        _shopPageView.setSoldRogerBow();
    }

    @Override
    public void setSoldZoom() {
        _shopPageView.setSoldZoom();
    }

    @Override
    public void setSoldStabilizer() {
        _shopPageView.setSoldStabilizer();
    }

}
