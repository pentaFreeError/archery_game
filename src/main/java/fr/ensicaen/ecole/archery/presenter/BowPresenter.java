package fr.ensicaen.ecole.archery.presenter;

import fr.ensicaen.ecole.archery.model.projectile.Arrow;
import fr.ensicaen.ecole.archery.model.structure.GameModel;
import fr.ensicaen.ecole.archery.model.exception.NoMoreArrowException;
import fr.ensicaen.ecole.archery.model.structure.Point3D;
import fr.ensicaen.ecole.archery.model.shooter.Shooter;

public class BowPresenter {

    IView _gameView;
    GameModel _model;

    double _scaleFactor;
    double _angleX;
    double _angleY;
    double _displayAngleX;

    private boolean _charging;
    private double _chargePower;

    public BowPresenter() {
        _chargePower = 0;
    }

    public void setInterface(IView gameView) {
        _gameView = gameView;
    }

    public void setModel(GameModel model) {
        _model = model;
    }
    
    public void setChargingValue(double power) {
        _chargePower = power;
    }
    
    public void bowMouseMoved(double mouseX, double mouseY, double bowX, double bowY) {
        double deltaX = mouseX - bowX;
        double deltaY = mouseY - bowY;
        double normalizedY = Math.max(0, Math.min(1, (600 - mouseY) / 600)); 
        double scaleFactor = 1 + (normalizedY * 0.2); 
        _scaleFactor = Math.max(1.0, Math.min(1.3, scaleFactor));  
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        _angleX = Math.atan2(deltaX, distance); 
        _angleY = Math.atan2(deltaY, distance); 

        _displayAngleX = Math.toDegrees(Math.atan2(deltaX, distance));
        _gameView.updateBow(_displayAngleX, _scaleFactor); 
    }    

    public void handleChargingBow(Shooter shooter) {
        if (_charging) {
            double MAX_CHARGE_POWER = 100;
            _chargePower = Math.min(_chargePower + 0.5 * shooter.getRopeFactor(), MAX_CHARGE_POWER);
            _gameView.updatePowerBar(_chargePower);
            _gameView.setBowPowerImage(_displayAngleX, _chargePower);
        }
    }

    public void bowMousePressed() {
        _gameView.deleteTrajectory();
        _charging = true;
        _gameView.updatePowerBar(_chargePower);        
        _chargePower = 0;
        _gameView.startChargingTimer();    
    }
    
    public int handleMouseReleased() {
        Arrow currentArrow;
        try {
            currentArrow = _model._player.shoot(_angleX, _angleY, _chargePower);
        } catch(NoMoreArrowException e) {
            _chargePower = 0;
            _charging = false;
            _gameView.stopChargingTimer();
            _gameView.updatePowerBar(_chargePower);
            _gameView.setBowPowerImage(_displayAngleX, _chargePower);
            _gameView.displayEndPopUp(_model._player.getScore());
            return _model._player.getScore();
        }    
        
        int score = _model._target.calculateScore(currentArrow);
        _model._player.addScore(score);

        double t = 0;
        Point3D position = currentArrow.getPositionAtTime(t);
        double max_size = 20;
        double size = (max_size/ (1 + t * 0.04));
        double DELTA_T = 0.2;

        if(score > 0) {
            while(position.z <= _model._target.getCenterPosition().z) {
                _gameView.drawCircle(position.x, position.y, size);
                t += DELTA_T;  
                position = currentArrow.getPositionAtTime(t);
                size = (max_size/ (1 + t * 0.04));
            }
        } else {
            while(position.equals(currentArrow.stopPosition(position.x, position.y, position.z))) {
                _gameView.drawCircle(position.x, position.y, size);
                t += DELTA_T;  
                position = currentArrow.getPositionAtTime(t);
                size = (max_size/ (1 + t * 0.04));
            }
        }

        _chargePower = 0;
        _charging = false;
        _gameView.stopChargingTimer();

        _gameView.changeScore(_model._player.getScore());
        _gameView.changeNumberArrows(_model._player.getNumberArrows());

        _gameView.setBowPowerImage(_displayAngleX, _chargePower);
        _gameView.updatePowerBar(_chargePower);
        return -1;
    }
}


