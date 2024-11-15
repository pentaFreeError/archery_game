package fr.ensicaen.ecole.archery.view;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class BackgroundView {
        public BackgroundView(Pane root, String imagePath, double sizeX, double sizeY) {
            Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toString());
            ImageView backgroundImageView = new ImageView(backgroundImage);
            backgroundImageView.setFitWidth(sizeX);
            backgroundImageView.setFitHeight(sizeY);
            root.getChildren().add(backgroundImageView);
        }
}
