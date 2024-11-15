package fr.ensicaen.ecole.archery.model.structure;

import fr.ensicaen.ecole.archery.model.player.Player;
import fr.ensicaen.ecole.archery.model.target.Shootable;

public class GameModel {
    
    public Shootable _target;
    public Player _player;
    public int _initialNumberArrows;

    public GameModel(Shootable target, Player player) {
        _target = target;
        _player = player;
        if(player.getArrows() != null) {
            _initialNumberArrows = player.getNumberArrows();
        } else {
            _initialNumberArrows = 0;
        }
    }
}
