package fr.ensicaen.ecole.archery.presenter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import fr.ensicaen.ecole.archery.app.ModelBuilder;
import fr.ensicaen.ecole.archery.app.ProxyMapSelector;
import fr.ensicaen.ecole.archery.data.Map;
import fr.ensicaen.ecole.archery.model.structure.GameModel;

public class WelcomePagePresenterTest {

    @Test
    public void testHandleGameButton() {
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Easy");
        GameModel _model = ModelBuilder.buildModel(_map);
        WelcomePagePresenter _welcomePagePresenter = new WelcomePagePresenter(); 
        _welcomePagePresenter.setInterface(_gameView);
        _welcomePagePresenter.setModel(_model);

        _welcomePagePresenter.handleHomeButton();
        verify(_gameView).setWelcomePageScene();
    }

    @Test
    public void testHandleRetryButtonFromEasyLevel() {
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Easy");
        GameModel _model = ModelBuilder.buildModel(_map);
        WelcomePagePresenter _welcomePagePresenter = new WelcomePagePresenter(); 
        _welcomePagePresenter.setInterface(_gameView);
        _welcomePagePresenter.setModel(_model);

        _welcomePagePresenter.handleRetryButton();
        
        verify(_gameView).deleteTrajectory();
        verify(_gameView).changeScore(0);
        verify(_gameView).changeNumberArrows(_model._player.getNumberArrows());
        assertEquals(0, _model._player.getScore());
    }

    @Test
    public void testHandleRetryButtonFromHardLevel() {
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Hard");
        GameModel _model = ModelBuilder.buildModel(_map);
        WelcomePagePresenter _welcomePagePresenter = new WelcomePagePresenter(); 
        _welcomePagePresenter.setInterface(_gameView);
        _welcomePagePresenter.setModel(_model);

        _welcomePagePresenter.handleRetryButton();
        
        verify(_gameView).deleteTrajectory();
        verify(_gameView).changeScore(0);
        verify(_gameView).changeNumberArrows(_model._player.getNumberArrows());
        assertEquals(0, _model._player.getScore());
    }
}
