package fr.ensicaen.ecole.archery.model.shooter;

import fr.ensicaen.ecole.archery.model.projectile.Arrow;

public abstract class AbstractShooter implements Shooter {

    private double _power;
    private double _angleX;
    private double _angleY;

    public AbstractShooter(double power, double angleX, double angleY) {
        _power = power;
        _angleX = angleX;
        _angleY = angleY;
    }

    @Override
    public void setAngleX(double angle) {
        _angleX = angle;
    }

    @Override
    public void setAngleY(double angle) {
        _angleY = angle;
    }

    @Override
    public void setPower(double power) {
        _power = power;
    }

    @Override
    public void shoot(Arrow arrow) {
        arrow.setAngleX(_angleX);
        arrow.setAngleY(_angleY);
        arrow.setPower(_power);
    }

    @Override
    public double getStabilizationFactor() {
        return 0.0;
    }

    @Override
    public double getZoomFactor() {
        return 1.0;
    }
}
