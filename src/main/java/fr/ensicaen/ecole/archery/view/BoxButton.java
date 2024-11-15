package fr.ensicaen.ecole.archery.view;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Objects;

public class BoxButton {
    private final Button _button;

    public BoxButton(Pane parent, double x, double y, double width, double height, String path) {
        Image iconImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));

        ImageView iconView = new ImageView(iconImage);
        iconView.setFitWidth(width);
        iconView.setFitHeight(height);

        _button = new Button();
        _button.setGraphic(iconView);

        _button.setLayoutX(x);
        _button.setLayoutY(y);

        _button.setPrefWidth(width);
        _button.setPrefHeight(height);

        _button.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-text-fill: white; -fx-border-color: #2C8F2F; -fx-border-width: 4; -fx-background-radius: 30; -fx-border-radius: 30;");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.DARKGREEN);
        dropShadow.setRadius(5);
        _button.setEffect(dropShadow);

        _button.setOnMouseEntered(event -> _button.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-text-fill: #7CFC00; -fx-border-color: #7CFC00; -fx-border-width: 4; -fx-background-radius: 30; -fx-border-radius: 30;"));
        _button.setOnMouseExited(event -> _button.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-text-fill: white; -fx-border-color: #2C8F2F; -fx-border-width: 4; -fx-background-radius: 30; -fx-border-radius: 30;"));

        _button.setOnMousePressed(event -> _button.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-text-fill: white; -fx-border-color: #2C8F2F; -fx-border-width: 4; -fx-background-radius: 30; -fx-border-radius: 30;"));
        _button.setOnMouseReleased(event -> _button.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-text-fill: white; -fx-border-color: #2C8F2F; -fx-border-width: 4; -fx-background-radius: 30; -fx-border-radius: 30;"));
    
        parent.getChildren().add(_button);
    }

    public Button getButton() {
        return _button;
    }
}

