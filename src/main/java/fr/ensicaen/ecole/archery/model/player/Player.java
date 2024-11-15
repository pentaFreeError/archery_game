package fr.ensicaen.ecole.archery.model.player;

import fr.ensicaen.ecole.archery.model.projectile.Arrow;
import fr.ensicaen.ecole.archery.model.structure.Point3D;
import fr.ensicaen.ecole.archery.model.exception.NoMoreArrowException;
import fr.ensicaen.ecole.archery.model.shooter.Shooter;

import java.util.ArrayList;

public abstract class Player {
    protected Point3D _position;
    private int _score;
    protected Shooter _shooter;
    protected ArrayList<Arrow> _arrows;
    private Inventory _inventory;

    public Player(Point3D position, Shooter shooter, ArrayList<Arrow> arrows) {
        _position = position;
        _shooter = shooter;
        _arrows = arrows;
        _score = 0;
        _inventory = new Inventory();
    }

    public Arrow shoot(double angleX, double angleY, double power) throws NoMoreArrowException {

        if (_arrows.isEmpty()){
            throw new NoMoreArrowException();
        }

        Arrow currentArrow = setupShot(angleX, angleY, power);

        _arrows.remove(currentArrow);
        return currentArrow;
    }

    public abstract Arrow setupShot(double angleX, double angleY, double power);

    public void addScore(int score) {
        _score = _score + score;
    }

    public int getScore() {
        return _score;
    }

    public void setScore(int score) {
        _score = score;
    }

    public void setPosition(Point3D position) {
        _position = position;
    }

    public Point3D getPosition() {
        return _position;
    }

    public int getNumberArrows() {
        return _arrows.size();
    }

    public ArrayList<Arrow> getArrows() {
        return _arrows;
    }

    public void setArrow(ArrayList<Arrow> arrows) {
        _arrows = arrows;
    }

    public void setShooter(Shooter shooter) {
        _shooter = shooter;
    }

    public Inventory getInventory() {
        return _inventory;
    }

    public void setInventory(Inventory inventory) {
        _inventory = inventory;
    }

}
