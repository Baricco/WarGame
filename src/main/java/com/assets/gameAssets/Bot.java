package com.assets.gameAssets;

public class Bot extends Player {
    
    public Bot(String name, String hexColor) {

        super(name, hexColor, Player.PlayerType.TYPE_BOT);        
    }

    public void play() {

        // Bisognerà creare un timer per fare in modo che i bot non finiscano istantaneamente il turno
        // Qui ci vanno tutte le cose che fa il bot quando gioca

    }

}
