package caveman.avatar;

/**
 * Vihollis avatar.
 *
 * @version 1.0
 * @author Sami Ollila
 */
public class Enemy extends Avatar {

    /**
     * Konstruktori.
     *
     * @param sprite kuva tiedoston id
     * @param y sijainti y
     * @param x sijainti x
     * @param health elämää jäljellä.
     */
    public Enemy(int sprite, int y, int x, int health) {
        super(sprite, y, x, health);
    }

}
