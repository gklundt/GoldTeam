package goldteam;

import javax.swing.JFrame;

public class GoldTeam {

    /**
     * @param args the command line arguments
     * Start of application.
     * Use the bootstrap to create objects used in the game.
     */
    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.initialize();
        JFrame frame = bootstrap.<JFrame>resolve("GameWindow");

    }

}
