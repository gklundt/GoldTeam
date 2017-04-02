package goldteam.panels;

import goldteam.domain.PanelManager;
import goldteam.domain.GamePanelBase;
import goldteam.colliders.EnemyArrowCollider;
import goldteam.gamedata.GameData;
import goldteam.builders.ArrowChargeHudBuilder;
import goldteam.builders.ArrowHudBuilder;
import goldteam.builders.HeartHudBuilder;
import goldteam.builders.HudGhostEnemyBuilder;
import goldteam.builders.LifeHudBuilder;
import goldteam.builders.ShieldHudBuilder;
import java.awt.Point;
import java.util.Random;

public class Test_HUD_Panel extends GamePanelBase {

    public Test_HUD_Panel(PanelManager panelManager) {
        super(panelManager, new GameData());
    }

    @Override
    protected void addGameObjects() {
        super.addGameObjects();

        EnemyArrowCollider gc = new EnemyArrowCollider();
        addGameObject(gc);

        Random r = new Random();

        gameObjectBuilder = new HudGhostEnemyBuilder(gameData);
        for (int i = 0; i < 5; i++) {
            addGameObject(gameObjectProvider.build(gameObjectBuilder, new Point(r.nextInt(800), r.nextInt(600))));
        }
        
        hudBuilder = new HeartHudBuilder(gameData);
        addGameObject(hudProvider.build(hudBuilder, new Point(10, 10), gameData.getAttackableCharacter()));

        hudBuilder = new ShieldHudBuilder(gameData);
        addGameObject(hudProvider.build(hudBuilder, new Point(10, 30), gameData.getAttackableCharacter()));

        hudBuilder = new LifeHudBuilder(gameData);
        addGameObject(hudProvider.build(hudBuilder, new Point(10, 50), gameData.getDepletableCharacter()));

        hudBuilder = new ArrowHudBuilder(gameData);
        addGameObject(hudProvider.build(hudBuilder, new Point(10, 70),archerWeapon));

        hudBuilder = new ArrowChargeHudBuilder(gameData);
        addGameObject(hudProvider.build(hudBuilder, gameData.getMovableCharacter().PositionVector(), archerWeapon));
    }
//
//    protected CharacterAnimationBase createNewArrow(GameData gd, Point p, DoubleVector speed, String image)
//    {
//        Arrow arrow = new Arrow(gd, (Point)(p.clone()), speed);
//        collisionDetector.registerCollidable(arrow);
//        CharacterAnimationBase ga1;
//        ga1 = new ArcherAnimationStanding(arrow, gd.getVisibleDimensions(), image);
//        arrow.setAnimator(ga1);
//        return ga1;
//    }
//    
//    @Override
//    public void keyPressed(KeyEvent e)
//    {
//        super.keyPressed(e);
//        
//        switch (e.getKeyCode())
//        {
//            case KeyEvent.VK_A:
//                archer.setLeft(true);
//                archer.removeAnimator = archer.animator;
//                archer.animator = archer.animators.get(AnimationState.WALKING_LEFT);
//                archer.notifyAnimationChangeListeners();
//                break;
//            case KeyEvent.VK_D:
//                archer.setRight(true);
//                archer.removeAnimator = archer.animator;
//                archer.animator = archer.animators.get(AnimationState.WALKING_RIGHT);
//                archer.notifyAnimationChangeListeners();
//                break;
//            case KeyEvent.VK_SPACE:
//                archer.setJump(true);
//                break;
//            case KeyEvent.VK_ESCAPE:
//                undoGraphics();
//                for(Ghost g : bigGhost)
//                    this.collisionDetector.removeCollidable(g);
//                panelManager.setActivePanel(GamePanelManager.OPTIONS_PANEL);
//                break;
//            case KeyEvent.VK_1:
//                archer.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
//                break;
//            case KeyEvent.VK_2:
//                archer.setShieldDelta(Delta.create(-1.0, ModType.FIXED));
//                break;
//            default:
//                break;
//        }
//    }
//    
//    @Override
//    public void keyReleased(KeyEvent e)
//    {
//        archer.removeAnimator = archer.animator;
//        switch (e.getKeyCode())
//        {
//            case KeyEvent.VK_A:
//                archer.setLeft(false);
//                archer.animator = archer.animators.get(AnimationState.DEFAULT_LEFT);
//                break;
//            case KeyEvent.VK_D:
//                archer.setRight(false);
//                archer.animator = archer.animators.get(AnimationState.DEFAULT_RIGHT);
//                break;
//            case KeyEvent.VK_SPACE:
//                archer.setJump(false);
//              //archer.animator = archer.animators.get(AnimationState.DEFAULT_JUMP);
//                break;
//        }
//        archer.notifyAnimationChangeListeners();
//    }
//    
//    @Override
//    public void mousePressed(MouseEvent e)
//    {
//        super.mousePressed(e);
//        archer.setMousePressed(true);
//        archer.removeAnimator = archer.animator;
//
//        if(e.getX() > archer.PositionVector().x) {
//            archer.animator = archer.animators.get(AnimationState.SHOOTING_RIGHT);
//        } else {
//            archer.animator = archer.animators.get(AnimationState.SHOOTING_LEFT);
//        }
//        archer.notifyAnimationChangeListeners();
//    }
//    
//    @Override
//    public void mouseReleased(MouseEvent e)
//    {   
//        archer.removeAnimator = archer.animator;
//        if(archer.animator == archer.animators.get(AnimationState.SHOOTING_RIGHT))
//            archer.animator = archer.animators.get(AnimationState.DEFAULT_RIGHT);
//        else if(archer.animator == archer.animators.get(AnimationState.SHOOTING_LEFT))
//            archer.animator = archer.animators.get(AnimationState.DEFAULT_LEFT);
//        archer.notifyAnimationChangeListeners();
//        if(archer.canShootArrow())
//        {
//            CharacterAnimationBase arrow = null;
//            DoubleVector velocity = VectorMath.getVelocityVector(new DoubleVector(e.getX() - archer.PositionVector().getX(), e.getY() - archer.PositionVector().getY()), 15 + archer.getMouseCharge() * 3);
//            //velocity = new DoubleVector(velocity.x + archer.getVelocityVector().x, velocity.y + archer.getVelocityVector().y); //Player Momentum transfers to arrow
//            if(archer.animator == archer.animators.get(AnimationState.DEFAULT_RIGHT))
//                arrow = this.createNewArrow(gameData, archer.PositionVector(), velocity, "assets/Archer/Arrow_Shot_Right.png");
//            else
//                arrow = this.createNewArrow(gameData, archer.PositionVector(), velocity, "assets/Archer/Arrow_Shot_Left.png");
//            this.layeredPane.add(arrow, layeredPane.highestLayer());
//            archer.shootArrow();
//            archer.setMousePressed(false);
//        }
//        else
//        {
//            //Something? Idk
//        }
//    }
//
//    private void SwitchArcherListener(ActionEvent event) {
//        Animatable obj = (Animatable) event.getSource();
//        this.layeredPane.remove(obj.getRemoveAnimator());
//        this.layeredPane.add(obj.getAnimator());
//    }
//
//    @Override
//    public void panelManagerChanged() {
////        for(Collidable g : bigGhost)                  this does not run. My guess is the panelManager never changes.
////            collisionDetector.removeCollidable(g);
//    }
//
//    private void SwitchGhostListener(ActionEvent event) {
//        Animatable obj = (Animatable) event.getSource();
//        this.layeredPane.remove(obj.getRemoveAnimator());
//        this.layeredPane.add(obj.getAnimator());
//    }
//    
}
