package fr.ensicaen.ecole.archery.model.shooter;

import fr.ensicaen.ecole.archery.model.shop.Purchasable;

public class ZoomEnhancementDecorator extends BowDecorator implements Purchasable {

    public ZoomEnhancementDecorator(Shooter decoratedBow) {
        super(decoratedBow);
    }

    @Override
    public double getZoomFactor() {
        return 2.0;
    }

    @Override
    public int getPrice() {
        return 20;
    }

    @Override
    public String getName() {
        return "Zoom";
    }
}
