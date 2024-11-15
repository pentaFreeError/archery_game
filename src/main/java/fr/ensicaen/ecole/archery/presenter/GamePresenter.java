package fr.ensicaen.ecole.archery.presenter;


import fr.ensicaen.ecole.archery.app.GameLevel;
import fr.ensicaen.ecole.archery.app.ModelBuilder;
import fr.ensicaen.ecole.archery.app.ProxyMapSelector;
import fr.ensicaen.ecole.archery.data.Map;
import fr.ensicaen.ecole.archery.model.shooter.BowFactory;
import fr.ensicaen.ecole.archery.model.structure.GameModel;
import fr.ensicaen.ecole.archery.model.player.Human;
import fr.ensicaen.ecole.archery.model.player.Inventory;
import fr.ensicaen.ecole.archery.model.shop.Purchasable;
import fr.ensicaen.ecole.archery.model.shooter.Shooter;
import fr.ensicaen.ecole.archery.model.shop.Shop;
import fr.ensicaen.ecole.archery.model.shooter.StabilizerEnhancementDecorator;
import fr.ensicaen.ecole.archery.model.shooter.ZoomEnhancementDecorator;
import fr.ensicaen.ecole.archery.model.shooter.BowType;

public class GamePresenter {

    private IView _interfaceWithView;
    private final GameModel _model;
    private final BowPresenter _bowPresenter;
    private final WelcomePagePresenter _welcomePagePresenter;
    private GameLevel _level;
    private final ShopPresenter _shopPresenter;
    private final Inventory _inventory;
    private final Purchasable _rogerBow;
    private final Purchasable _zoomBow;
    private final Purchasable _stabilizerBow;
    private Shooter _currentBow;


    public GamePresenter() {
        _bowPresenter = new BowPresenter();
        _welcomePagePresenter = new WelcomePagePresenter();
        _shopPresenter = new ShopPresenter();
        _level = GameLevel.EASY;
        _inventory = new Inventory();
        _inventory.setMoney(0);

        Purchasable _defaultBow = (Purchasable)BowFactory.createBow(BowType.DEFAULT_BOW);
        _rogerBow = (Purchasable)BowFactory.createBow(BowType.ROGER_PERE_ET_FILS_BOW); 
        _zoomBow = new ZoomEnhancementDecorator((Shooter) _defaultBow);
        _stabilizerBow = new StabilizerEnhancementDecorator((Shooter) _defaultBow);

        _inventory.addItem(_defaultBow);
        Shop.addObject(_defaultBow);
        Shop.addObject(_rogerBow);
        Shop.addObject(_zoomBow);
        Shop.addObject(_stabilizerBow);

        _currentBow = BowFactory.createBow(BowType.DEFAULT_BOW);
        _model = new GameModel(null, new Human(null, null, null));
        _model._player.setInventory(_inventory);
        setModel(_model);
    }

    public void setInterface(IView interfaceWithView) {
        _interfaceWithView = interfaceWithView;
        _bowPresenter.setInterface(_interfaceWithView);
        _welcomePagePresenter.setInterface(interfaceWithView);
        _shopPresenter.setInterface(interfaceWithView);
    }

    public void setModel(GameModel model) {
        _bowPresenter.setModel(model);
        _welcomePagePresenter.setModel(model);
        _shopPresenter.setModel(model);
    }

    public void bowMouseMoved(double mouseX, double mouseY, double bowX, double bowY) {
        _bowPresenter.bowMouseMoved(mouseX, mouseY, bowX, bowY);
    }

    public void handleChargingBow() {
        _bowPresenter.handleChargingBow(_currentBow);
    }

    public void bowMousePressed() {
        _bowPresenter.bowMousePressed();
    }

    public void handleMouseReleased() {

        int ret = _bowPresenter.handleMouseReleased();
        if(ret != -1) {
            _inventory.addMoney(ret);
            for(Purchasable purchasable : _model._player.getInventory().getItems()) {
                if(!_inventory.getItems().contains(purchasable)) {
                    _inventory.addItem(purchasable);
                }
            }
        }

    }

    public void handleHomeButton() {
        _welcomePagePresenter.handleHomeButton();
    }

    public void handleRetryButton() {
        _welcomePagePresenter.handleRetryButton();
    }

    public void handlePlayButton() {
        Map map = new ProxyMapSelector().getMap(_level.getMapName());
        GameModel gameModel = ModelBuilder.buildModel(map);
        setModel(gameModel);
        gameModel._player.setInventory(_inventory);
        gameModel._player.setShooter(_currentBow);
        _bowPresenter.setChargingValue(0);
        String type = "bow";
        if(gameModel._player.getInventory().getItems().contains(_rogerBow)) {
            type = "roger";
        }

        gameModel._target.setRadius(gameModel._target.getRadius() * _currentBow.getZoomFactor());
        _interfaceWithView.displayGameView(map, type, _currentBow.getZoomFactor());
        _interfaceWithView.changeNumberArrows(gameModel._initialNumberArrows);
    }

    public void levelPressed(String level) {
        _level = GameLevel.fromString(level); 
    }

    public void handleShopButton() {
        _shopPresenter.handleShopButton(_inventory.getMoney());
    }

    public void handleRogerBowBuy() {
        _shopPresenter.handleRogerBowBuy();
        updateBow();
    }

    public void handleZoomBuy() {
        _shopPresenter.handleZoomBuy();
        updateBow();
    }

    public void handleStabilizerBuy() {
        _shopPresenter.handleStabilizerBuy();
        updateBow();
    }

    public void updateBow() {
        if(_model._player.getInventory().getItems().contains(_rogerBow)) {
            _currentBow = (Shooter)_rogerBow;
        }

        if(_model._player.getInventory().getItems().contains(_zoomBow)) {
            _currentBow = new ZoomEnhancementDecorator(_currentBow);
        }

        if(_model._player.getInventory().getItems().contains(_stabilizerBow)) {
            _currentBow = new StabilizerEnhancementDecorator(_currentBow);
        }
    }

}



