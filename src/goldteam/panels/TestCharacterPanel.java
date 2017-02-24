package goldteam.panels;

import goldteam.animators.BigGhostAnimation;
import goldteam.domain.PanelManager;
import goldteam.domain.GamePanelBase;
import goldteam.animators.GhostAnimation;
import goldteam.characters.ArcherMan;
import goldteam.characters.Arrow;
import goldteam.characters.Ghost;
import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.Delta;
import goldteam.domain.DoubleVector;
import goldteam.domain.ModType;
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
        CharacterAnimationBase archerDefaultRight = new BigGhostAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Standing_Right.png", charge, charge);
        CharacterAnimationBase archerDefaultLeft = new BigGhostAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Standing_Left.png", charge, charge);
        CharacterAnimationBase archerWalkingRight = new BigGhostAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Walking_Right.png", charge);
        CharacterAnimationBase archerWalkingLeft = new BigGhostAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Walking_Left.png", charge);
        CharacterAnimationBase archerDrawingRight = new BigGhostAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Right.png", charge);
        CharacterAnimationBase archerDrawingLeft = new BigGhostAnimation(ar, gameData.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Left.png", charge);
        ar.setAnimator(archerDefaultRight);
        ar.addAnimator(AnimationState.DEFAULT_RIGHT, archerDefaultRight);
        ar.addAnimator(AnimationState.DEFAULT_LEFT, archerDefaultLeft);
        ar.addAnimator(AnimationState.WALKING_RIGHT, archerWalkingRight);
        ar.addAnimator(AnimationState.WALKING_LEFT, archerWalkingLeft);
        ar.addAnimator(AnimationState.SHOOTING_RIGHT, archerDrawingRight);
        ar.addAnimator(AnimationState.SHOOTING_LEFT, archerDrawingLeft);
        AnimationBase t = ar.getAnimator();
        this.layeredPane.add(t, layeredPane.highestLayer());
        ar.addAnimationChangeListener(l -> SwitchArcherListener(l));
    }
    
    protected CharacterAnimationBase createNewArrow(GameData gd, Point p, DoubleVector speed, String image)
    {
        Arrow arrow = new Arrow(gd, (Point)(p.clone()), speed);
        CharacterAnimationBase ga1;
        ga1 = new BigGhostAnimation(arrow, gd.getVisibleDimensions(), image);
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
                break;    
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        ar.removeAnimator = ar.animator;
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_A:
                ar.setLeft(false);
                ar.animator = ar.animators.get(AnimationState.DEFAULT_LEFT);
                break;
            case KeyEvent.VK_D:
                ar.setRight(false);
                ar.animator = ar.animators.get(AnimationState.DEFAULT_RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                ar.setJump(false);
              //ar.animator = ar.animators.get(AnimationState.DEFAULT_JUMP);
                break;
        }
        ar.notifyAnimationChangeListeners();
    }
    
    @Override
    public void mousePressed(MouseEvent e)
    {
        super.mousePressed(e);
        ar.setMousePressed(true);
        ar.removeAnimator = ar.animator;
        if(e.getX() > ar.rawVector.x)
            ar.animator = ar.animators.get(AnimationState.SHOOTING_RIGHT);
        else if(e.getX() < ar.rawVector.x)
            ar.animator = ar.animators.get(AnimationState.SHOOTING_LEFT);
        ar.notifyAnimationChangeListeners();
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {
        ar.removeAnimator = ar.animator;
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
            arrow = this.createNewArrow(gameData, ar.PositionVector(), velocity, "assets/GameGhostStripe.png");
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
