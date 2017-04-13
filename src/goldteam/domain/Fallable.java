package goldteam.domain;

/**
 *
 * @author Joshua
 */
public interface Fallable
{
    public void fall();
    
    public void land(double yPos);
    
    public int getOffset();
}
