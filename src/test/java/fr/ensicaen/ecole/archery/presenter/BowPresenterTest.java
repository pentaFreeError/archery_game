package fr.ensicaen.ecole.archery.presenter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import fr.ensicaen.ecole.archery.app.ModelBuilder;
import fr.ensicaen.ecole.archery.app.ProxyMapSelector;
import fr.ensicaen.ecole.archery.data.Map;
import fr.ensicaen.ecole.archery.model.projectile.Arrow;
import fr.ensicaen.ecole.archery.model.shooter.DefaultBow;
import fr.ensicaen.ecole.archery.model.structure.GameModel;
import fr.ensicaen.ecole.archery.model.structure.Point3D;
import fr.ensicaen.ecole.archery.model.target.ArcheryTarget;

public class BowPresenterTest {
   @Test
   public void testBowMouseMovedNullValue() {
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Easy");
        GameModel _model = ModelBuilder.buildModel(_map);
        BowPresenter _bowPresenter = new BowPresenter(); 
        _bowPresenter.setInterface(_gameView);
        _bowPresenter.setModel(_model);

        double mouseX = 0;
        double mouseY = 0;
        double bowX = 0;
        double bowY = 0;

        _bowPresenter.bowMouseMoved(mouseX, mouseY, bowX, bowY);

        double deltaX = mouseX - bowX;
        double deltaY = mouseY - bowY;
        double normalizedY = Math.max(0, Math.min(1, (600 - mouseY) / 600));
        double scaleFactor = 1 + (normalizedY * 0.2); 
        scaleFactor = Math.max(1.0, Math.min(1.3, scaleFactor));  
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        double displayAngleX = Math.toDegrees(Math.atan2(deltaX, distance));
       
        verify(_gameView).updateBow(displayAngleX, scaleFactor);
   } 

   @Test
   public void testBowMouseMovedRealValue() {
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Easy");
        GameModel _model = ModelBuilder.buildModel(_map);
        BowPresenter _bowPresenter = new BowPresenter(); 
        _bowPresenter.setInterface(_gameView);
        _bowPresenter.setModel(_model);

        double mouseX = 120.3;
        double mouseY = 111.3;
        double bowX = 22.11;
        double bowY = 10;

        _bowPresenter.bowMouseMoved(mouseX, mouseY, bowX, bowY);

        double deltaX = mouseX - bowX;
        double deltaY = mouseY - bowY;
        double normalizedY = Math.max(0, Math.min(1, (600 - mouseY) / 600)); 
        double scaleFactor = 1 + (normalizedY * 0.2); 
        scaleFactor = Math.max(1.0, Math.min(1.3, scaleFactor));  
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        double displayAngleX = Math.toDegrees(Math.atan2(deltaX, distance));
       
        verify(_gameView).updateBow(displayAngleX, scaleFactor);
   } 

    @Test
    public void testHandleChargingBowActive() {
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Easy");
        GameModel _model = ModelBuilder.buildModel(_map);
        BowPresenter _bowPresenter = new BowPresenter(); 
        _bowPresenter.setInterface(_gameView);
        _bowPresenter.setModel(_model);

        _bowPresenter.bowMousePressed();
        _bowPresenter.handleChargingBow(new DefaultBow());
        
        double _chargePower = Math.min(0 + 0.5, 100);
        verify(_gameView).updatePowerBar(_chargePower);
   }

    @Test
    public void testHandleChargingBowNotActive() {
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Easy");
        GameModel _model = ModelBuilder.buildModel(_map);
        BowPresenter _bowPresenter = new BowPresenter(); 
        _bowPresenter.setInterface(_gameView);
        _bowPresenter.setModel(_model);

        _model._player.setArrow(new ArrayList<>());
        _bowPresenter.handleMouseReleased();
        _bowPresenter.handleChargingBow(new DefaultBow());
        
        double _chargePower = Math.min(0 + 0.5, 100);
        verify(_gameView, never()).updatePowerBar(_chargePower);
   }

   @Test
    public void testHandleMousePressed() {
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Easy");
        GameModel _model = ModelBuilder.buildModel(_map);
        BowPresenter _bowPresenter = new BowPresenter(); 
        _bowPresenter.setInterface(_gameView);
        _bowPresenter.setModel(_model);

        _model._player.setArrow(new ArrayList<>());
        _bowPresenter.handleMouseReleased();
        _bowPresenter.bowMousePressed();

        verify(_gameView).deleteTrajectory();
        verify(_gameView, times(2)).updatePowerBar(0);
        verify(_gameView).startChargingTimer();
   }

   @Test
   public void testHandleMouseReleasedNoArrow() {
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Easy");
        GameModel _model = ModelBuilder.buildModel(_map);
        BowPresenter _bowPresenter = new BowPresenter(); 
        _bowPresenter.setInterface(_gameView);
        _bowPresenter.setModel(_model);

        _model._player.setArrow(new ArrayList<>());
        int ret = _bowPresenter.handleMouseReleased();
        verify(_gameView).stopChargingTimer();
        verify(_gameView).updatePowerBar(0);
        verify(_gameView).displayEndPopUp(_model._player.getScore());

        assertEquals(_model._player.getScore(), ret);
   }

   @Test
   public void testHandleMouseReleasedGotArrow() {
        IView _gameView = mock(IView.class);
        ProxyMapSelector proxy = new ProxyMapSelector();
        Map _map = proxy.getMap("Easy");
        GameModel _model = ModelBuilder.buildModel(_map);
        BowPresenter _bowPresenter = new BowPresenter(); 
        _bowPresenter.setInterface(_gameView);
        _bowPresenter.setModel(_model);

        ArrayList<Arrow> arrows = new ArrayList<>();
        arrows.add(new Arrow(new Point3D(0, 0, 0), new ArcheryTarget(new Point3D(0, 0, 0), 0, 0)));

        _model._player.setArrow(arrows);
        int ret = _bowPresenter.handleMouseReleased();
        verify(_gameView).stopChargingTimer();
        verify(_gameView).changeScore(_model._player.getScore());
        verify(_gameView).changeNumberArrows(_model._player.getNumberArrows());
        verify(_gameView).updatePowerBar(0);

        assertEquals(-1, ret);
   }
}
