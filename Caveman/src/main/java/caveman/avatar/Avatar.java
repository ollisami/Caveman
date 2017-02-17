package caveman.avatar;

/**
 * Pääryhmä pelin eri avatareille.
 *
 * @version 1.0
 * @author Sami Ollila
 */
public class Avatar {

    private int spriteVal;
    private int posX;
    private int posY;
    private int health;

    /**
     * Konstruktori.
     *
     * @param spriteVal kuvatiedoston id arvo
     * @param y y sijainti
     * @param x x sijainti
     * @param health elämää jäljellä
     *
     */
    public Avatar(int spriteVal, int y, int x, int health) {
        this.spriteVal = spriteVal;
        this.posX = x;
        this.posY = y;
        this.health = health;
    }

    /**
     * Muuttaa avatarin sijaintia.
     *
     * @param y y arvon muutos
     * @param x x arvon muutos
     *
     */
    public void move(int y, int x) {
        y = Math.min(1, y);
        x = Math.min(1, x);
        this.posX += x;
        this.posY += y;
    }

    /**
     * Palauttaa sijainnin x.
     *
     * @return sijainnin x-koordinaatti
     */
    public int getPosX() {
        return this.posX;
    }

    /**
     * Palauttaa sijainnin y.
     *
     * @return sijainnin y-koordinaatti
     */
    public int getPosY() {
        return this.posY;
    }

    /**
     * Palauttaa health arvon.
     *
     * @return health arvo
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Palauttaa avatarin kuvan id-numeron.
     *
     * @return kuvan id-numero
     */
    public int getSpriteValue() {
        return this.spriteVal;
    }

    /**
     * Laskee matkan toiseen avatariin.
     *
     * @param other toinen avatari
     *
     * @return matka toiseen avatariin
     */
    public int distanceTo(Avatar other) {
        int distX = Math.abs(posX - other.getPosX());
        int distY = Math.abs(posY - other.getPosY());
        return distX + distY;
    }
}
