/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

//import goldteam.collectables.CollectableArrows;
//import goldteam.Collectables.Health;
//import goldteam.collectables.Shields;
//import goldteam.collectables.CollectableArrows;
import goldteam.characters.Ghost;
import goldteam.characters.StationaryGhost;
import goldteam.colliders.CollisionDetector;
import goldteam.domain.GameObject;
import goldteam.domain.GamePanelBase;
import goldteam.domain.PanelManager;
import goldteam.gamedata.GameData;
import java.awt.Point;
import java.util.ArrayList;
import goldteam.platforms.FlatPlatform;
import goldteam.builders.CollectableArrowBuilder;
import goldteam.builders.ArrowHudBuilder;
import goldteam.builders.CollectableHealthBoostBuilder;
import goldteam.builders.HeartHudBuilder;
import goldteam.builders.CollectableShieldBuilder;
import goldteam.builders.ShieldHudBuilder;
import goldteam.characters.ArcherMan;
import goldteam.characters.Arrow;
import goldteam.colliders.CollectableArrowCollider;
import goldteam.domain.Platform;
import goldteam.colliders.PlatformCollider;
import goldteam.colliders.CollectableShieldCollider;
import goldteam.hud.ArrowHudItem;
import goldteam.hud.HeartHudItem;
import goldteam.hud.ShieldHudItem;
import goldteam.builders.CollectableHealthBuilder;
import goldteam.builders.CollectableLifeBuilder;
import goldteam.builders.CollectablePermanentWeaponBoostBuilder;
import goldteam.builders.WeaponBoostStatusBarBuilder;
import goldteam.builders.CollectableWeaponBoostBuilder;
import goldteam.builders.FlatPlatformBuilder;
import goldteam.builders.FlyerEnemyBuilder;
import goldteam.builders.HealthBoostStatusBarBuilder;
import goldteam.builders.HorizontalPlatformBuilder;
import goldteam.builders.LauncherEnemyBuilder;
import goldteam.builders.LifeHudBuilder;
import goldteam.builders.PitPlatformBuilder;
import goldteam.builders.WalkerEnemyBuilder;
import goldteam.colliders.ArcherCollider;
import goldteam.colliders.CollectableHealthBoostCollider;
import goldteam.colliders.CollectableHealthCollider;
import goldteam.colliders.CollectableLifeCollider;
import goldteam.colliders.CollectablePermanentWeaponBoostCollider;
import goldteam.colliders.CollectableWeaponBoostCollider;
import goldteam.colliders.EnemyArrowCollider;
import goldteam.domain.Delta;
import goldteam.domain.ModType;

/**
 *
 * @author fchishti
 */
public class TestCollidersPanel extends GamePanelBase
{
    public TestCollidersPanel(PanelManager panelManager) {
        super(panelManager, new GameData());
    }

    @Override
    protected void addGameObjects() {
        this.spawnPoint = new Point(200, 0);
        super.addGameObjects();

        CollectableArrowCollider ac = new CollectableArrowCollider();
        addGameObject(ac);

        CollectableShieldCollider sc = new CollectableShieldCollider();
        addGameObject(sc);

        CollectableHealthCollider hc = new CollectableHealthCollider();
        addGameObject(hc);

        CollectableLifeCollider lc = new CollectableLifeCollider();
        addGameObject(lc);
        
        CollectableWeaponBoostCollider wbc = new CollectableWeaponBoostCollider();
        addGameObject(wbc);
        
        CollectableHealthBoostCollider hbc = new CollectableHealthBoostCollider();
        addGameObject(hbc);
        
        CollectablePermanentWeaponBoostCollider pwbc = new CollectablePermanentWeaponBoostCollider();
        addGameObject(pwbc);
        
        PlatformCollider pc = new PlatformCollider();
        addGameObject(pc);
        
        EnemyArrowCollider ec = new EnemyArrowCollider();
        addGameObject(ec);

        ArcherCollider arc = new ArcherCollider();
        addGameObject(arc);
        
       /* this.collectableBuilder = new CollectableArrowBuilder(gameData);
        addGameObject(this.collectableProvider.build(collectableBuilder, new Point(200, 300), gameData.getAttackableCharacter()));
        this.collectableBuilder = new CollectableShieldBuilder(gameData);
        addGameObject(this.collectableProvider.build(collectableBuilder, new Point(100, 300), gameData.getAttackableCharacter()));*/

        this.collectableBuilder = new CollectableHealthBuilder(gameData);
        addGameObject(this.collectableProvider.build(collectableBuilder, new Point(250, 900), gameData.getAttackableCharacter()));

        this.collectableBuilder = new CollectableWeaponBoostBuilder(gameData);
        addGameObject(this.collectableProvider.build(collectableBuilder, new Point(200, 900), gameData.getAttackableCharacter()));
        
        this.collectableBuilder = new CollectableLifeBuilder(gameData);
        addGameObject(this.collectableProvider.build(collectableBuilder, new Point(150, 900), gameData.getAttackableCharacter()));
        
        this.collectableBuilder = new CollectableHealthBoostBuilder(gameData);
        addGameObject(this.collectableProvider.build(collectableBuilder, new Point(100, 900), gameData.getAttackableCharacter()));
        
        this.collectableBuilder = new CollectablePermanentWeaponBoostBuilder(gameData);
        addGameObject(this.collectableProvider.build(collectableBuilder, new Point(50, 900), gameData.getAttackableCharacter()));

        hudBuilder = new ShieldHudBuilder(gameData);
        addGameObject(hudProvider.build(hudBuilder, new Point(10, 30), gameData.getAttackableCharacter()));

        hudBuilder = new ArrowHudBuilder(gameData);
        addGameObject(hudProvider.build(hudBuilder, new Point(10, 70), archerWeapon));

        hudBuilder = new HeartHudBuilder(gameData);
        addGameObject(hudProvider.build(hudBuilder, new Point(10, 10), gameData.getAttackableCharacter()));
        
        hudBuilder = new WeaponBoostStatusBarBuilder(gameData);
        addGameObject(hudProvider.build(hudBuilder, new Point(650, 10), gameData.getAttackableCharacter()));
        
        hudBuilder = new HealthBoostStatusBarBuilder(gameData);
        addGameObject(hudProvider.build(hudBuilder, new Point(650, 30), gameData.getAttackableCharacter()));

        hudBuilder = new LifeHudBuilder(gameData);
        addGameObject(hudProvider.build(hudBuilder, new Point(10, 50), gameData.getDepletableCharacter()));

        gameData.getAttackableCharacter().setHealthDelta(Delta.create(-1.0, ModType.FIXED));
        gameData.getAttackableCharacter().setShieldDelta(Delta.create(-1.0, ModType.FIXED));
        gameData.getDepletableCharacter().setCountDelta(Delta.create(-1.0, ModType.FIXED));

        this.platformBuilder = new FlatPlatformBuilder(gameData);
        addGameObject(this.platformProvider.build(platformBuilder, new Point(0, 928), 450, 250));
        
        this.platformBuilder = new PitPlatformBuilder(gameData);
        addGameObject(this.platformProvider.build(platformBuilder, new Point(450, 928), 100, 250));
        
        this.platformBuilder = new FlatPlatformBuilder(gameData);
        addGameObject(this.platformProvider.build(platformBuilder, new Point(550, 928), 1200, 250));
        
        
        this.gameObjectBuilder = new WalkerEnemyBuilder(gameData);
        this.addGameObject(gameObjectProvider.build(gameObjectBuilder, new Point(500, 600)));
//        
//        this.gameObjectBuilder = new LauncherEnemyBuilder(gameData);
//        this.addGameObject(gameObjectProvider.build(gameObjectBuilder, new Point(400, 200)));
//        
//        this.gameObjectBuilder = new FlyerEnemyBuilder(gameData, this); //Passed so Flyers can create Launchers
//        this.addGameObject(gameObjectProvider.build(gameObjectBuilder, new Point(400, 100)));
//        
//        
        //Little platform
        this.platformBuilder = new PitPlatformBuilder(gameData);
        addGameObject(this.platformProvider.build(platformBuilder, new Point(310, 775), 10, 10));
        this.platformBuilder = new HorizontalPlatformBuilder(gameData);
        addGameObject(this.platformProvider.build(platformBuilder, new Point(320, 775), 50, 10));
        this.platformBuilder = new PitPlatformBuilder(gameData);
        addGameObject(this.platformProvider.build(platformBuilder, new Point(370, 775), 10, 10));
        
        this.platformBuilder = new PitPlatformBuilder(gameData);
        addGameObject(this.platformProvider.build(platformBuilder, new Point(310, 825), 10, 10));
        this.platformBuilder = new HorizontalPlatformBuilder(gameData);
        addGameObject(this.platformProvider.build(platformBuilder, new Point(320, 825), 50, 10));
        this.platformBuilder = new PitPlatformBuilder(gameData);
        addGameObject(this.platformProvider.build(platformBuilder, new Point(370, 825), 10, 10));
    }
}
