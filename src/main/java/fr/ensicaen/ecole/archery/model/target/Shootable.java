package fr.ensicaen.ecole.archery.model.target;

import fr.ensicaen.ecole.archery.model.projectile.Arrow;
import fr.ensicaen.ecole.archery.model.structure.Point3D;

public interface Shootable {
    int calculateScore(Arrow arrow);
    Point3D getCenterPosition();
    double getRadius();
    void setRadius(double radius);
}