package fr.ensicaen.ecole.archery.view;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomButtonView extends Button {

    public CustomButtonView(String text) {
        super(text);

        setFont(Font.font("Open Sans", FontWeight.BOLD, 32)); // Police "Open Sans" en gras

        setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-text-fill: white; -fx-border-color: #2C8F2F; -fx-border-width: 4; -fx-background-radius: 30; -fx-border-radius: 30;");
        setPrefSize(200, 60);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.DARKGREEN);
        dropShadow.setRadius(5);
        setEffect(dropShadow);

        setOnMouseEntered(event -> setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-text-fill: #7CFC00; -fx-border-color: #7CFC00; -fx-border-width: 4; -fx-background-radius: 30; -fx-border-radius: 30;"));
        setOnMouseExited(event -> setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-text-fill: white; -fx-border-color: #2C8F2F; -fx-border-width: 4; -fx-background-radius: 30; -fx-border-radius: 30;"));

        setOnMousePressed(event -> setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-text-fill: white; -fx-border-color: #2C8F2F; -fx-border-width: 4; -fx-background-radius: 30; -fx-border-radius: 30;"));
        setOnMouseReleased(event -> setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-text-fill: white; -fx-border-color: #2C8F2F; -fx-border-width: 4; -fx-background-radius: 30; -fx-border-radius: 30;"));
    }
}

