package goldteam;

import goldteam.panels.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GoldTeam {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.initialize();
        JFrame frame = bootstrap.<JFrame>resolve("GameWindow");

    }

}
