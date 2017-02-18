package goldteam.panels;

import goldteam.GamePanelManager;
import goldteam.animators.TestMapAnimator;
import goldteam.domain.Delta;
import goldteam.domain.GamePanelBase;
import goldteam.domain.ModType;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import goldteam.maps.TestMap;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author Mishal
 */
public class TestMapsPanel extends GamePanelBase {

    private TestMap tm;

    public TestMapsPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
    }

    @Override
    protected void addGameObjects() {

        tm = new TestMap(gameData, new Point(-1000, -1000));
        TestMapAnimator tma = new TestMapAnimator(tm, gameData.getMapDimensions());
        tm.setAnimator(tma);
        this.layeredPane.add(tma, layeredPane.highestLayer());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            panelThread.interrupt();
            undoGraphics();
            panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
            return;
        }

        Delta x = Delta.create(0.0, ModType.FIXED);
        Delta y = Delta.create(0.0, ModType.FIXED);

        if (e.getKeyCode() == KeyEvent.VK_D) {
            x.delta = -20.0d;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            x.delta = 20.0d;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            y.delta = 20.0d;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            y.delta = -20.0d;
        }

        tm.setVelocityVectorDelta(x, y);

    }

    @Override
    public void keyReleased(KeyEvent e) {

        Delta x = Delta.create(0.0, ModType.FIXED);
        Delta y = Delta.create(0.0, ModType.FIXED);
        tm.setVelocityVectorDelta(x, y);
    }

}
