package goldteam.domain;

import java.awt.event.ActionListener;

public interface Objective {

    public boolean IsReached();

    public void setReached(Boolean reached);

    public void addObjectiveListener(ActionListener listener);

    public void removeObjectiveListener(ActionListener listener);
}
