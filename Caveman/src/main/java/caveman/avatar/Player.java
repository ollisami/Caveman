package caveman.avatar;

/**
 * Pelaaja avatar.
 *
 * @version 1.0
 * @author Sami Ollila
 */
public class Player extends Avatar {

    /**
     * Konstruktori.
     *
     * @param sprite kuva tiedoston id
     * @param y sijainti y
     * @param x sijainti x
     * @param name tyyppi
     */
    public Player(int sprite, int y, int x, String name) {
        super(sprite, y, x, name);
    }

}
