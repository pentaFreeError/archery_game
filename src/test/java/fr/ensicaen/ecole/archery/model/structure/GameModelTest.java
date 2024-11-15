package fr.ensicaen.ecole.archery.model.structure;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import fr.ensicaen.ecole.archery.model.player.Human;
import fr.ensicaen.ecole.archery.model.projectile.Arrow;
import fr.ensicaen.ecole.archery.model.target.ArcheryTarget;

public class GameModelTest {
    @Test
    public void testArrowsNull() {

        GameModel gameModel = new GameModel(null, new Human(null, null, null));

        assertEquals(0, gameModel._initialNumberArrows);        
        assertNull(gameModel._player.getArrows());
    }

    @Test
    public void testArrowsNotNull() {
        ArrayList<Arrow> arrows = new ArrayList<>();
        arrows.add(new Arrow(null, new ArcheryTarget(new Point3D(0, 0, 0), 0, 0)));
        arrows.size();

        GameModel gameModel = new GameModel(null, new Human(null, null, arrows));

        assertEquals(1, gameModel._initialNumberArrows);        
        assertNotNull(gameModel._player.getArrows());

    }
}