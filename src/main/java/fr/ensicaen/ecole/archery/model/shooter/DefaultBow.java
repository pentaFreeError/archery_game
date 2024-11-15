package fr.ensicaen.ecole.archery.model.shooter;

import fr.ensicaen.ecole.archery.model.shop.Purchasable;

public class DefaultBow extends AbstractShooter implements Purchasable {

    public DefaultBow() {
        this(0.0, 0.0, 0.0);
    }

    public DefaultBow(double power, double angleX, double angleY) {
        super(power, angleX, angleY);
    }

    @Override
    public double getRopeFactor() {
        return 1.0;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public String getName() {
        return "Default Bow";
    }
}
