package fr.ensicaen.ecole.archery.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class DataTest {

    @Test
    public void testBowInitialization() {
        PowerBar powerBar = new PowerBar();
        powerBar.width = 100;
        powerBar.height = 20;
        powerBar.position = new Position();

        Position position = new Position();
        position.x = 10.0;
        position.y = 20.0;
        position.z = 30.0;

        Bow bow = new Bow();
        bow.powerBar = powerBar;
        bow.position = position;
        bow.width = 150;
        bow.height = 300;

        assertEquals(150, bow.width);
        assertEquals(300, bow.height);
        assertNotNull(bow.position);
        assertEquals(10.0, bow.position.x);
        assertNotNull(bow.powerBar);
        assertEquals(100, bow.powerBar.width);
    }

    @Test
    public void testBoxInitialization() {
        BoxElement nbArrows = new BoxElement();
        nbArrows.width = 50;
        nbArrows.height = 50;
        nbArrows.position = new Position();

        BoxElement scoreZone = new BoxElement();
        scoreZone.width = 200;
        scoreZone.height = 200;
        scoreZone.position = new Position();

        Box box = new Box();
        box.nbArrows = nbArrows;
        box.scoreZone = scoreZone;

        assertNotNull(box.nbArrows);
        assertEquals(50, box.nbArrows.width);
        assertEquals(50, box.nbArrows.height);
        assertNotNull(box.scoreZone);
        assertEquals(200, box.scoreZone.width);
        assertEquals(200, box.scoreZone.height);
    }

    @Test
    public void testMapInitialization() {
        Map map = new Map();
        map.name = "Archery Field";
        map.player = new Player();
        map.bow = new Bow();
        map.box = new Box();
        map.target = new Target();

        assertEquals("Archery Field", map.name);
        assertNotNull(map.player);
        assertNotNull(map.bow);
        assertNotNull(map.box);
        assertNotNull(map.target);
    }

    @Test
    public void testPlayerInitialization() {
        Player player = new Player();
        player.nbArrow = 10;
        player.position = new Position();
        player.position.x = 5.0;
        player.position.y = 10.0;
        player.position.z = 15.0;

        assertEquals(10, player.nbArrow);
        assertNotNull(player.position);
        assertEquals(5.0, player.position.x);
        assertEquals(10.0, player.position.y);
        assertEquals(15.0, player.position.z);
    }

    @Test
    public void testTargetInitialization() {
        Target target = new Target();
        target.position = new Position();
        target.radius = 30.0;
        target.nbPart = 5;

        assertNotNull(target.position);
        assertEquals(30.0, target.radius);
        assertEquals(5, target.nbPart);
    }

    @Test
    public void testPowerBarInitialization() {
        PowerBar powerBar = new PowerBar();
        powerBar.width = 200;
        powerBar.height = 30;
        powerBar.position = new Position();
        powerBar.position.x = 5.0;
        powerBar.position.y = 10.0;

        assertEquals(200, powerBar.width);
        assertEquals(30, powerBar.height);
        assertNotNull(powerBar.position);
        assertEquals(5.0, powerBar.position.x);
        assertEquals(10.0, powerBar.position.y);
    }

    @Test
    public void testMapDataInitialization() {
        MapData mapData = new MapData();
        mapData.maps = new ArrayList<>();

        Map map1 = new Map();
        map1.name = "Map 1";

        mapData.maps.add(map1);

        assertNotNull(mapData.maps);
        assertEquals(1, mapData.maps.size());
        assertEquals("Map 1", mapData.maps.get(0).name);
    }
}
