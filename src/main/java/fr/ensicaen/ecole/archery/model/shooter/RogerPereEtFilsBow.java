package fr.ensicaen.ecole.archery.model.shooter;

import fr.ensicaen.ecole.archery.model.shop.Purchasable;

public class RogerPereEtFilsBow extends AbstractShooter implements Purchasable {

    public RogerPereEtFilsBow() {
        this(0.0, 0.0, 0.0);
    }

    public RogerPereEtFilsBow(double power, double angleX, double angleY) {
        super(power, angleX, angleY);
    }

    @Override
    public double getRopeFactor() {
        return 5.0;
    }

    @Override
    public int getPrice() {
        return 40;
    }

    @Override
    public String getName() {
        return "Roger Bow";
    }
}