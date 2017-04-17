package goldteam.domain;

/**
 *
 * @author Joshua
 */
public interface Fallable
{
    public void startFalling();
    public void stopFalling(int y);
    public int getOffset();
}
