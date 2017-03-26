package goldteam.domain;

import java.awt.event.ActionListener;

public interface Removable {

    public void addRemovableListener(ActionListener listener);

    public void removeRemovableListener(ActionListener listener);
    
    public void markForRemoval();
    
    public void remove();

}
