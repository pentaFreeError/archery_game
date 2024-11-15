package fr.ensicaen.ecole.archery.view;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import fr.ensicaen.ecole.archery.presenter.GamePresenter;

import java.util.Objects;

public class BowView {
    private final ImageView _bowPower0;
    private final ImageView _bowPower33;
    private final ImageView _bowPower66;
    private final ImageView _bowPower100;

    private final Pane _root;

    private final double _x;
    private final double _y;

    private final AnimationTimer _chargeTimer;
    private GamePresenter _gamePresenter; 

    private final double _initWidth;
    private final double _initHeight;
    private ImageView _currentImageView;

    public BowView(double x, double y, double width, double height, Pane root, Canvas canvas, GamePresenter gamePresenter, String type) {
        _root = root;
        _gamePresenter = gamePresenter;
        _initHeight = height;
        _initWidth = width;
        _x = x + width / 2.0;
        _y = y + height / 2.0;

        _bowPower0 = createImageView("/fr/ensicaen/ecole/archery/images/" + type + "_power_0.png");
        _bowPower33 = createImageView("/fr/ensicaen/ecole/archery/images/" + type + "_power_33.png");
        _bowPower66 = createImageView("/fr/ensicaen/ecole/archery/images/" + type + "_power_66.png");
        _bowPower100 = createImageView("/fr/ensicaen/ecole/archery/images/" + type + "_power_100.png");

        canvas.setOnMousePressed(this::handleMousePressed);
        canvas.setOnMouseReleased(this::handleMouseReleased);
        canvas.setOnMouseMoved(this::handleMouseMoved);

        root.getChildren().add(_bowPower0);
        _currentImageView = _bowPower0;

        _chargeTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                _gamePresenter.handleChargingBow();
            }
        };
    }

    public void setPresenter(GamePresenter gamePresenter) {
        _gamePresenter = gamePresenter;
    }

    private ImageView createImageView(String path) {
        Image image = new Image(Objects.requireNonNull(getClass().getResource(path)).toString());
        ImageView imageView = new ImageView(image);
        imageView.setX(_x - _initWidth / 2.0);
        imageView.setY(_y - _initHeight / 2.0);
        imageView.setFitHeight(_initHeight);
        imageView.setFitWidth(_initWidth);
        return imageView;
    }

    public void setBowPowerImage(double angle, double chargePower) {
        _root.getChildren().remove(_currentImageView);

        if(chargePower <= 0) {
            _currentImageView = _bowPower0;
        } else if(chargePower <= 33) {
            _currentImageView = _bowPower33;
        } else if(chargePower <= 66) {
            _currentImageView = _bowPower66;
        } else {
            _currentImageView = _bowPower100;
        }
        _currentImageView.setRotate(angle);

        if (!_root.getChildren().contains(_currentImageView)) {
            _root.getChildren().add(_currentImageView);
        }
    }

    public void handleMousePressed(MouseEvent event) {
        _gamePresenter.bowMousePressed();
    }

    public void handleMouseReleased(MouseEvent event) {

        _gamePresenter.handleMouseReleased();
    }

    public void stopChargingTimer() {
        _chargeTimer.stop();
    }

    public void startChargingTimer() {
        _chargeTimer.start();
    }

    public void updateBow(double displayAngleX, double scaleFactor) {
        _currentImageView.setFitHeight(_initHeight * scaleFactor);
        _currentImageView.setRotate(displayAngleX);
    }

    public void handleMouseMoved(MouseEvent event) {
        _gamePresenter.bowMouseMoved(event.getX(), event.getY(), _x, _y);
    }
}