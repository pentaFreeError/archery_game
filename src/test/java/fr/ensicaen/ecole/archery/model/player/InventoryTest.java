package fr.ensicaen.ecole.archery.model.player;

import fr.ensicaen.ecole.archery.model.shooter.DefaultBow;
import fr.ensicaen.ecole.archery.model.shop.Purchasable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public final class InventoryTest {

    private final Inventory _inventory = new Inventory();
    private final Purchasable _testItem = new DefaultBow();


    @Test
    public void should_return_testItem_when_adding_testItem() {
        _inventory.addItem(_testItem);
        assertNotNull(_inventory.getItems());
        assertEquals(_testItem, _inventory.getItems().get(0));
    }

    @Test
    public void should_increase_money_when_adding_a_positive_number_to_the_money() {
        _inventory.addMoney(15);
        assertEquals(15, _inventory.getMoney());
    }

    @Test
    public void should_decrease_money_when_adding_a_negative_number_to_the_money() {
        _inventory.setMoney(0);
        _inventory.addMoney(-15);
        assertEquals(-15, _inventory.getMoney());
    }

    @Test
    public void should_do_nothing_when_adding_zero_to_the_money() {
        _inventory.setMoney(0);
        _inventory.addMoney(0);
        assertEquals(0, _inventory.getMoney());
    }
}

