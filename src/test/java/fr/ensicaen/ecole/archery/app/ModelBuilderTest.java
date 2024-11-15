package fr.ensicaen.ecole.archery.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.ensicaen.ecole.archery.data.*;
import fr.ensicaen.ecole.archery.model.structure.GameModel;
import fr.ensicaen.ecole.archery.model.structure.Point3D;

public class ModelBuilderTest {

    @Test
    public void testBuildModel() {
        Map map = new Map();
        map.name = "Archery Field";
        map.player = new Player();
        map.player.nbArrow = 10;
        map.player.position = new Position();
        map.player.position.x = 5.0;
        map.player.position.y = 10.0;
        map.player.position.z = 15.0;
        map.target = new Target();
        map.target.position = new Position();
        map.target.radius = 30.0;
        map.target.nbPart = 5;
        map.target.position.x = 5.0;
        map.target.position.y = 10.0;
        map.target.position.z = 15.0;

        GameModel gamemodel = ModelBuilder.buildModel(map);

        assertEquals(30, gamemodel._target.getRadius());
        assertEquals(new Point3D(5, 10, 15), gamemodel._target.getCenterPosition());
        assertEquals(10, gamemodel._player.getNumberArrows());
        assertEquals(new Point3D(5, 10, 15), gamemodel._player.getPosition());
    }
}


