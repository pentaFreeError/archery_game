package fr.ensicaen.ecole.archery.model.shooter;

import fr.ensicaen.ecole.archery.model.projectile.Arrow;
import fr.ensicaen.ecole.archery.model.structure.Point3D;
import fr.ensicaen.ecole.archery.model.target.ArcheryTarget;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DefaultBowTest {
   
   @Test
    public void closeShootDefaultBow() {
        DefaultBow defaultBow = new DefaultBow();
        Point3D arrowPosition = new Point3D(0, 0, 0);
        Arrow arrow = new Arrow(arrowPosition, new ArcheryTarget(arrowPosition, 0, 0)); 
        defaultBow.shoot(arrow);
        Point3D position = arrow.getPositionAtTime(0);

        assertEquals(arrowPosition.x, position.x);
        assertEquals(arrowPosition.y, position.y);
        assertEquals(arrowPosition.z, position.z);
        defaultBow.setAngleX(10);
        defaultBow.setAngleY(10);
        defaultBow.setPower(100);
        defaultBow.shoot(arrow);

        position = arrow.getPositionAtTime(1);
        double x = 100 * Math.cos(10) * Math.sin(10) + defaultBow.getStabilizationFactor();
        double y = 1.5 * 100 * Math.sin(10) + (0.5 * 9.8);
        double z = 100 * Math.cos(10) * Math.cos(10) * defaultBow.getZoomFactor();

        assertEquals(x, position.x);
        assertEquals(y, position.y);
        assertEquals(z, position.z);
   }

    @Test
    public void farShootDefaultBow() {
        DefaultBow defaultBow = new DefaultBow(100, 45, 45);

        Point3D arrowPosition = new Point3D(0, 0, 0);
        Arrow arrow = new Arrow(arrowPosition, new ArcheryTarget(arrowPosition, 0, 0)); 
        defaultBow.shoot(arrow);
        Point3D position = arrow.getPositionAtTime(10);

        double x = 100 * Math.cos(45) * Math.sin(45) * 10 + defaultBow.getStabilizationFactor();
        double y = 1.5 * 100 * Math.sin(45) * 10 + (0.5 * 9.8 * 10 * 10);
        double z = 100 * Math.cos(45) * Math.cos(45) * defaultBow.getZoomFactor() * 10;

        assertEquals(x, position.x);
        assertEquals(y, position.y);
        assertEquals(z, position.z);
    }

    @Test
    public void testRogerBowIsBetter() {
        DefaultBow defaultBow = (DefaultBow)BowFactory.createBow(BowType.DEFAULT_BOW);
        RogerPereEtFilsBow rogerBow = (RogerPereEtFilsBow)BowFactory.createBow(BowType.ROGER_PERE_ET_FILS_BOW);
        assertTrue(rogerBow.getRopeFactor() > defaultBow.getRopeFactor());
        assertTrue(rogerBow.getPrice() > defaultBow.getPrice());
        assertNotEquals(rogerBow.getName(), defaultBow.getName());
    }

    @Test
    public void testTrajectoryAfterShoot() {
        DefaultBow defaultBow = new DefaultBow(100, 45, 45);

        Point3D arrowPosition = new Point3D(0, 0, 0);
        Arrow arrow = new Arrow(arrowPosition, new ArcheryTarget(arrowPosition, 0, 0)); 
        defaultBow.shoot(arrow);
        
        for(int i = 0; i < 100; i++) {
            Point3D position = arrow.getPositionAtTime(i);
            double x = 100 * Math.cos(45) * Math.sin(45) * i + defaultBow.getStabilizationFactor();
            double y = 1.5 * 100 * Math.sin(45) * i + (0.5 * 9.8 * i * i);
            double z = 100 * Math.cos(45) * Math.cos(45) * defaultBow.getZoomFactor() * i;

            assertEquals(x, position.x);
            assertEquals(y, position.y);
            assertEquals(z, position.z);
        }
    }

    @Test
    public void testTrajectoryNegativeAngle() {
        DefaultBow defaultBow = new DefaultBow(100, -45, -45);

        Point3D arrowPosition = new Point3D(0, 0, 0);
        Arrow arrow = new Arrow(arrowPosition, new ArcheryTarget(arrowPosition, 0, 0)); 
        defaultBow.shoot(arrow);
        
        for(int i = 0; i < 100; i++) {
            Point3D position = arrow.getPositionAtTime(i);
            double x = 100 * Math.cos(-45) * Math.sin(-45) * i + defaultBow.getStabilizationFactor();
            double y = 1.5 * 100 * Math.sin(-45) * i + (0.5 * 9.8 * i * i);
            double z = 100 * Math.cos(-45) * Math.cos(-45) * defaultBow.getZoomFactor() * i;

            assertEquals(x, position.x);
            assertEquals(y, position.y);
            assertEquals(z, position.z);
        }
    }

    @Test
    public void testTrajectoryDifferentAngles() {
        DefaultBow defaultBow = new DefaultBow(100, -45, 45);

        Point3D arrowPosition = new Point3D(0, 0, 0);
        Arrow arrow = new Arrow(arrowPosition, new ArcheryTarget(arrowPosition, 0, 0)); 
        defaultBow.shoot(arrow);
        
        for(int i = 0; i < 100; i++) {
            Point3D position = arrow.getPositionAtTime(i);
            double x = 100 * Math.cos(45) * Math.sin(-45) * i + defaultBow.getStabilizationFactor();
            double y = 1.5 * 100 * Math.sin(45) * i + (0.5 * 9.8 * i * i);
            double z = 100 * Math.cos(45) * Math.cos(-45) * defaultBow.getZoomFactor() * i;

            assertEquals(x, position.x);
            assertEquals(y, position.y);
            assertEquals(z, position.z);
        }
    }
}
