package goldteam.panels;

import goldteam.animators.ArcherAnimation;
import goldteam.animators.ArrowAnimation;
import goldteam.domain.PanelManager;
import goldteam.domain.GamePanelBase;
import goldteam.characters.ArcherMan;
import goldteam.characters.Arrow;
import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.DoubleVector;
import goldteam.domain.VectorMath;
import goldteam.gamedata.GameData;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class TestCharacterPanel extends GamePanelBase
{

    private ArcherMan ar;
    private int charge;
    
    public TestCharacterPanel(PanelManager panelManager)
    {
        super(panelManager, new GameData());
        charge = 0;
    }

    @Override
    protected void addGameObjects()
    {
        ar = new ArcherMan(gameData, new Point(400, 400));
        AnimationBase t = ar.getAnimator();
        this.layeredPane.add(t, layeredPane.highestLayer());
        ar.addAnimationChangeListener(l -> SwitchArcherListener(l));
    }
    
    protected CharacterAnimationBase createNewArrow(GameData gd, Point p, DoubleVector speed, String image)
    {
        Arrow arrow = new Arrow(gd, (Point)(p.clone()), speed);
        CharacterAnimationBase ga1;
        ga1 = new ArrowAnimation(arrow, gd.getVisibleDimensions(), image);
        arrow.setAnimator(ga1);
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
                ar.removeAnimator = ar.animator;
                ar.animator = ar.animators.get(AnimationState.WALKING_LEFT);
                ar.notifyAnimationChangeListeners();
                break;
            case KeyEvent.VK_D:
                ar.setRight(true);
                ar.removeAnimator = ar.animator;
                ar.animator = ar.animators.get(AnimationState.WALKING_RIGHT);
                ar.notifyAnimationChangeListeners();
                break;
            case KeyEvent.VK_SPACE:
                ar.setJump(true);
                ar.removeAnimator = ar.animator;
                ar.animator = ar.animators.get(AnimationState.JUMPING_RIGHT);
                ar.notifyAnimationChangeListeners();
                break;
            case KeyEvent.VK_1:
                ar.removeAnimator = ar.animator;
                ar.animator = ar.animators.get(AnimationState.DYING);
                ar.notifyAnimationChangeListeners();
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
                ar.removeAnimator = ar.animator;
                ar.animator = ar.animators.get(AnimationState.DEFAULT_LEFT);
                ar.notifyAnimationChangeListeners();
                break;
            case KeyEvent.VK_D:
                ar.setRight(false);
                ar.removeAnimator = ar.animator;
                ar.animator = ar.animators.get(AnimationState.DEFAULT_RIGHT);
                ar.notifyAnimationChangeListeners();
                break;
            case KeyEvent.VK_SPACE:
                ar.setJump(false);
                ar.removeAnimator = ar.animator;
                ar.animator = ar.animators.get(AnimationState.DEFAULT_RIGHT);
                ar.notifyAnimationChangeListeners();
                break;
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e)
    {
        super.mousePressed(e);
        ar.setMousePressed(true);
        ar.removeAnimator = ar.animator;
        ar.pauseAnimation = true;
        if(e.getX() > ar.PositionVector().x)
            ar.animator = ar.animators.get(AnimationState.SHOOTING_RIGHT);
        else
            ar.animator = ar.animators.get(AnimationState.SHOOTING_LEFT);
        ar.notifyAnimationChangeListeners();
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {
        ar.removeAnimator = ar.animator;
        ar.pauseAnimation = false;
        if(ar.animator == ar.animators.get(AnimationState.SHOOTING_RIGHT))
            ar.animator = ar.animators.get(AnimationState.DEFAULT_RIGHT);
        else if(ar.animator == ar.animators.get(AnimationState.SHOOTING_LEFT))
            ar.animator = ar.animators.get(AnimationState.DEFAULT_LEFT);
        ar.notifyAnimationChangeListeners();
        if(ar.canShootArrow())
        {
            CharacterAnimationBase arrow = null;
            DoubleVector velocity = VectorMath.getVelocityVector(new DoubleVector(e.getX() - ar.PositionVector().getX(), e.getY() - ar.PositionVector().getY()), 15 + ar.getMouseCharge() * 3);
            //velocity = new DoubleVector(velocity.x + ar.getVelocityVector().x, velocity.y + ar.getVelocityVector().y); //Player Momentum transfers to arrow
            if(ar.animator == ar.animators.get(AnimationState.DEFAULT_RIGHT))
                arrow = this.createNewArrow(gameData, ar.PositionVector(), velocity, "assets/Archer/Arrow_Shot_Right.png");
            else
                arrow = this.createNewArrow(gameData, ar.PositionVector(), velocity, "assets/Archer/Arrow_Shot_Left.png");
            this.layeredPane.add(arrow, layeredPane.highestLayer());
            ar.shootArrow();
            ar.setMousePressed(false);
        }
        else
        {
            //Something? Idk
        }
    }

    private void SwitchArcherListener(ActionEvent event) {
        Animatable obj = (Animatable) event.getSource();
        this.layeredPane.remove(obj.getRemoveAnimator());
        this.layeredPane.add(obj.getAnimator());
    }

}
