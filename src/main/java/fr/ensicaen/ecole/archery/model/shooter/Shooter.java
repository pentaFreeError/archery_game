package fr.ensicaen.ecole.archery.model.shooter;

import fr.ensicaen.ecole.archery.model.projectile.Arrow;

public interface Shooter {
    void setAngleX(double angle);
    void setAngleY(double angle);
    void setPower(double power);
    void shoot(Arrow arrow);
    double getRopeFactor();
    double getZoomFactor();
    double getStabilizationFactor();
}
