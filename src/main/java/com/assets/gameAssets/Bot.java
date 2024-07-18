package com.assets.gameAssets;

import java.util.random.RandomGenerator;

import com.assets.generalAssets.App;

public class Bot extends Player implements Runnable {
    
    final static int MAX_ACTIONS = 5;

    /*
    private String[] stringozze = { 
        "Sei stato adottato",
        "Tuo padre è un tossico",
        "Dune è un film del cazzo",
        "Nano del cazzo",
        "Noi Bot facciamo sesso con tua mamma",
        "Dio, Patria e Famiglia",
        "Falce e Martello, Effige di Mao",
        "Palemmo mmedda",
        "Fanculo i carabinieri, mi scopo Giusy Ferreri",
        "Vannacci >> Tolstoj",
        "Vedi te se mi devo fa dì male dal figlio di Totò Riina",
        "Meglio fare la cacca nella doccia che fare la doccia nella cacca",
        "I <3 Dittatura del Proletariato",
        "Meglio un giorno in pretura che vent'anni di dittatura",
        "Madonna cara",
        "Baricco spero ti venga un colpo",
        "Sborricchio",
        "Galassia lontana, tua madre puttana"
    };
    */

    private static RandomGenerator rnd = RandomGenerator.getDefault();

    public Bot(String name, String hexColor) {

        super(name, hexColor, Player.PlayerType.TYPE_BOT);   
    }

    public void run() {
        play();
    }

    public void play() {

        // TODO: Qui ci vanno tutte le cose che fa il bot quando gioca

        // System.out.println(this.getName() + " says: " + stringozze[rnd.nextInt(stringozze.length)]);
        
        int actionNumber = rnd.nextInt(MAX_ACTIONS);

        for (int i = 0; i < actionNumber; i++) {

            int curAction = rnd.nextInt(6); // QUI COME NUMERO BISOGNA METTERE IL NUMERO DI AZIONI CHE SI POSSONO FARE

            switch (curAction) {
                case 0:     // Attacco
                    
                    break;

                case 1:     // Negoziazione
                    
                    break;

                case 2:     // Opere per i Cittadini
                    
                    break;

                case 3:     // Fortificazione

                    break;
                
                case 4:     // Recluta

                    break;

                case 5:     // Rifornimento
                    
                    break;

                default:    // Se entra qui dentro abbiamo un problema
                    
                    break;
            }


            

            try { Thread.sleep(rnd.nextLong(500, 2000)); } catch (InterruptedException e) { return; }
        }

        try { Thread.sleep(rnd.nextLong(500, 2000)); } catch (InterruptedException e) { return; }

        App.gameManager.passTurn();
    }

}
