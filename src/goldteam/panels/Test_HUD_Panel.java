package goldteam.panels;

import goldteam.GamePanelManager;
import goldteam.animators.ArcherAnimation;
import goldteam.animators.ArrowHudAnimation;
import goldteam.animators.BigGhostAnimation;
import goldteam.domain.PanelManager;
import goldteam.domain.GamePanelBase;
import goldteam.animators.HeartHudAnimation;
import goldteam.animators.LifeHudAnimation;
import goldteam.animators.ShieldHudAnimation;
import goldteam.characters.ArcherMan;
import goldteam.characters.Arrow;
import goldteam.characters.Ghost;
import goldteam.colliders.CollisionDetector;
import goldteam.colliders.GhostCollider;
import goldteam.domain.Animatable;
import goldteam.domain.AnimationBase;
import goldteam.domain.AnimationState;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.Delta;
import goldteam.domain.DoubleVector;
import goldteam.domain.ModType;
import goldteam.domain.VectorMath;
import goldteam.gamedata.GameData;
import goldteam.hud.ArrowHudItem;
import goldteam.hud.HeartHudItem;
import goldteam.hud.LifeHudItem;
import goldteam.hud.ShieldHudItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class Test_HUD_Panel extends GamePanelBase {
    private static final long serialVersionUID = 1L;

    private ArcherMan archer;
    private int charge;
    private Ghost[] bigGhost;
    private HeartHudItem hearts;
    private ShieldHudItem shields;
    private LifeHudItem lives;
    private ArrowHudItem arrows;
    private final CollisionDetector collisionDetector;

    public Test_HUD_Panel(PanelManager panelManager) {
        super(panelManager, new GameData());
        collisionDetector = new CollisionDetector(this.gameData);
        charge = 0;
    }

    @Override
    protected void addGameObjects() {
        GhostCollider gc = new GhostCollider();
        collisionDetector.addCollisionListener(gc);
        
        archer = new ArcherMan(gameData, new Point(60, 60));
        CharacterAnimationBase archerDefaultRight = new ArcherAnimation(archer, gameData.getVisibleDimensions(), "assets/Archer/Archer_Standing_Right.png", charge, charge);
        CharacterAnimationBase archerDefaultLeft = new ArcherAnimation(archer, gameData.getVisibleDimensions(), "assets/Archer/Archer_Standing_Left.png", charge, charge);
        CharacterAnimationBase archerWalkingRight = new ArcherAnimation(archer, gameData.getVisibleDimensions(), "assets/Archer/Archer_Walking_Right.png", charge);
        CharacterAnimationBase archerWalkingLeft = new ArcherAnimation(archer, gameData.getVisibleDimensions(), "assets/Archer/Archer_Walking_Left.png", charge);
        CharacterAnimationBase archerDrawingRight = new ArcherAnimation(archer, gameData.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Right.png", charge);
        CharacterAnimationBase archerDrawingLeft = new ArcherAnimation(archer, gameData.getVisibleDimensions(), "assets/Archer/Archer_Drawing_Left.png", charge);
        archer.setAnimator(archerDefaultRight);
        archer.addAnimator(AnimationState.DEFAULT_RIGHT, archerDefaultRight);
        archer.addAnimator(AnimationState.DEFAULT_LEFT, archerDefaultLeft);
        archer.addAnimator(AnimationState.WALKING_RIGHT, archerWalkingRight);
        archer.addAnimator(AnimationState.WALKING_LEFT, archerWalkingLeft);
        archer.addAnimator(AnimationState.SHOOTING_RIGHT, archerDrawingRight);
        archer.addAnimator(AnimationState.SHOOTING_LEFT, archerDrawingLeft);
        AnimationBase t = archer.getAnimator();
        this.layeredPane.add(t, layeredPane.highestLayer());
        archer.addAnimationChangeListener(l -> SwitchArcherListener(l));
        
        bigGhost = new Ghost[5];
        BigGhostAnimation[] bigGhostAnimation = new BigGhostAnimation[5];
        for(int i = 0; i<5; i++) {
            bigGhost[i] = new Ghost(gameData, new Point(100*i, 500));
            bigGhost[i].setHealthDelta(Delta.create(-20.0, ModType.FIXED));
            bigGhost[i].setShieldDelta(Delta.create(-20.0, ModType.FIXED));
            bigGhostAnimation[i] = new BigGhostAnimation(bigGhost[i], gameData.getVisibleDimensions(), "assets/GameGhostStripeRed.png");
            bigGhost[i].setAnimator(bigGhostAnimation[i]);
            bigGhost[i].setVelocityScalarDelta(Delta.create(0.0d, ModType.FIXED));
            this.layeredPane.add(bigGhostAnimation[i], this.layeredPane.highestLayer());
            collisionDetector.registerCollidable(bigGhost[i]);
        }
        
        hearts = new HeartHudItem(gameData, new Point(10, 10));
        hearts.setWatcher(archer);
        HeartHudAnimation hha = new HeartHudAnimation(hearts, gameData.getVisibleDimensions(), "assets/heart.png");
        hearts.setAnimator(hha);

        shields = new ShieldHudItem(gameData, new Point(10, 30));
        shields.setWatcher(archer);
        ShieldHudAnimation sha = new ShieldHudAnimation(shields, gameData.getVisibleDimensions(), "assets/shield.png");
        shields.setAnimator(sha);
        
        lives = new LifeHudItem(gameData, new Point(10, 50));
        lives.setWatcher(archer);
        LifeHudAnimation lha = new LifeHudAnimation(lives, gameData.getVisibleDimensions(), "assets/Archer_Head.png");
        lives.setAnimator(lha);

//        arrows = new ArrowHudItem(gameData, new Point(10, 70));
//        arrows.setWatcher(archer);
//        ArrowHudAnimation aha = new ArrowHudAnimation(arrows, gameData.getVisibleDimensions(), "assets/Arrow_HUD_Item.png");
//        arrows.setAnimator(aha);
        
        this.layeredPane.add(hha, this.layeredPane.highestLayer());
        this.layeredPane.add(sha, this.layeredPane.highestLayer());
        this.layeredPane.add(lha, this.layeredPane.highestLayer());
//        this.layeredPane.add(aha, this.layeredPane.highestLayer());
    }

    protected CharacterAnimationBase createNewArrow(GameData gd, Point p, DoubleVector speed, String image)
    {
        Arrow arrow = new Arrow(gd, (Point)(p.clone()), speed);
        CharacterAnimationBase ga1;
        ga1 = new ArcherAnimation(arrow, gd.getVisibleDimensions(), image);
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
                archer.setLeft(true);
                archer.removeAnimator = archer.animator;
                archer.animator = archer.animators.get(AnimationState.WALKING_LEFT);
                archer.notifyAnimationChangeListeners();
                break;
            case KeyEvent.VK_D:
                archer.setRight(true);
                archer.removeAnimator = archer.animator;
                archer.animator = archer.animators.get(AnimationState.WALKING_RIGHT);
                archer.notifyAnimationChangeListeners();
                break;
            case KeyEvent.VK_SPACE:
                archer.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                undoGraphics();
                panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
                break;
            case KeyEvent.VK_1:
                archer.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
                break;
            case KeyEvent.VK_2:
                archer.setShieldDelta(Delta.create(-1.0, ModType.FIXED));
                break;
            default:
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        archer.removeAnimator = archer.animator;
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_A:
                archer.setLeft(false);
                archer.animator = archer.animators.get(AnimationState.DEFAULT_LEFT);
                break;
            case KeyEvent.VK_D:
                archer.setRight(false);
                archer.animator = archer.animators.get(AnimationState.DEFAULT_RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                archer.setJump(false);
              //archer.animator = archer.animators.get(AnimationState.DEFAULT_JUMP);
                break;
        }
        archer.notifyAnimationChangeListeners();
    }
    
    @Override
    public void mousePressed(MouseEvent e)
    {
        super.mousePressed(e);
        archer.setMousePressed(true);
        archer.removeAnimator = archer.animator;

        if(e.getX() > archer.PositionVector().x)
            archer.animator = archer.animators.get(AnimationState.SHOOTING_RIGHT);
        else
            archer.animator = archer.animators.get(AnimationState.SHOOTING_LEFT);
        archer.notifyAnimationChangeListeners();
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {
        archer.removeAnimator = archer.animator;
        if(archer.animator == archer.animators.get(AnimationState.SHOOTING_RIGHT))
            archer.animator = archer.animators.get(AnimationState.DEFAULT_RIGHT);
        else if(archer.animator == archer.animators.get(AnimationState.SHOOTING_LEFT))
            archer.animator = archer.animators.get(AnimationState.DEFAULT_LEFT);
        archer.notifyAnimationChangeListeners();
        if(archer.canShootArrow())
        {
            CharacterAnimationBase arrow = null;
            DoubleVector velocity = VectorMath.getVelocityVector(new DoubleVector(e.getX() - archer.PositionVector().getX(), e.getY() - archer.PositionVector().getY()), 15 + archer.getMouseCharge() * 3);
            //velocity = new DoubleVector(velocity.x + archer.getVelocityVector().x, velocity.y + archer.getVelocityVector().y); //Player Momentum transfers to arrow
            if(archer.animator == archer.animators.get(AnimationState.DEFAULT_RIGHT))
                arrow = this.createNewArrow(gameData, archer.PositionVector(), velocity, "assets/Archer/Arrow_Shot_Right.png");
            else
                arrow = this.createNewArrow(gameData, archer.PositionVector(), velocity, "assets/Archer/Arrow_Shot_Left.png");
            this.layeredPane.add(arrow, layeredPane.highestLayer());
            archer.shootArrow();
            archer.setMousePressed(false);
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
