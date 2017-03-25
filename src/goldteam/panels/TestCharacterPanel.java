package goldteam.panels;

import goldteam.domain.PanelManager;
import goldteam.domain.GamePanelBase;
import goldteam.characters.ArcherMan;
import goldteam.domain.DoubleVector;
import goldteam.domain.GameObjectBuilderBase;
import goldteam.gamedata.GameData;
import goldteam.providers.ArcherBuilder;
import goldteam.providers.ArrowBuilder;
import goldteam.providers.FlyerEnemyBuilder;
import goldteam.providers.GameObjectProvider;
import goldteam.providers.LauncherEnemyBuilder;
import goldteam.providers.WalkerEnemyBuilder;
import java.awt.Point;

public class TestCharacterPanel extends GamePanelBase {

    private ArcherMan ar;
    private int charge;

    public TestCharacterPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
        charge = 0;
    }

    @Override
    protected void addGameObjects() {

        GameObjectProvider provider = new GameObjectProvider();
        GameObjectBuilderBase builder;

        builder = new ArcherBuilder(this.gameData, new Point(400, 400));
        this.addGameObject(provider.build(builder));

        builder = new FlyerEnemyBuilder(gameData, new Point(400, 100));
        this.addGameObject(provider.build(builder));

        builder = new WalkerEnemyBuilder(gameData, new Point(200, 400));
        this.addGameObject(provider.build(builder));

        builder = new LauncherEnemyBuilder(gameData, new Point(750, 350), true);
        this.addGameObject(provider.build(builder));
        
        builder = new ArrowBuilder(gameData, new Point(0,0), new DoubleVector(10d,1d));
        this.addGameObject(provider.build(builder));

    }
//
//    protected CharacterAnimationBase createNewArrow(GameData gd, Point p, DoubleVector speed, String image) {
//        Arrow arrow = new Arrow(gd, (Point) (p.clone()), speed);
//        CharacterAnimationBase ga1;
//        ga1 = new ArcherAnimationStanding(arrow, gd.getVisibleDimensions(), image);
//        arrow.setAnimator(ga1);
//        return ga1;
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        super.keyPressed(e);
//
//        switch (e.getKeyCode()) {
//            case KeyEvent.VK_A:
//                ar.setLeft(true);
//                ar.removeAnimator = ar.animator;
//                ar.animator = ar.animators.get(AnimationState.WALKING_LEFT);
//                ar.notifyAnimationChangeListeners();
//                break;
//            case KeyEvent.VK_D:
//                ar.setRight(true);
//                ar.removeAnimator = ar.animator;
//                ar.animator = ar.animators.get(AnimationState.WALKING_RIGHT);
//                ar.notifyAnimationChangeListeners();
//                break;
//            case KeyEvent.VK_SPACE:
//                ar.setJump(true);
//                break;
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        ar.removeAnimator = ar.animator;
//        switch (e.getKeyCode()) {
//            case KeyEvent.VK_A:
//                ar.setLeft(false);
//                ar.animator = ar.animators.get(AnimationState.DEFAULT_LEFT);
//                break;
//            case KeyEvent.VK_D:
//                ar.setRight(false);
//                ar.animator = ar.animators.get(AnimationState.DEFAULT_RIGHT);
//                break;
//            case KeyEvent.VK_SPACE:
//                ar.setJump(false);
//                //ar.animator = ar.animators.get(AnimationState.DEFAULT_JUMP);
//                break;
//        }
//        ar.notifyAnimationChangeListeners();
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        super.mousePressed(e);
//        ar.setMousePressed(true);
//        ar.removeAnimator = ar.animator;
//
//        if (e.getX() > ar.PositionVector().x) {
//            ar.animator = ar.animators.get(AnimationState.SHOOTING_RIGHT);
//        } else {
//            ar.animator = ar.animators.get(AnimationState.SHOOTING_LEFT);
//        }
//        ar.notifyAnimationChangeListeners();
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//        ar.removeAnimator = ar.animator;
//        if (ar.animator == ar.animators.get(AnimationState.SHOOTING_RIGHT)) {
//            ar.animator = ar.animators.get(AnimationState.DEFAULT_RIGHT);
//        } else if (ar.animator == ar.animators.get(AnimationState.SHOOTING_LEFT)) {
//            ar.animator = ar.animators.get(AnimationState.DEFAULT_LEFT);
//        }
//        ar.notifyAnimationChangeListeners();
//        if (ar.canShootArrow()) {
//            CharacterAnimationBase arrow = null;
//            DoubleVector velocity = VectorMath.getVelocityVector(new DoubleVector(e.getX() - ar.PositionVector().getX(), e.getY() - ar.PositionVector().getY()), 15 + ar.getMouseCharge() * 3);
//            //velocity = new DoubleVector(velocity.x + ar.getVelocityVector().x, velocity.y + ar.getVelocityVector().y); //Player Momentum transfers to arrow
//            if (ar.animator == ar.animators.get(AnimationState.DEFAULT_RIGHT)) {
//                arrow = this.createNewArrow(gameData, ar.PositionVector(), velocity, "assets/Archer/ArrowRight.png");
//            } else {
//                arrow = this.createNewArrow(gameData, ar.PositionVector(), velocity, "assets/Archer/ArrowLeft.png");
//            }
//            this.layeredPane.add(arrow, layeredPane.highestLayer());
//            ar.shootArrow();
//            ar.setMousePressed(false);
//        } else {
//            //Something? Idk
//        }
//    }

//    private void SwitchArcherListener(ActionEvent event) {
//        Animatable obj = (Animatable) event.getSource();
//        this.layeredPane.remove(obj.getRemoveAnimator());
//        this.layeredPane.add(obj.getAnimator());
//    }

}
