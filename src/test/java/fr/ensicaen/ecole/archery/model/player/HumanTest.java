package fr.ensicaen.ecole.archery.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import fr.ensicaen.ecole.archery.model.exception.NoMoreArrowException;
import fr.ensicaen.ecole.archery.model.projectile.Arrow;
import fr.ensicaen.ecole.archery.model.shooter.DefaultBow;
import fr.ensicaen.ecole.archery.model.structure.Point3D;
import fr.ensicaen.ecole.archery.model.target.ArcheryTarget;

public class HumanTest {
    @Test
    public void createHuman() {
        Point3D position = new Point3D(1, 2, 3);
        DefaultBow bow = new DefaultBow();
        ArrayList<Arrow> arrows = new ArrayList<>();

        Player h = new Human(position, bow, arrows);

        assertEquals(0, h.getArrows().size());
        assertEquals(0, h.getInventory().getItems().size());
    }

    @Test 
    public void addScore() {
        DefaultBow bow = new DefaultBow();
        ArrayList<Arrow> arrows = new ArrayList<>();
        Player h = new Human(null, null, null);
        h.setArrow(arrows);
        h.setInventory(new Inventory());
        h.setPosition(new Point3D(0, 0, 0));
        h.setScore(10);
        h.setShooter(bow);
        h.addScore(10);
        assertEquals(20, h.getScore());
        assertNotNull(h.getPosition());
    }

    @Test
    public void testsetUpShot() {
        DefaultBow bow = new DefaultBow();
        ArrayList<Arrow> arrows = new ArrayList<>();
        arrows.add(new Arrow(null, new ArcheryTarget(new Point3D(0, 0, 0), 0, 0)));
        Human h = new Human(new Point3D(0, 0, 0), bow, arrows);
        
        Arrow currentArrow = h.setupShot(0, 0, 0);
        assertEquals(arrows.get(0), currentArrow);
    }

    @Test
    public void testShoot() {
        DefaultBow bow = new DefaultBow();
        ArrayList<Arrow> arrows = new ArrayList<>();
        
        Arrow tmp = new Arrow(null, new ArcheryTarget(new Point3D(0, 0, 0), 0, 0)); 
        arrows.add(tmp);
        Human h = new Human(new Point3D(0, 0, 0), bow, arrows);
        
        Arrow currentArrow = h.shoot(0, 0, 0);
        assertEquals(tmp, currentArrow);
        assertEquals(0, arrows.size());

        boolean catchException = false;

        try {
            h.shoot(0, 0, 0);
        } catch(NoMoreArrowException e) {
            catchException = true;
        }

        assertTrue(catchException);
    }
}
