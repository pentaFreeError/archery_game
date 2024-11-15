package fr.ensicaen.ecole.archery.model.player;

import fr.ensicaen.ecole.archery.model.projectile.Arrow;
import fr.ensicaen.ecole.archery.model.structure.Point3D;
import fr.ensicaen.ecole.archery.model.shooter.Shooter;

import java.util.ArrayList;

public class Human extends Player {
    public Human(Point3D position, Shooter shooter, ArrayList<Arrow> arrows){
        super(position, shooter, arrows);
    }

    @Override
    public Arrow setupShot(double angleX, double angleY, double power) {
        
        Arrow currentArrow = _arrows.get(0);
        currentArrow.setPosition(_position);

        _shooter.setAngleX(angleX);
        _shooter.setAngleY(angleY);
        _shooter.setPower(power);
        _shooter.shoot(currentArrow);

        return currentArrow;
    }
}
