package fr.ensicaen.ecole.archery.model.target;

import fr.ensicaen.ecole.archery.model.projectile.Arrow;
import fr.ensicaen.ecole.archery.model.structure.Point3D;

public class ArcheryTarget implements Shootable {

    private final Point3D _targetCenterPosition;
    private double _radius;
    private final int _numberPart;

    public ArcheryTarget(Point3D position, double radius, int numberPart) {
        _targetCenterPosition = position;
        _radius = radius;
        _numberPart = numberPart;
    }
   
    private double distanceFromCenter(Point3D position) {
        double deltaX = (_targetCenterPosition.x - position.x) * (_targetCenterPosition.x - position.x);
        double deltaY = (_targetCenterPosition.y - position.y) * (_targetCenterPosition.y - position.y);
        
        return Math.sqrt(deltaY + deltaX); 
    }

    @Override
    public int calculateScore(Arrow arrow) {
        Point3D endPosition = arrow.getEndPosition();
        double targetYmax = _targetCenterPosition.y + _radius;
        double targetYmin = _targetCenterPosition.y - _radius;
        double targetXmax = _targetCenterPosition.x + _radius;
        double targetXmin = _targetCenterPosition.x - _radius;

        boolean validY = (endPosition.y <= targetYmax) && (endPosition.y >= targetYmin);
        boolean validX = (endPosition.x <= targetXmax) && (endPosition.x >= targetXmin);

        if(validX && validY) {
            double distance = distanceFromCenter(endPosition);
            if(distance > _radius) return 0;

            double partRadius = _radius / _numberPart;
            
            for (int i = 0; i < _numberPart; i++) {
                if (distance <= (i + 1) * partRadius) {
                    return _numberPart - i;
                }
            }
        }

        return 0;
    }

    @Override
    public Point3D getCenterPosition() {
        return _targetCenterPosition;
    }

    @Override
    public double getRadius() {
        return _radius;
    }

    @Override
    public void setRadius(double radius) {
        _radius = radius;
    }

}