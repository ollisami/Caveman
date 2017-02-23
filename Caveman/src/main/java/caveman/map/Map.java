package caveman.map;

import caveman.avatar.Avatar;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Pelissä käytettävä kartta.
 *
 * @version 1.0
 * @author Sami Ollila
 */
public class Map {

    private int[][] map;
    private List<Room> rooms;

    /**
     * Konstruktori.
     *
     * @param size kartan koko
     */
    public Map(int size) {
        this.map = new int[size][size];
        resetMap();
        this.rooms = new ArrayList<>();
    }

    private void resetMap() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (y < 0 || x < 0) {
                    break;
                }
                setData(y, x, 0);
            }
        }
    }

    /**
     * Lisää uuden huoneen.
     *
     * @param r lisättävä huone
     * @return true jos huoneen lisäys onnistuu, muutoin false
     */
    public boolean addRoom(Room r) {
        int i = 0;
        Random rand = new Random();
        while (true) {
            if (!roomCollides(r)) {
                this.rooms.add(r);
                break;
            }
            r.setLeft(rand.nextInt(map.length - 1 - r.getWidth()));
            r.setTop(rand.nextInt(map.length - 1 - r.getHeight()));
            i++;
            if (i > 10) {
                return false;
            }
        }
        int[][] data = r.getRoom();
        for (int y = 0; y < r.getHeight(); y++) {
            for (int x = 0; x < r.getWidth(); x++) {
                setData(y + r.getTop(), x + r.getLeft(), data[y][x]);
            }
        }
        if (this.rooms.size() > 1) {
            createCorridore(r, this.rooms.get(this.rooms.size() - 2));
        }
        return true;
    }

    private boolean roomCollides(Room r) {
        for (Room other : this.rooms) {
            if (r.collidesWith(other)) {
                return true;
            }
        }
        return false;
    }

    private void createCorridore(Room a, Room b) {
        int x = a.getCenterX();
        int y = a.getCenterY();
        while (x != b.getCenterX()) {
            setData(y, x, groundValue(y, x));
            setData(y + 1, x + 1, groundValue(y + 1, x + 1));
            setData(y - 1, x - 1, groundValue(y - 1, x - 1));
            setData(y + 2, x + 2, groundValue(y + 2, x + 2));
            setData(y - 2, x - 2, groundValue(y - 2, x - 2));
            if (x < b.getCenterX()) {
                x += 1;
                continue;
            }
            x += -1;
        }

        while (y != b.getCenterY()) {
            setData(y, x, groundValue(y, x));
            setData(y + 1, x + 1, groundValue(y + 1, x + 1));
            setData(y - 1, x - 1, groundValue(y + 1, x + 1));
            setData(y + 2, x + 2, groundValue(y + 1, x + 1));
            setData(y - 2, x - 2, groundValue(y + 1, x + 1));
            if (y < b.getCenterY()) {
                y += 1;
                continue;
            }
            y += -1;
        }
    }

    private int groundValue(int x, int y) {
        Random rand = new Random();
        double a = rand.nextDouble();
        if (a > 0.95) {
            return 5;
        }
        return 1;
    }

    private boolean isWalkable(int y, int x) {
        return !(getData(y, x) == 0 || getData(y, x) == 2);
    }

    /**
     * Kertoo voiko ruutuun astua.
     *
     * @param y sijainti y
     * @param x sijainti x
     * @param type avatarin tyyppi.
     * @return true jos ruutuun voi astua, muutoin false.
     */
    public boolean isWalkable(int y, int x, String type) {
        if (type.equals("enemy") && getData(y, x) == 5) {
            return false;
        }
        if (type.equals("enemy") && getData(y, x) == 4) {
            return false;
        }
        if (type.equals("enemy") && getData(y, x) == 7) {
            return false;
        }
        if (type.equals("enemy") && getData(y, x) == 3) {
            return true;
        }
        return isWalkable(y, x);
    }

    /**
     * Luo seinät huoneiden / käytävien ympärille.
     */
    public void setWalls() {
        for (int y = 0; y < this.map.length; y++) {
            for (int x = 0; x < this.map.length; x++) {
                if (isBorder(y, x)) {
                    setData(y, x, 2);
                }
            }
        }
    }

    private boolean isBorder(int y, int x) {
        if (getData(y, x) == 1 || getData(y, x) == 5) {
            if (getData(y + 1, x) == 0
                    || getData(y - 1, x) == 0
                    || getData(y, x + 1) == 0
                    || getData(y, x - 1) == 0
                    || getData(y - 1, x - 1) == 0
                    || getData(y + 1, x + 1) == 0
                    || getData(y - 1, x + 1) == 0
                    || getData(y + 1, x - 1) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Asettaa ruudun datan.
     *
     * @param y sijainti y
     * @param x sijainti x
     * @param val uusi arvo
     */
    public void setData(int y, int x, int val) {
        if (y < 0 || y >= this.map.length
                || x < 0 || x >= this.map[y].length) {
            return;
        }
        this.map[y][x] = val;
    }

    /**
     * Palauttaa ruudun datan.
     *
     * @param y sijainti y
     * @param x sijainti x
     * @return data
     */
    public int getData(int y, int x) {
        if (y < 0 || y >= this.map.length
                || x < 0 || x >= this.map.length) {
            return 0;
        }
        return this.map[y][x];
    }

    /**
     * Palauttaa kartan.
     *
     * @return map
     */
    public int[][] getMap() {
        return this.map;
    }

    /**
     * Palauttaa kartasta sen osan jonka pelaaja näkee.
     *
     * @param player pelaajan avatar
     * @param size kuinka iso kartta halutaan
     * @return kartto size*size jossa pelaaja on keskipisteessä
     */
    public int[][] getPlayerView(Avatar player, int size) {
        int[][] newMap = new int[size][size];
        if (player == null) {
            System.out.println("Player is empty");
            return newMap;
        }
        int orginX = player.getPosX() - (size / 2);
        int orginY = player.getPosY() - (size / 2);
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                newMap[y][x] = getData(orginY + y, orginX + x);
            }
        }
        return newMap;
    }

    /**
     * Palauttaa huoneen jossa ei ole avatareja.
     *
     * @param index indeksi
     * @return palauttaa huoneen jos sellainen löytyy, muutoin null.
     */
    public Room getRoom(int index) {
        if (this.rooms.isEmpty() || this.rooms.size() <= index) {
            return null;
        }
        return this.rooms.get(index);
    }

    /**
     * Kertoo kuinka hyvä valinta ruutuun astuminen on.
     *
     * @param y sijainti y
     * @param x sijainti x
     *
     * @return hinta
     */
    public int getCostToEnter(int y, int x) {
        int val = getData(y, x);
        int p = 0;
        if (val == 0 || val == 2 || val == 4 || val == 7) {
            p = 100;
        }
        if (val == 1 || val == 3) {
            p = 1;
        }
        if (val == 5) {
            p = 5;
        }
        return p;
    }

    @Override
    public String toString() {
        setWalls();
        String p = "";
        for (int y = 0; y < this.map.length; y++) {
            for (int x = 0; x < this.map[y].length; x++) {
                p += map[y][x];
            }
            p += "\n";
        }
        return p;
    }
}
