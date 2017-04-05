package goldteam.animators;

import goldteam.domain.ChargeAnimationBase;
import goldteam.domain.Depletable;
import goldteam.domain.GameObject;
import goldteam.domain.Weapon;
import goldteam.domain.WeaponWatcher;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class ArrowChargeAnimation extends ChargeAnimationBase {

    private final WeaponWatcher gameObj;
    private final GameObject weaponLocationInterface;
    private final Depletable weaponDepleatableInterface;

    public ArrowChargeAnimation(GameObject gameObject, Dimension preferredSize, Weapon weapon) {
        super(gameObject, preferredSize, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0, 0.0));
        this.gameObj = (WeaponWatcher) gameObject;
        this.weaponLocationInterface = (GameObject) weapon;
        this.weaponDepleatableInterface = (Depletable) weapon;
    }

    @Override
    protected void update() {
        if (this.weaponDepleatableInterface.getCount() > 0) {
            this.chargeAmount = this.gameObj.getWatcher().getChargeValue();
            this.archerPosition = this.weaponLocationInterface.PositionVector();
        }
    }

}
