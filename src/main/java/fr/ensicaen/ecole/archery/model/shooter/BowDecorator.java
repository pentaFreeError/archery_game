package fr.ensicaen.ecole.archery.model.shooter;

import fr.ensicaen.ecole.archery.model.projectile.Arrow;

public abstract class BowDecorator implements Shooter {
    protected Shooter _decoratedBow;

    public BowDecorator(Shooter decoratedBow) {
        _decoratedBow = decoratedBow;
    }

    @Override
    public void shoot(Arrow arrow) {
        _decoratedBow.shoot(arrow);  
    }
    
    @Override
    public void setAngleX(double angle) {
        _decoratedBow.setAngleX(angle);
    }

    @Override
    public void setAngleY(double angle) {
        _decoratedBow.setAngleY(angle);
    }
    
    @Override
    public void setPower(double power) {
        _decoratedBow.setPower(power);
    }

    @Override
    public double getRopeFactor() {
        return _decoratedBow.getRopeFactor();
    }

    @Override
    public double getStabilizationFactor() {
        return _decoratedBow.getStabilizationFactor();
    }

    @Override
    public double getZoomFactor() {
        return _decoratedBow.getZoomFactor();
    }
}
