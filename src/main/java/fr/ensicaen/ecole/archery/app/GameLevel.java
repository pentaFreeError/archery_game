package fr.ensicaen.ecole.archery.app;

public enum GameLevel {
    EASY("Easy"),
    NORMAL("Normal"),
    HARD("Hard");

    private final String mapName;

    GameLevel(String mapName) {
        this.mapName = mapName;
    }

    public String getMapName() {
        return mapName;
    }

    public static GameLevel fromString(String level) {
        for (GameLevel gameLevel : GameLevel.values()) {
            if (gameLevel.mapName.equalsIgnoreCase(level)) {
                return gameLevel;
            }
        }

        return null;
    }
}

