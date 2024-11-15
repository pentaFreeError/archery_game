package fr.ensicaen.ecole.archery.model.shooter;

import fr.ensicaen.ecole.archery.model.projectile.Arrow;
import fr.ensicaen.ecole.archery.model.structure.Point3D;
import fr.ensicaen.ecole.archery.model.target.ArcheryTarget;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RogerPereEtFilsBowTest {
   
   @Test
    public void closeShootRogerPereEtFilsBow() {
        RogerPereEtFilsBow rogerBow = new RogerPereEtFilsBow();
        Point3D arrowPosition = new Point3D(0, 0, 0);
        Arrow arrow = new Arrow(arrowPosition, new ArcheryTarget(arrowPosition, 0, 0)); 
        rogerBow.shoot(arrow);
        Point3D position = arrow.getPositionAtTime(0);

        assertEquals(arrowPosition.x, position.x);
        assertEquals(arrowPosition.y, position.y);
        assertEquals(arrowPosition.z, position.z);
        rogerBow.setAngleX(10);
        rogerBow.setAngleY(10);
        rogerBow.setPower(100);
        rogerBow.shoot(arrow);

        position = arrow.getPositionAtTime(1);
        double x = 100 * Math.cos(10) * Math.sin(10) + rogerBow.getStabilizationFactor();
        double y = 1.5 * 100 * Math.sin(10) + (0.5 * 9.8);
        double z = 100 * Math.cos(10) * Math.cos(10) * rogerBow.getZoomFactor();

        assertEquals(x, position.x);
        assertEquals(y, position.y);
        assertEquals(z, position.z);
   }

    @Test
    public void farShootRogerPereEtFilsBow() {
        RogerPereEtFilsBow rogerBow = new RogerPereEtFilsBow(100, 45, 45);

        Point3D arrowPosition = new Point3D(0, 0, 0);
        Arrow arrow = new Arrow(arrowPosition, new ArcheryTarget(arrowPosition, 0, 0)); 
        rogerBow.shoot(arrow);
        Point3D position = arrow.getPositionAtTime(10);

        double x = 100 * Math.cos(45) * Math.sin(45) * 10 + rogerBow.getStabilizationFactor();
        double y = 1.5 * 100 * Math.sin(45) * 10 + (0.5 * 9.8 * 10 * 10);
        double z = 100 * Math.cos(45) * Math.cos(45) * rogerBow.getZoomFactor() * 10;

        assertEquals(x, position.x);
        assertEquals(y, position.y);
        assertEquals(z, position.z);
    }

    @Test
    public void testTrajectoryAfterShoot() {
        RogerPereEtFilsBow rogerBow = new RogerPereEtFilsBow(100, 45, 45);

        Point3D arrowPosition = new Point3D(0, 0, 0);
        Arrow arrow = new Arrow(arrowPosition, new ArcheryTarget(arrowPosition, 0, 0)); 
        rogerBow.shoot(arrow);
        
        for(int i = 0; i < 100; i++) {
            Point3D position = arrow.getPositionAtTime(i);
            double x = 100 * Math.cos(45) * Math.sin(45) * i + rogerBow.getStabilizationFactor();
            double y = 1.5 * 100 * Math.sin(45) * i + (0.5 * 9.8 * i * i);
            double z = 100 * Math.cos(45) * Math.cos(45) * rogerBow.getZoomFactor() * i;

            assertEquals(x, position.x);
            assertEquals(y, position.y);
            assertEquals(z, position.z);
        }
    }

    @Test
    public void testTrajectoryNegativeAngle() {
        RogerPereEtFilsBow rogerBow = new RogerPereEtFilsBow(100, -45, -45);

        Point3D arrowPosition = new Point3D(0, 0, 0);
        Arrow arrow = new Arrow(arrowPosition, new ArcheryTarget(arrowPosition, 0, 0)); 
        rogerBow.shoot(arrow);
        
        for(int i = 0; i < 100; i++) {
            Point3D position = arrow.getPositionAtTime(i);
            double x = 100 * Math.cos(-45) * Math.sin(-45) * i + rogerBow.getStabilizationFactor();
            double y = 1.5 * 100 * Math.sin(-45) * i + (0.5 * 9.8 * i * i);
            double z = 100 * Math.cos(-45) * Math.cos(-45) * rogerBow.getZoomFactor() * i;

            assertEquals(x, position.x);
            assertEquals(y, position.y);
            assertEquals(z, position.z);
        }
    }

    @Test
    public void testTrajectoryDifferentAngles() {
        RogerPereEtFilsBow rogerBow = new RogerPereEtFilsBow(100, -45, 45);

        Point3D arrowPosition = new Point3D(0, 0, 0);
        Arrow arrow = new Arrow(arrowPosition, new ArcheryTarget(arrowPosition, 0, 0)); 
        rogerBow.shoot(arrow);
        
        for(int i = 0; i < 100; i++) {
            Point3D position = arrow.getPositionAtTime(i);
            double x = 100 * Math.cos(45) * Math.sin(-45) * i + rogerBow.getStabilizationFactor();
            double y = 1.5 * 100 * Math.sin(45) * i + (0.5 * 9.8 * i * i);
            double z = 100 * Math.cos(45) * Math.cos(-45) * rogerBow.getZoomFactor() * i;

            assertEquals(x, position.x);
            assertEquals(y, position.y);
            assertEquals(z, position.z);
        }
    }
}
