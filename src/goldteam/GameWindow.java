package goldteam;

import javax.swing.JFrame;
import goldteam.domain.PanelManager;
import goldteam.domain.PanelManagerListener;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.SplashScreen;
import javax.swing.JPanel;

/**
 *
 * GameWindow contains all subsequent control panels.
 *
 * Game Window is constructed with a PanelManager and implements its listener.
 *
 * When the Panel Manager's active panel is updated, the GameWindow updates the
 * focus element and loads a new panel
 *
 *
 */
public final class GameWindow extends JFrame implements PanelManagerListener {

    private final PanelManager panelManager;

    public GameWindow(GraphicsConfiguration gc, PanelManager panelManager) {
        super(gc);
        this.panelManager = panelManager;
        this.panelManager.addPanelManagerListener(() -> panelManagerChanged());
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(new Dimension(800, 600));
        super.setLocationRelativeTo(null);
        super.setContentPane(panelManager.getActivePanel());

        final SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            System.out.println("SplashScreen.getSplashScreen() returned null");
            return;
        }
        Graphics2D g = splash.createGraphics();
        if (g == null) {
            System.out.println("g is null");
            return;
        }

        for (int i = 0; i < 5; i++) {

            renderSplashFrame(g, i);
            splash.update();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }
        splash.close();

        super.setVisible(true);

    }

    @Override
    public void panelManagerChanged() {
        setVisible(false);
        JPanel p = panelManager.getActivePanel();
        setContentPane(p);
        super.setVisible(true);
    }

    static void renderSplashFrame(Graphics2D g, int frame) {
        final String[] comps = {"Adding Reticulating Splines", "Recovering from Hangover", "Brushing Teeth", "Cleaning Garage", "Battoning Down the Hatches", "Painting Toenails"};
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(0, 0, 800, 600);
        g.setPaintMode();
        g.setColor(Color.CYAN);
        g.drawString(comps[(frame / 5) % comps.length] + "...", 580, 20);
    }
}
