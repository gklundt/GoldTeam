package goldteam.domain;

import java.awt.Point;

public abstract class HudBuilderBase extends GameObjectBuilderBase {

    public HudBuilderBase(GameEngine gameData, Point point) {
        super(gameData, point);
    }

    @Override
    protected void buildTemplate() {
        this.createObject();
        this.setWatcher();
        this.addAnimations();

    }

    protected abstract void createObject();

    protected abstract void setWatcher();

    protected abstract void addAnimations();

}
