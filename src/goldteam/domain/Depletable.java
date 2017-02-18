package goldteam.domain;

import java.awt.event.ActionListener;

public interface Depletable {

    public Integer getCount();
    
    public void setCountDelta(Delta delta);
    
    public void addDepletableListener(ActionListener listener);

}