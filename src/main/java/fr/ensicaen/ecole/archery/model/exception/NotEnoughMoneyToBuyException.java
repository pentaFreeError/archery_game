package fr.ensicaen.ecole.archery.model.exception;

public class NotEnoughMoneyToBuyException extends Exception {

    public NotEnoughMoneyToBuyException() {
        super("You don't have enough money to buy this item.");
    }
}

