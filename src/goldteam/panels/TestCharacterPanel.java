package goldteam.panels;

import goldteam.domain.PanelManager;
import goldteam.domain.GamePanelBase;
import goldteam.gamedata.GameData;
import goldteam.builders.FlyerEnemyBuilder;
import goldteam.builders.LauncherEnemyBuilder;
import goldteam.builders.WalkerEnemyBuilder;
import java.awt.Point;

public class TestCharacterPanel extends GamePanelBase {

    public TestCharacterPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
    }

    @Override
    protected void addGameObjects() {

        super.addGameObjects();

        gameObjectBuilder = new FlyerEnemyBuilder(gameData);
        this.addGameObject(gameObjectProvider.build(gameObjectBuilder, new Point(400, 100)));

        gameObjectBuilder = new WalkerEnemyBuilder(gameData);
        this.addGameObject(gameObjectProvider.build(gameObjectBuilder, new Point(200, 400)));

        gameObjectBuilder = new LauncherEnemyBuilder(gameData, true);
        this.addGameObject(gameObjectProvider.build(gameObjectBuilder, new Point(750, 350)));


    }

//    protected CharacterAnimationBase createNewArrow(GameData gd, Point p, DoubleVector speed, String image) {
//        Arrow arrow = new Arrow(gd, (Point) (p.clone()), speed);
//        CharacterAnimationBase ga1;
//        ga1 = new ArcherAnimationStanding(arrow, gd.getVisibleDimensions(), image);
//        arrow.setAnimator(ga1);
//        return ga1;
//    }
//    @Override
//    public void mouseReleased(MouseEvent e) {
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
}
