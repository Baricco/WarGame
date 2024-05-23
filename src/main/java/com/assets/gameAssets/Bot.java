package com.assets.gameAssets;

import java.util.random.RandomGenerator;

import com.assets.generalAssets.App;

public class Bot extends Player {
    
    private String[] stringozze = { 
        "Sei stato adottato",
        "Tuo padre è un tossico",
        "Dune è un film del cazzo",
        "Nano del cazzo",
        "Noi Bot facciamo sesso con tua mamma",
        "Dio, Patria e Famiglia",
        "Falce e Martello, Effige di Mao"
    };

    private static RandomGenerator rnd = RandomGenerator.getDefault();

    public Bot(String name, String hexColor) {

        super(name, hexColor, Player.PlayerType.TYPE_BOT);   
    }

    public void play() {

        // TODO: Bisognerà creare un timer per fare in modo che i bot non finiscano istantaneamente il turno
        // TODO: Qui ci vanno tutte le cose che fa il bot quando gioca

        System.out.println(this.getName() + " says: " + stringozze[rnd.nextInt(stringozze.length)]);

        App.gameManager.passTurn();
    }

}
