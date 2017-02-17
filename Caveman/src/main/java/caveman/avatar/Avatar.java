package caveman.avatar;

/**
 * Pääryhmä pelin eri avatareille.
 *
 * @version 1.0
 * @author Sami Ollila
 */
public class Avatar {

    private String type;
    private int spriteVal;
    private int posX;
    private int posY;
    private int prevdata;

    /**
     * Konstruktori.
     *
     * @param spriteVal kuvatiedoston id arvo
     * @param y y sijainti
     * @param x x sijainti
     * @param type tyyppi
     */
    public Avatar(int spriteVal, int y, int x, String type) {
        this.spriteVal = spriteVal;
        this.posX = x;
        this.posY = y;
        this.prevdata = 1;
        this.type = type;
    }

    /**
     * Aseta edellisen sijainnin grafiikka id.
     *
     * @param data arvo
     *
     */
    public void setPrevData(int data) {
        this.prevdata = data;
        if (data == 3) {
            this.prevdata = 1;
        }
    }

    /**
     * hae edellisen sijainnin grafiikka id.
     *
     * @return data
     */
    public int getPrevData() {
        return this.prevdata;
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

    /**
     * Palauttaa tyypin.
     *
     *
     * @return string tyyppi
     */
    public String getType() {
        return this.type;
    }

}
