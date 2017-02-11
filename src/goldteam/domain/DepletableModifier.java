package goldteam.domain;

import java.awt.event.ActionListener;

public interface DepletableModifier extends Modifier {

    public Delta getCountDelta();

    public void setCountDelta(Delta delta);
    
    public void addDepletableListener(ActionListener listener);

}
