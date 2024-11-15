package fr.ensicaen.ecole.archery.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import fr.ensicaen.ecole.archery.data.Map;

public class ProxyMapSelectorTest {

    @Test
    public void proxyNotLoadedMapTest() {
        ProxyMapSelector proxy = new ProxyMapSelector(); 
        Map map = proxy.getMap("Easy");

        assertEquals("Easy", map.name);
        assertEquals(550, map.target.position.x);
        assertEquals(200, map.target.position.y);
        assertEquals(400, map.target.position.z);
        assertEquals(60, map.target.radius);
        assertEquals(9, map.target.nbPart);

        assertEquals(550, map.player.position.x);
        assertEquals(615, map.player.position.y);
        assertEquals(0, map.player.position.z);
        assertEquals(30, map.player.nbArrow);
        assertEquals(390, map.bow.position.x);
        assertEquals(540, map.bow.position.y);

        map = proxy.getMap("Normal");
        assertEquals("Normal", map.name);

        map = proxy.getMap("Hard");
        assertEquals("Hard", map.name);

        map = proxy.getMap("tttt");
        assertNotEquals("tttt", map.name);
    }

    @Test
    public void proxyLoadedMapTest() {
        ProxyMapSelector proxy = new ProxyMapSelector(); 
        Map map = proxy.getMap("Easy");
        assertEquals("Easy", map.name);
        Map map2 = proxy.getMap("Easy");

        assertEquals(map.target.position.x, map2.target.position.x);
        assertEquals(map.target.position.y, map2.target.position.y);
        assertEquals(map.target.position.z, map2.target.position.z);
        assertEquals(map.target.radius, map2.target.radius);
        assertEquals(map.target.nbPart, map2.target.nbPart);

        assertEquals(map2.player.position.x, map.player.position.x);
        assertEquals(map2.player.position.y, map.player.position.y);
        assertEquals(map2.player.position.z, map.player.position.z);
        assertEquals(map2.player.nbArrow, map.player.nbArrow);
        assertEquals(map2.bow.position.x, map.bow.position.x);
        assertEquals(map2.bow.position.y, map.bow.position.y);

        map = proxy.getMap("Hard");
        map2 = proxy.getMap("Hard");

        assertEquals(map.target.position.x, map2.target.position.x);
        assertEquals(map.target.position.y, map2.target.position.y);
        assertEquals(map.target.position.z, map2.target.position.z);
        assertEquals(map.target.radius, map2.target.radius);
        assertEquals(map.target.nbPart, map2.target.nbPart);

        assertEquals(map2.player.position.x, map.player.position.x);
        assertEquals(map2.player.position.y, map.player.position.y);
        assertEquals(map2.player.position.z, map.player.position.z);
        assertEquals(map2.player.nbArrow, map.player.nbArrow);
        assertEquals(map2.bow.position.x, map.bow.position.x);
        assertEquals(map2.bow.position.y, map.bow.position.y);
    }
}
