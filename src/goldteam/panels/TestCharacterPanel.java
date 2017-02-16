package goldteam.panels;

import goldteam.animators.BigGhostAnimation;
import goldteam.domain.PanelManager;
import goldteam.domain.GamePanelBase;
import goldteam.animators.GhostAnimation;
import goldteam.characters.ArcherMan;
import goldteam.characters.Ghost;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.Delta;
import goldteam.domain.ModType;
import goldteam.gamedata.GameData;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class TestCharacterPanel extends GamePanelBase {

    private boolean pressRight, pressLeft, pressJump;
    private ArcherMan ar;
    
    public TestCharacterPanel(PanelManager panelManager)
    {
        super(panelManager, new GameData());
        pressRight = pressLeft = pressJump = false;
    }

    @Override
    protected void addGameObjects()
    {
        //CharacterAnimationBase ga1 = null;
        //ga1 = this.createNewGhost(gameData, new Point(300, 400), 15, "assets/GameGhostStripe.png", 3);
        //this.layeredPane.add(ga1, layeredPane.highestLayer());
        
        CharacterAnimationBase ar1 = null;
        ar1 = this.createNewArcher(gameData, new Point(400, 100), 15, "assets/GameGhostStripe.png");
        this.layeredPane.add(ar1, layeredPane.highestLayer());
    }

    protected CharacterAnimationBase createNewArcher(GameData gd, Point p, Integer speed, String image) {
        ar = new ArcherMan(gd, p);
        CharacterAnimationBase ga1;
        ga1 = new BigGhostAnimation(ar, gd.getVisibleDimensions(), image);
        //g1.setVelocityScalarDelta(Delta.create(0d, ModType.FIXED));
        ar.setAnimator(ga1);
        return ga1;
    }
    
    protected CharacterAnimationBase createNewGhost(GameData gd, Point p, Integer speed, String image, int bigOrSmall) {
        Ghost g1 = new Ghost(gd, p);
        CharacterAnimationBase ga1;
        if (bigOrSmall % 3 == 0) {
             ga1 = new BigGhostAnimation(g1, gd.getVisibleDimensions(), image);
        } else {
             ga1 = new GhostAnimation(g1, gd.getVisibleDimensions(), image);
        }
        g1.setVelocityScalarDelta(Delta.create(0d, ModType.FIXED));
        g1.setAnimator(ga1);
        return ga1;
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        super.keyPressed(e);
        
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_A:
                ar.setLeft(true);
                break;
            case KeyEvent.VK_D:
                ar.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                ar.setJump(true);
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_A:
                ar.setLeft(false);
                break;
            case KeyEvent.VK_D:
                ar.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                ar.setJump(false);
                break;
        }
    }

}
