package fr.ensicaen.ecole.archery.app;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import fr.ensicaen.ecole.archery.data.Map;
import fr.ensicaen.ecole.archery.data.MapData;

public class ProxyMapSelector {
    
    private final java.util.Map<String, Map> cache;
    private final Set<String> loadedMapNames;

    public ProxyMapSelector() {
        this.cache = new HashMap<>();
        this.loadedMapNames = new HashSet<>();
    }

    public Map getMap(String mapName) {
        if (loadedMapNames.contains(mapName)) {
            return cache.get(mapName);
        }
        
        Map map = MapSelector.getMap(mapName);
        
        if (map != null) {
            cache.put(mapName, map);
            loadedMapNames.add(mapName);
        }

        return map;
    }

    private static class MapSelector {

        public static Map getMap(String mapName) {
            Gson gson = new Gson();
            Map currentMap = new Map();

            try {
                InputStream inputStream = MapSelector.class.getResourceAsStream("/fr/ensicaen/ecole/archery/maps/maps.json");

                assert inputStream != null;
                InputStreamReader reader = new InputStreamReader(inputStream);

                MapData mapData = gson.fromJson(reader, MapData.class);

                for (Map map : mapData.maps) {
                    if (mapName.equals(map.name)) {
                        currentMap = map;
                        break;
                    }
                }

            } catch (JsonIOException | JsonSyntaxException e) {
                e.printStackTrace();
            }

            return currentMap;
        }
    }
}
