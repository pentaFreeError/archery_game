package fr.ensicaen.ecole.archery.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;

import java.util.Objects;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import javafx.stage.Stage;
import fr.ensicaen.ecole.archery.presenter.GamePresenter;

public class ShopPageView {

    private final GamePresenter _gamePresenter;
    private final AnchorPane _shopPane;
    private final Scene _shopScene;
    private ImageView _background;
    private final BoxView _moneyBox;
    private final Button _RogerBowBuyButton;
    private final Button _zoomBuyButton;
    private final Button _stabilizerBuyButton;
    private final Label _errorMessage;

    public ShopPageView(GamePresenter gamePresenter, Canvas canvas) {
        _gamePresenter = gamePresenter;
        _shopPane = new AnchorPane();

        _moneyBox = new BoxView(canvas, 250, 100, 430, 10, "Money");
        _moneyBox.changeValue(0);
        _errorMessage = createErrorMessage(200, 100, 36);

        loadBackground();
        _shopPane.getChildren().addAll(_background, canvas);

        drawHomeButton();
        addCopyrightText();

        Button _defaultBowBuyButton = createSellBox("/fr/ensicaen/ecole/archery/images/bow_power_100.png", "Default bow", 0, 70, 200, 200, 400, 200, 150);
        _RogerBowBuyButton = createSellBox("/fr/ensicaen/ecole/archery/images/roger_power_100.png", "Roger bow", 40, 320, 200, 200, 400, 200, 150);
        _zoomBuyButton = createSellBox("/fr/ensicaen/ecole/archery/images/icons8-zoom-100.png", "Zoom", 20, 570, 200, 200, 400, 150, 150);
        _stabilizerBuyButton =  createSellBox("/fr/ensicaen/ecole/archery/images/icons8-damper-64.png", "Stabilizer", 20, 820, 200, 200, 400, 100, 150);

        setSold(_defaultBowBuyButton);

        _RogerBowBuyButton.setOnAction(e -> _gamePresenter.handleRogerBowBuy());
        _zoomBuyButton.setOnAction(e -> _gamePresenter.handleZoomBuy());
        _stabilizerBuyButton.setOnAction(e -> _gamePresenter.handleStabilizerBuy());

        _shopScene = new Scene(_shopPane, 1080, 720);
    }

    private void drawHomeButton() {
        BoxButton _homeButton = new BoxButton(_shopPane, 0, 0, 50, 50, "/fr/ensicaen/ecole/archery/images/icons8-home-50.png");
        _homeButton.getButton().setOnAction(e -> _gamePresenter.handleHomeButton());
    }

    private Label createErrorMessage(double x, double y, int fontSize) {
        Label errorLabel = new Label("Not Enough money to buy this item");

        errorLabel.setTextFill(Color.RED);
        errorLabel.setFont(new Font(fontSize));
        errorLabel.setLayoutX(x);
        errorLabel.setLayoutY(y);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);
        dropShadow.setRadius(3);

        errorLabel.setEffect(dropShadow);

        return errorLabel;
    }

    private void loadBackground() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/fr/ensicaen/ecole/archery/images/Forest.jpg")));
        _background = new ImageView(image);
        _background.setFitWidth(1080);
        _background.setFitHeight(720);
    }

    private void addCopyrightText() {
        Font minecraftFont = Font.loadFont(getClass().getResourceAsStream("/fr/ensicaen/ecole/archery/fonts/MinecraftRegular-Bmg3.otf"), 14);
        Label copyrightLabel = new Label("Copyright Roger pere et fils. Do not distribute!");
        copyrightLabel.setFont(minecraftFont);
        copyrightLabel.setStyle("-fx-text-fill: white;");
        AnchorPane.setRightAnchor(copyrightLabel, 0.0);
        AnchorPane.setBottomAnchor(copyrightLabel, 0.0);
        _shopPane.getChildren().add(copyrightLabel);
    }

    public void setShopPageScene(Stage stage) {
        stage.setScene(_shopScene);
    }

    private Button createSellBox(String imagePath, String name, double price, double x, double y, double boxWidth,
                                 double boxHeight, double imageWidth, double imageHeight) {
        VBox sellBox = new VBox();
        sellBox.setLayoutX(x);
        sellBox.setLayoutY(y);
        sellBox.setPrefWidth(boxWidth);
        sellBox.setPrefHeight(boxHeight);
        sellBox.setSpacing(10);
        sellBox.setAlignment(Pos.CENTER);

        sellBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); "
                + "-fx-border-color: #00FF00; "
                + "-fx-border-width: 3px; "
                + "-fx-border-radius: 15px; "
                + "-fx-background-radius: 15px;");

        sellBox.setEffect(new DropShadow(10, Color.GRAY));

        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(imageWidth);
        imageView.setFitHeight(imageHeight);

        Label nameLabel = new Label(name);
        nameLabel.setFont(new Font("Arial", 18));
        nameLabel.setTextAlignment(TextAlignment.CENTER);
        nameLabel.setTextFill(Color.WHITE);

        Label priceLabel = new Label(String.format("$%.2f", price));
        priceLabel.setFont(new Font("Arial", 16));
        priceLabel.setTextFill(Color.GREEN);
        priceLabel.setTextAlignment(TextAlignment.CENTER);

        Button buyButton = new Button("BUY");
        buyButton.setStyle("-fx-background-color: #00FF00; -fx-text-fill: black;");
        buyButton.setFont(new Font("Arial", 14));

        sellBox.getChildren().addAll(imageView, nameLabel, priceLabel, buyButton);

        _shopPane.getChildren().add(sellBox);
        return buyButton;
    }

    public void changeMoneyValue(int value) {
        _moneyBox.changeValue(value);
    }

    public void setSoldRogerBow() {
        setSold(_RogerBowBuyButton);
    }

    public void setSoldZoom() {
        setSold(_zoomBuyButton);
    }

    public void setSoldStabilizer() {
        setSold(_stabilizerBuyButton);
    }

    private void setSold(Button button) {
        button.setText("OWNED");
        button.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        button.setOnAction(null);
    }

    public void displayShopErrorMessage() {
        if(!_shopPane.getChildren().contains(_errorMessage)) {
            _shopPane.getChildren().add(_errorMessage);
        }
    }

    public void hideShopErrorMessage() {
        if(_shopPane.getChildren().contains(_errorMessage)) {
            _shopPane.getChildren().remove(_errorMessage);
        }
    }
}

