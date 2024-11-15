package fr.ensicaen.ecole.archery.model.shooter;

import org.junit.jupiter.api.Test;

import fr.ensicaen.ecole.archery.model.projectile.Arrow;
import fr.ensicaen.ecole.archery.model.structure.Point3D;
import fr.ensicaen.ecole.archery.model.target.ArcheryTarget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class ZoomEnchancementDecoratorTest {
    @Test
    public void bowChangedTest() {
        DefaultBow bow = new DefaultBow();
        ZoomEnhancementDecorator zoombow = new ZoomEnhancementDecorator(bow);

        assertNotEquals(bow.getZoomFactor(), zoombow.getZoomFactor());
        assertNotEquals(bow.getPrice(), zoombow.getPrice());
        assertNotEquals(bow.getName(), zoombow.getName());
        assertEquals(bow.getRopeFactor(), zoombow.getRopeFactor());
    }

   @Test
    public void closeShootZoomBow() {
        ZoomEnhancementDecorator bow = new ZoomEnhancementDecorator(new DefaultBow());
        Point3D arrowPosition = new Point3D(0, 0, 0);
        Arrow arrow = new Arrow(arrowPosition, new ArcheryTarget(arrowPosition, 0, 0)); 
        bow.shoot(arrow);
        Point3D position = arrow.getPositionAtTime(0);

        assertEquals(arrowPosition.x, position.x);
        assertEquals(arrowPosition.y, position.y);
        assertEquals(arrowPosition.z, position.z);
        bow.setAngleX(10);
        bow.setAngleY(10);
        bow.setPower(100);
        bow.shoot(arrow);

        position = arrow.getPositionAtTime(1);
        double x = 100 * Math.cos(10) * Math.sin(10) + bow.getStabilizationFactor();
        double y = 1.5 * 100 * Math.sin(10) + (0.5 * 9.8);
        double z = 100 * Math.cos(10) * Math.cos(10);

        assertEquals(x, position.x);
        assertEquals(y, position.y);
        assertEquals(z, position.z);
   }

    @Test
    public void farShootZoomBow() {
        Shooter bow = new ZoomEnhancementDecorator(new DefaultBow(100, 45, 45));

        Point3D arrowPosition = new Point3D(0, 0, 0);
        Arrow arrow = new Arrow(arrowPosition, new ArcheryTarget(arrowPosition, 0, 0)); 
        bow.shoot(arrow);
        Point3D position = arrow.getPositionAtTime(10);

        double x = 100 * Math.cos(45) * Math.sin(45) * 10 + bow.getStabilizationFactor();
        double y = 1.5 * 100 * Math.sin(45) * 10 + (0.5 * 9.8 * 10 * 10);
        double z = 100 * Math.cos(45) * Math.cos(45) * 10;

        assertEquals(x, position.x);
        assertEquals(y, position.y);
        assertEquals(z, position.z);
    }

    @Test
    public void testTrajectoryAfterShoot() {
        BowDecorator bow = new ZoomEnhancementDecorator(new DefaultBow());

        bow.setAngleX(45);
        bow.setAngleY(45);
        bow.setPower(100);

        Point3D arrowPosition = new Point3D(0, 0, 0);
        Arrow arrow = new Arrow(arrowPosition, new ArcheryTarget(arrowPosition, 0, 0)); 
        bow.shoot(arrow);
        
        for(int i = 0; i < 100; i++) {
            Point3D position = arrow.getPositionAtTime(i);
            double x = 100 * Math.cos(45) * Math.sin(45) * i + bow.getStabilizationFactor();
            double y = 1.5 * 100 * Math.sin(45) * i + (0.5 * 9.8 * i * i);
            double z = 100 * Math.cos(45) * Math.cos(45) * i;

            assertEquals(x, position.x);
            assertEquals(y, position.y);
            assertEquals(z, position.z);
        }
    }

    @Test
    public void testTrajectoryNegativeAngle() {
        ZoomEnhancementDecorator bow = new ZoomEnhancementDecorator(new DefaultBow(100, -45, -45));

        Point3D arrowPosition = new Point3D(0, 0, 0);
        Arrow arrow = new Arrow(arrowPosition, new ArcheryTarget(arrowPosition, 0, 0)); 
        bow.shoot(arrow);
        
        for(int i = 0; i < 100; i++) {
            Point3D position = arrow.getPositionAtTime(i);
            double x = 100 * Math.cos(-45) * Math.sin(-45) * i + bow.getStabilizationFactor();
            double y = 1.5 * 100 * Math.sin(-45) * i + (0.5 * 9.8 * i * i);
            double z = 100 * Math.cos(-45) * Math.cos(-45) * i;

            assertEquals(x, position.x);
            assertEquals(y, position.y);
            assertEquals(z, position.z);
        }
    }

    @Test
    public void testTrajectoryDifferentAngles() {
        BowDecorator bow = new ZoomEnhancementDecorator(new DefaultBow(100, -45, 45));

        Point3D arrowPosition = new Point3D(0, 0, 0);
        Arrow arrow = new Arrow(arrowPosition, new ArcheryTarget(arrowPosition, 0, 0)); 
        bow.shoot(arrow);
        
        for(int i = 0; i < 100; i++) {
            Point3D position = arrow.getPositionAtTime(i);
            double x = 100 * Math.cos(45) * Math.sin(-45) * i + bow.getStabilizationFactor();
            double y = 1.5 * 100 * Math.sin(45) * i + (0.5 * 9.8 * i * i);
            double z = 100 * Math.cos(45) * Math.cos(-45) * i;

            assertEquals(x, position.x);
            assertEquals(y, position.y);
            assertEquals(z, position.z);
        }
    }
}
