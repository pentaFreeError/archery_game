package fr.ensicaen.ecole.archery.model.shooter;

import fr.ensicaen.ecole.archery.model.shop.Purchasable;

public class StabilizerEnhancementDecorator extends BowDecorator implements Purchasable {

    public StabilizerEnhancementDecorator(Shooter decoratedBow) {
        super(decoratedBow);
    }

    @Override
    public double getStabilizationFactor() {
        return 1.0;
    }

    @Override
    public int getPrice() {
        return 20;
    }    

    @Override
    public String getName() {
        return "Stabilizer";
    }
}
