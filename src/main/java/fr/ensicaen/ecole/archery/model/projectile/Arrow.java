package fr.ensicaen.ecole.archery.model.projectile;

import fr.ensicaen.ecole.archery.model.structure.Point3D;
import fr.ensicaen.ecole.archery.model.target.Shootable;

public class Arrow{
    private Point3D _position;
    private double _power;
    private double _angleX;
    private double _angleY;

    private final static double XMAX = 1080;
    private final static double YMAX = 720;
    private static double ZMAX;

    public Arrow(Point3D position, Shootable target){
        _position = position;
        _power = 0.0;
        _angleX = 0.0;
        _angleY = 0.0;
        ZMAX = target.getCenterPosition().z;
    }

    public void setAngleX(double angle) {
        _angleX = angle;
    }

    public void setAngleY(double angle) {
        _angleY = angle;
    }

    public void setPower(double power) {
        _power = power;
    }

    public void setPosition(Point3D position) {
        _position = position;
    }

    private double calculateTargetTime() {
        return (ZMAX - _position.z) / (_power * Math.cos(_angleY) * Math.cos(_angleX));
    }

    public Point3D stopPosition(double x, double y, double z) {
        if (y <= 0) y = 0;
        if (y >= YMAX) y = YMAX;

        if (x <= 0) x = 0;
        if (x >= XMAX) x = XMAX;

        return new Point3D(x, y, z);
    }

    public Point3D getPositionAtTime(double time) {

        double gravityConstant = 9.8;
        double x = _position.x + _power * Math.cos(_angleY) * Math.sin(_angleX) * time;
        double y = _position.y + 1.5 * _power * Math.sin(_angleY) * time + (0.5 * gravityConstant * time * time);
        double z = _position.z + _power * Math.cos(_angleY) * Math.cos(_angleX) * time;

        return new Point3D(x, y, z);
    }

    public Point3D getEndPosition() {
        double time = calculateTargetTime();
        return getPositionAtTime(time);
    }
}
