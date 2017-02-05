package goldteam.domain;

/**
 * Archer interface exposes methods for phases of an archer attack
 * Ready should set the initial trajectory of the weapon
 * The initial trajectory should be a unit vector
 * @author gordon
 */
public interface Archer {

    public void ready();

    public void aim();

    public void fire();

}
