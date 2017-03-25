package goldteam.characters;

import goldteam.domain.ClickHandler;
import goldteam.domain.Delta;
import goldteam.domain.Depletable;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import goldteam.domain.ModType;
import goldteam.domain.Weapon;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ArcherBow
        extends GameObject
        implements Weapon,
        Depletable,
        ClickHandler {

    private Boolean mousePressed;
    private Integer charge;
    private Integer arrowCount;
    private final ArrayList<ActionListener> weaponListeners;
    private final ArrayList<ActionListener> depletableListeners;

    public ArcherBow(GameEngine gamedata, Point initialPoint) {
        super(gamedata, initialPoint);
        this.weaponListeners = new ArrayList<>();
        this.depletableListeners = new ArrayList<>();
        this.arrowCount = 100;

    }

// <editor-fold defaultstate="collapsed" desc="GameObject Implementation">
    @Override
    protected void Update() {
        this.positionVector = this.gamedata.getMovableCharacter().PositionVector();
    }

    @Override
    protected void GraphicsUpdateHandler() {
        // Weapon does not have an associated animator
    }

    @Override
    protected void UpdateEffectHandler() {
        // ArcherBow does not handle the effect timer
    }

    @Override
    protected void MapUpdateTimerHandler() {
        // ArcherBow does not handle the map update timer
    }
// </editor-fold>

//<editor-fold defaultstate="collapsed" desc="Weapon Implementation">
    @Override
    public void addWeaponListener(ActionListener listener) {
        this.weaponListeners.add(listener);
    }

    @Override
    public void notifyWeaponListener() {
        ActionEvent e = new ActionEvent(this, 0, null);
        weaponListeners.forEach((weaponListener) -> {
            weaponListener.actionPerformed(e);
        });
    }

    @Override
    public double getChargeValue() {
        return this.charge;
    }

    @Override
    public void setChargeValue(double chargeValue) {
        this.charge = ((Double) chargeValue).intValue();
    }

    @Override
    public void setChargeDelta(Delta chargeDelta) {
        this.charge += chargeDelta.delta.intValue();
        this.notifyWeaponListener();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Depletable Implementation">
    @Override
    public Integer getCount() {
        return this.arrowCount;
    }

    @Override
    public void setCountDelta(Delta delta) {
        this.arrowCount += delta.delta.intValue();
        this.notifyDepletableListeners();
    }

    @Override
    public void addDepletableListener(ActionListener listener) {
        this.depletableListeners.add(listener);
    }

    @Override
    public void notifyDepletableListeners() {
        ActionEvent e = new ActionEvent(this, 0, null);
        depletableListeners.forEach((depletableListener) -> {
            depletableListener.actionPerformed(e);
        });
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="ClickHandler Implementation">
    @Override
    public void processMouseInput() {
        if (mousePressed && charge < 20) {
            this.setChargeDelta(Delta.create(1.0, ModType.FIXED));
        }
    }
//</editor-fold>
}
