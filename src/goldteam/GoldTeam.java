package goldteam;

import goldteam.domain.GameSounds;
import javax.swing.JFrame;

public class GoldTeam {

    /**
     * @param args the command line arguments Start of application. Use the
     * bootstrap to create objects used in the game.
     */
    public static void main(String[] args) {

        /**
         * Add new panels in goldteam.Bootstrap.java
         */
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.initialize();
        Runnable r = () -> {
            bootstrap.<JFrame>resolve("GameWindow");
        };

        javax.swing.SwingUtilities.invokeLater(r);
    }

}
