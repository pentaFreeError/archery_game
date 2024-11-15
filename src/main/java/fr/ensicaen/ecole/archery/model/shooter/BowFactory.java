package fr.ensicaen.ecole.archery.model.shooter;

public class BowFactory {
    public static Shooter createBow(BowType bowType) {
        switch (bowType) {
            case ROGER_PERE_ET_FILS_BOW:
                return new RogerPereEtFilsBow();
            default:
                return new DefaultBow();
        }
    }
}
