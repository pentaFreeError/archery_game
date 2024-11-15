package fr.ensicaen.ecole.archery.presenter;

import fr.ensicaen.ecole.archery.model.projectile.Arrow;
import fr.ensicaen.ecole.archery.model.structure.GameModel;
import java.util.ArrayList;


public class WelcomePagePresenter {
  
    GameModel _model;
    IView _gameView;
    
    public void setInterface(IView gameView) {
        _gameView = gameView;
    }

    public void setModel(GameModel model) {
        _model = model;
    }    

    public void handleHomeButton() {
        _gameView.setWelcomePageScene();
    }

    public void handleRetryButton() {
        _gameView.deleteTrajectory();
        _model._player.setScore(0);
        ArrayList<Arrow> arrows = _model._player.getArrows();
        int arrowToAdd = _model._initialNumberArrows - arrows.size();

        for(int i = 0; i < arrowToAdd; i++) {
            arrows.add(new Arrow(_model._player.getPosition(), _model._target));
        }

        _gameView.changeScore(0);
        _gameView.changeNumberArrows(arrows.size());
    }
}
