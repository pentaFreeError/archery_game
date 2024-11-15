package fr.ensicaen.ecole.archery;

import fr.ensicaen.ecole.archery.presenter.GamePresenter;
import fr.ensicaen.ecole.archery.view.ArcheryGameView;
import fr.ensicaen.ecole.archery.view.ShopPageView;
import fr.ensicaen.ecole.archery.view.WelcomePageView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Archery Game");
        AnchorPane root = new AnchorPane();
        ArcheryGameView.drawBackground("/fr/ensicaen/ecole/archery/images/Forest.jpg", 1080, 720, root);

        Canvas canvas = new Canvas(1080, 720);
        root.getChildren().addAll(canvas);
        AnchorPane.setTopAnchor(canvas, 0.0);
        AnchorPane.setLeftAnchor(canvas, 0.0);
        Scene scene = new Scene(root, 1080, 720);

        GamePresenter gamePresenter = new GamePresenter();

        WelcomePageView welcomePageView = new WelcomePageView(gamePresenter);
        welcomePageView.setWelcomePageScene(primaryStage);

        ShopPageView shopPageView = new ShopPageView(gamePresenter, canvas);

        ArcheryGameView gameView = new ArcheryGameView(canvas, root, primaryStage, welcomePageView,shopPageView, scene);

        gamePresenter.setInterface(gameView);
        gameView.setPresenter(gamePresenter);

        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
