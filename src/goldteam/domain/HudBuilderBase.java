package goldteam.domain;

public abstract class HudBuilderBase extends HudObjectBuilderBase {

    public HudBuilderBase(GameEngine gameData) {
        super(gameData);
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
