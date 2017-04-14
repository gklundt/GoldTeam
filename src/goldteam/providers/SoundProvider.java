package goldteam.providers;

import goldteam.domain.GameObject;
import goldteam.domain.SoundBuilderBase;

public class SoundProvider {

    public GameObject build(SoundBuilderBase builder, Object watchable) {
        builder.createGameObject(watchable);
        return builder.getGameObject();
    }
}
