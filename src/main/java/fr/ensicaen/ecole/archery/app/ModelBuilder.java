package fr.ensicaen.ecole.archery.app;

import java.util.ArrayList;

import fr.ensicaen.ecole.archery.data.Map;
import fr.ensicaen.ecole.archery.model.player.Human;
import fr.ensicaen.ecole.archery.model.player.Player;
import fr.ensicaen.ecole.archery.model.projectile.Arrow;
import fr.ensicaen.ecole.archery.model.shooter.DefaultBow;
import fr.ensicaen.ecole.archery.model.structure.GameModel;
import fr.ensicaen.ecole.archery.model.structure.Point3D;
import fr.ensicaen.ecole.archery.model.target.ArcheryTarget;

public class ModelBuilder {

    public static GameModel buildModel(Map map) {

        Point3D targetPosition = new Point3D(map.target.position.x, map.target.position.y, map.target.position.z);
        ArcheryTarget target = new ArcheryTarget(targetPosition, map.target.radius, map.target.nbPart);

        ArrayList<Arrow> arrows = new ArrayList<>();
        for(int i = 0; i < map.player.nbArrow; i++) {
            arrows.add(new Arrow(null, target));
        }

        DefaultBow bow = new DefaultBow();

        Point3D playerPosition = new Point3D(map.player.position.x, map.player.position.y, map.player.position.z);
        Player player = new Human(playerPosition, bow, arrows);

        return new GameModel(target, player);
    }
}
