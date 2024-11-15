package fr.ensicaen.ecole.archery.presenter;

import fr.ensicaen.ecole.archery.model.structure.GameModel;
import fr.ensicaen.ecole.archery.model.exception.NotEnoughMoneyToBuyException;
import fr.ensicaen.ecole.archery.model.shop.Shop;

public class ShopPresenter {
    GameModel _model;
    IView _gameView;

    public void setInterface(IView gameView) {
        _gameView = gameView;
    }

    public void setModel(GameModel model) {
        _model = model;
    }

    public void handleShopButton(int money) {
        _gameView.hideShopErrorMessage();
        _gameView.changeMoneyValue(money);
        _gameView.setShopPage();
    }

    public void handleRogerBowBuy() {
        if(handleBuy("Roger Bow"))
            _gameView.setSoldRogerBow();
    }

    public void handleZoomBuy() {
        if(handleBuy("Zoom"))
            _gameView.setSoldZoom();
    }

    public void handleStabilizerBuy() {
        if(handleBuy("Stabilizer"))
            _gameView.setSoldStabilizer();
    }

    private boolean handleBuy(String objectName) {
        try {
            Shop.buy(_model._player, objectName);
        } catch(NotEnoughMoneyToBuyException e) {
            _gameView.displayShopErrorMessage();
            return false;
        }
        _gameView.hideShopErrorMessage();
        _gameView.changeMoneyValue(_model._player.getInventory().getMoney());
        return true;
    }
}

