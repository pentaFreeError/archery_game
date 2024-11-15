package fr.ensicaen.ecole.archery.model.shop;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import fr.ensicaen.ecole.archery.model.exception.NotEnoughMoneyToBuyException;
import fr.ensicaen.ecole.archery.model.player.Human;
import fr.ensicaen.ecole.archery.model.player.Inventory;
import fr.ensicaen.ecole.archery.model.player.Player;
import fr.ensicaen.ecole.archery.model.shooter.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopTest {
       
    @BeforeEach
    public void setUp() {
        Shop.objectsToSell = new ArrayList<>();
    }

    @Test
    public void testListIsNotNull() {
        assertNotNull(Shop.objectsToSell);
    }

    @Test
    public void testListIsEmpty() {
        assertEquals(0, Shop.objectsToSell.size());
    }

    @Test
    public void testAddObject() {
        Purchasable defaultBow = new DefaultBow();
        Purchasable rogerBow = new RogerPereEtFilsBow();
        Purchasable zoomBow = new ZoomEnhancementDecorator((Shooter)defaultBow);
        Purchasable stabilizerBow = new StabilizerEnhancementDecorator((Shooter)defaultBow);
        
        Shop.addObject(defaultBow);
        assertEquals(1, Shop.objectsToSell.size());
        assertEquals(defaultBow, Shop.objectsToSell.get(0));
        
        Shop.addObject(rogerBow);
        assertEquals(2, Shop.objectsToSell.size());
        assertEquals(rogerBow, Shop.objectsToSell.get(1));

        Shop.addObject(zoomBow);
        assertEquals(3, Shop.objectsToSell.size());
        assertEquals(zoomBow, Shop.objectsToSell.get(2));

        Shop.addObject(stabilizerBow);
        assertEquals(4, Shop.objectsToSell.size());
        assertEquals(stabilizerBow, Shop.objectsToSell.get(3));
    }

    @Test
    public void testBuyDefaultBow() {
        Player player = new Human(null, null, null);
        player.setInventory(new Inventory());
        player.getInventory().setMoney(0);
        int initSize = player.getInventory().getItems().size();
        DefaultBow defaultBow = new DefaultBow();
        Shop.addObject(defaultBow);
        try {
            Shop.buy(player, "Default Bow");    
        } catch(NotEnoughMoneyToBuyException e) {
            fail();
        }

        assertEquals(0, player.getInventory().getMoney());
        assertEquals(initSize + 1, player.getInventory().getItems().size());

        try {
            Shop.buy(player, "Default Bow");    
        } catch(NotEnoughMoneyToBuyException e) {
            fail();
        }

        assertEquals(0, player.getInventory().getMoney());
        assertEquals(initSize + 1, player.getInventory().getItems().size());
        assertEquals(defaultBow, player.getInventory().getItems().get(0));
    }

    @Test
    public void testBuyRogerBow() {
        Player player = new Human(null, null, null);
        player.getInventory().setMoney(43);
        int initSize = player.getInventory().getItems().size();
        RogerPereEtFilsBow rogerBow = new RogerPereEtFilsBow();
        Shop.addObject(rogerBow);

        try {
            Shop.buy(player, "Roger Bow");    
        } catch(NotEnoughMoneyToBuyException e) {
            fail();
        }

        assertEquals(3, player.getInventory().getMoney());
        assertEquals(initSize + 1, player.getInventory().getItems().size());
        assertEquals(rogerBow, player.getInventory().getItems().get(0));
    }

    @Test
    public void testBuyZoom() {
        Player player = new Human(null, null, null);
        player.getInventory().setMoney(43);
        int initSize = player.getInventory().getItems().size();
        ZoomEnhancementDecorator zoomBow = new ZoomEnhancementDecorator(new DefaultBow());
        Shop.addObject(zoomBow);

        try {
            Shop.buy(player, "Zoom");    
        } catch(NotEnoughMoneyToBuyException e) {
            fail();
        }

        assertEquals(23, player.getInventory().getMoney());
        assertEquals(initSize + 1, player.getInventory().getItems().size());
        assertEquals(zoomBow, player.getInventory().getItems().get(0));
    }

    @Test
    public void testBuyStabilizer() {
        Player player = new Human(null, null, null);
        player.getInventory().setMoney(43);
        int initSize = player.getInventory().getItems().size();
        StabilizerEnhancementDecorator stabilizerBow = new StabilizerEnhancementDecorator(new DefaultBow());
        Shop.addObject(stabilizerBow);

        try {
            Shop.buy(player, stabilizerBow.getName());    
        } catch(NotEnoughMoneyToBuyException e) {
            fail();
        }

        assertEquals(23, player.getInventory().getMoney());
        assertEquals(initSize + 1, player.getInventory().getItems().size());
        assertEquals(stabilizerBow, player.getInventory().getItems().get(0));
    }

    @Test
    public void testNotEnoughMoneyToBuyException() {
        Player player = new Human(null, null, null);
        player.getInventory().setMoney(13);
        int initSize = player.getInventory().getItems().size();
        StabilizerEnhancementDecorator stabilizerBow = new StabilizerEnhancementDecorator(new DefaultBow());
        Shop.addObject(stabilizerBow);

        boolean catchException = false;

        try {
            Shop.buy(player, stabilizerBow.getName());    
        } catch(NotEnoughMoneyToBuyException e) {
            catchException = true;
        } 
        assertTrue(catchException);
        assertEquals(initSize, player.getInventory().getItems().size());
        assertEquals(13, player.getInventory().getMoney());
    }
}
