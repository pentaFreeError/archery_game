package fr.ensicaen.ecole.archery.model.player;

import fr.ensicaen.ecole.archery.model.shop.Purchasable;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private final List<Purchasable> _items;
    private int _money;

    public Inventory() {
        _items = new ArrayList<>();
        _money = 0;
    }

    public void addItem(Purchasable item) {

        if(!_items.contains(item))
            _items.add(item);
    }

    public void setMoney(int money) {
        _money = money;
    }

    public int getMoney() {
        return _money;
    }

    public void addMoney(int money) {
        _money += money;
    }

    public List<Purchasable> getItems() {
        return _items;
    }
}

