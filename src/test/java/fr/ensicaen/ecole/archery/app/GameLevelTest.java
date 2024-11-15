package fr.ensicaen.ecole.archery.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameLevelTest {
   
    @Test
    public void testCreate() {
        GameLevel gameLevelEasy = GameLevel.EASY;
        GameLevel gameLevelNormal = GameLevel.NORMAL;
        GameLevel gameLevelHard = GameLevel.HARD;

        assertEquals("Hard", gameLevelHard.getMapName());
        assertEquals("Easy", gameLevelEasy.getMapName());
        assertEquals("Normal", gameLevelNormal.getMapName());
   } 

    @Test
    public void testFromString() {
        GameLevel gameLevel = GameLevel.fromString("Hard");
        assertEquals(GameLevel.HARD, gameLevel);
        gameLevel = GameLevel.fromString("Normal");
        assertEquals(GameLevel.NORMAL, gameLevel);
        gameLevel = GameLevel.fromString("Easy");
        assertEquals(GameLevel.EASY, gameLevel);
        gameLevel = GameLevel.fromString("apfhsapifhaf");
        assertNull(gameLevel);
    }
}
