package goldteam.domain;

import java.awt.event.ActionListener;

interface Fighter {

    public Integer getStrength();

    public void setStrengthDelta(Delta delta);
    
    public void addFighterListener(ActionListener listener);

}