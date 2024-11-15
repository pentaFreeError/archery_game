package fr.ensicaen.ecole.archery.model.shop;

import fr.ensicaen.ecole.archery.model.exception.NotEnoughMoneyToBuyException;
import fr.ensicaen.ecole.archery.model.player.Player;

import java.util.ArrayList;

public class Shop {
    public static ArrayList<Purchasable> objectsToSell = new ArrayList<>();

    public static void addObject(Purchasable object) {
        objectsToSell.add(object);
    }

    public static void buy(Player player, String objectName) throws NotEnoughMoneyToBuyException {
        for(Purchasable object : objectsToSell) {
            if(object.getName().equals(objectName)) {
                for(Purchasable playerObject : player.getInventory().getItems()) {
                    if(playerObject.equals(object)) {
                        return;
                    }
                }

                int currentMoney = player.getInventory().getMoney();
                
                if(currentMoney < object.getPrice()) 
                    throw new NotEnoughMoneyToBuyException();
                
                player.getInventory().setMoney(currentMoney - object.getPrice());
                player.getInventory().addItem(object);
            }
        }
    }
}

