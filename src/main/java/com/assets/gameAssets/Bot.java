package com.assets.gameAssets;

public class Bot extends Player {
    
    public Bot(State state, String name, String hexColor) {

        super(state, name, hexColor, Player.PlayerType.TYPE_PLAYER);        

    }

    public void play() {

        // Bisognerà creare un timer per fare in modo che i bot non finiscano istantaneamente il turno
        // Qui ci vanno tutte le cose che fa il bot quando gioca

    }

}
