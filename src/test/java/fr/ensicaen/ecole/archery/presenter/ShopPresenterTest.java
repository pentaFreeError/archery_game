package fr.ensicaen.ecole.archery.presenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import fr.ensicaen.ecole.archery.app.ModelBuilder;
import fr.ensicaen.ecole.archery.app.ProxyMapSelector;
import fr.ensicaen.ecole.archery.data.Map;
import fr.ensicaen.ecole.archery.model.structure.GameModel;

public class ShopPresenterTest {

    @Test
    public void testHandleShopButton() {
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Easy");
        GameModel _model = ModelBuilder.buildModel(_map);
        ShopPresenter _shopPresenter = new ShopPresenter(); 
        _shopPresenter.setInterface(_gameView);
        _shopPresenter.setModel(_model);

        _shopPresenter.handleShopButton(200);
        verify(_gameView).hideShopErrorMessage();
        verify(_gameView).changeMoneyValue(200);
        verify(_gameView).setShopPage();
    }

    @Test
    public void testHandleRogerBowBuyWork() {        
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Easy");
        GameModel _model = ModelBuilder.buildModel(_map);
        ShopPresenter _shopPresenter = new ShopPresenter(); 
        _shopPresenter.setInterface(_gameView);
        _shopPresenter.setModel(_model);

        _shopPresenter.handleRogerBowBuy();

        verify(_gameView, never()).setSoldZoom();
        verify(_gameView, never()).setSoldStabilizer();
    }

    @Test
    public void testHandleRogerBowBuyDontWork() {
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Easy");
        GameModel _model = ModelBuilder.buildModel(_map);
        ShopPresenter _shopPresenter = new ShopPresenter(); 
        _shopPresenter.setInterface(_gameView);
        _shopPresenter.setModel(_model);

        _model._player.getInventory().addMoney(30);
        
        verify(_gameView, never()).setSoldRogerBow();
        verify(_gameView, never()).setSoldStabilizer();

        _shopPresenter.handleRogerBowBuy();

    }

    @Test
    public void testHandleStabilizerWork() {        
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Easy");
        GameModel _model = ModelBuilder.buildModel(_map);
        ShopPresenter _shopPresenter = new ShopPresenter(); 
        _shopPresenter.setInterface(_gameView);
        _shopPresenter.setModel(_model);

        _shopPresenter.handleStabilizerBuy();

        verify(_gameView, never()).setSoldZoom();
        verify(_gameView, never()).setSoldRogerBow();
    }

    @Test
    public void testHandleStabilizerDontWork() {
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Easy");
        GameModel _model = ModelBuilder.buildModel(_map);
        ShopPresenter _shopPresenter = new ShopPresenter(); 
        _shopPresenter.setInterface(_gameView);
        _shopPresenter.setModel(_model);

        _model._player.getInventory().addMoney(30);
        
        verify(_gameView, never()).setSoldRogerBow();
        verify(_gameView, never()).setSoldZoom();

        _shopPresenter.handleStabilizerBuy();
    }

    @Test
    public void testHandleZoomWork() {        
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Easy");
        GameModel _model = ModelBuilder.buildModel(_map);
        ShopPresenter _shopPresenter = new ShopPresenter(); 
        _shopPresenter.setInterface(_gameView);
        _shopPresenter.setModel(_model);

        _shopPresenter.handleZoomBuy();

        verify(_gameView, never()).setSoldStabilizer();
        verify(_gameView, never()).setSoldRogerBow();
    }

    @Test
    public void testHandleZoomDontWork() {
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Easy");
        GameModel _model = ModelBuilder.buildModel(_map);
        ShopPresenter _shopPresenter = new ShopPresenter(); 
        _shopPresenter.setInterface(_gameView);
        _shopPresenter.setModel(_model);

        _model._player.getInventory().addMoney(30);
        
        verify(_gameView, never()).setSoldRogerBow();
        verify(_gameView, never()).setSoldStabilizer();

        _shopPresenter.handleZoomBuy();
    }

}