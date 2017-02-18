package goldteam.panels;

import goldteam.animators.BigGhostAnimation;
import goldteam.domain.PanelManager;
import goldteam.domain.GamePanelBase;
import goldteam.animators.GhostAnimation;
import goldteam.characters.Ghost;
import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.Delta;
import goldteam.domain.ModType;
import goldteam.gamedata.GameData;
import java.awt.Point;
import java.util.Random;

public class GameEngineTestPanel extends GamePanelBase {

    public GameEngineTestPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
    }

    @Override
    protected void addGameObjects() {

        Random x = new Random();
        Random y = new Random();
        for (int i = 1; i <= 18; ++i) {

            int rx = ((Double) (x.nextDouble() * 500.00)).intValue() % 500;
            int ry = ((Double) (y.nextDouble() * 500.00)).intValue() % 500;

            CharacterAnimationBase ga1 = null;
            switch (i % 4) {
                case 0:
                    ga1 = this.createNewGhost(gameData, new Point(rx, ry), 15, "assets/GameGhostStripe.png", i);
                    break;
                case 1:
                    ga1 = this.createNewGhost(gameData, new Point(rx, ry), 5, "assets/GameGhostStripeRed.png", i);
                    break;
                case 2:
                    ga1 = this.createNewGhost(gameData, new Point(rx, ry), 10, "assets/GameGhostStripeOrange.png", i);
                    break;
                case 3:
                    ga1 = this.createNewGhost(gameData, new Point(rx, ry), 20, "assets/GameGhostStripeGreen.png", i);
                    break;
            }

            this.layeredPane.add(ga1, layeredPane.highestLayer());
        }
    }

    protected CharacterAnimationBase createNewGhost(GameData gd, Point p, Integer speed, String image, int bigOrSmall) {
        Ghost g1 = new Ghost(gd, p);
        CharacterAnimationBase ga1;
        if (bigOrSmall % 3 == 0) {
             ga1 = new BigGhostAnimation(g1, gd.getVisibleDimensions(), image);
        } else {
             ga1 = new GhostAnimation(g1, gd.getVisibleDimensions(), image);
        }
        g1.setVelocityScalarDelta(Delta.create((-0.9 * g1.getVelocity().doubleValue()) + speed, ModType.FIXED));
        g1.setAnimator(ga1);
        return ga1;
    }

}
