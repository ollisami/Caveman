package caveman.map;

/**
 * Huone joista kartat rakentuvat.
 *
 * @version 1.0
 * @author Sami Ollila
 */
public class Room {

    private int left;
    private int top;
    private int width;
    private int height;
    private int[][] data;

    /**
     * Konstruktori.
     *
     * @param left vasemman reunan sijainti
     * @param top katon sijainti
     * @param width leveys
     * @param height korkeus
     */
    public Room(int left, int top, int width, int height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;

        data = new int[this.height][this.width];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                data[y][x] = 1;
            }
        }
    }

    /**
     * Aseta data.
     *
     * @param  x x arvo.
     * @param  y y arvo.
     * @param  val arvo
     */
    public void setData(int x, int y, int val) {
         System.out.println("x=" + x + " y= " + y);
         System.out.println("bottom: " + this.getBottom());
         System.out.println("left: " + this.getLeft());
         System.out.println("right: " + this.getRight());
         System.out.println("top: " + this.getTop());
        if (x < this.left || y > this.getBottom() || x > this.getRight()|| y < this.getTop()) {
            return;
        }
        System.out.println("here");
        this.data[y][x] = val;
       
    }

    /**
     * paluttaa huoneen taulukko muodossa.
     *
     * @return huone taulukko muodossa.
     */
    public int[][] getRoom() {
        return data;
    }

    /**
     * Palauttaa huoneen katon kordinaatin.
     *
     * @return huoneen katto
     */
    public int getTop() {
        return this.top;
    }

    /**
     * Palauttaa huoneen vasemman reunan kordinaatin.
     *
     * @return huoneen vasen reuna
     */
    public int getLeft() {
        return this.left;
    }

    /**
     * Palauttaa huoneen oikean reunan kordinaatin.
     *
     * @return huoneen oikea reuna
     */
    public int getRight() {
        return left + width - 1;
    }

    /**
     * Palauttaa huoneen pohjan kordinaatin.
     *
     * @return huoneen pohja
     */
    public int getBottom() {
        return top + height - 1;
    }

    /**
     * Palauttaa huoneen keskusta x-kordinaatin.
     *
     * @return huoneen keskusta x
     */
    public int getCenterX() {
        return left + width / 2;
    }

    /**
     * Palauttaa huoneen keskustan y-kordinaatin.
     *
     * @return huoneen keskusta
     */
    public int getCenterY() {
        return top + height / 2;
    }

    /**
     * Palauttaa huoneen korkeuden.
     *
     * @return huoneen korkeus
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Palauttaa huoneen leveys.
     *
     * @return huoneen leveys
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Tarkastaako törmääkö huone toiseen huoneeseen.
     *
     * @param other toinen huone johon törmäys tarkistetaan
     *
     * @return true jos huoneet törmäävät, muutoin false
     */
    public boolean collidesWith(Room other) {
        if (left > other.getRight() - 1
                || top > other.getBottom() - 1
                || getRight() < other.left + 1
                || getBottom() < other.top + 1) {
            return false;
        }
        return true;
    }

    /**
     * aseta left.
     *
     * @param newpos uusi arvo
     */
    public void setLeft(int newpos) {
        this.left = newpos;
    }

    /**
     * aseta top.
     *
     * @param newpos uusi arvo
     */
    public void setTop(int newpos) {
        this.top = newpos;
    }

}
