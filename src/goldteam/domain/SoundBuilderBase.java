package goldteam.domain;

public abstract class SoundBuilderBase extends SoundObjectBuilderBase {

    public SoundBuilderBase(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void buildTemplate() {
        this.createObject();
        this.setWatcher();
    }

    protected abstract void createObject();

    protected abstract void setWatcher();

    protected abstract void play();
    
    protected abstract void stop();

}
