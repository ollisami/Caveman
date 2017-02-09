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

    public Map(int size) {
        this.map = new int[size][size];
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                setData(y, x, 0);
            }
        }
        rooms = new ArrayList<>();
    }

    /**
     * Lisää uuden huoneen.
     *
     * @param rsy huoneen korkeus
     * @param rsx huoneen leveys
     *
     * @return true jos huoneen lisäys onnistuu, muutoin false
     */
    public boolean addRoom(int rsy, int rsx) {
        if (rsy > map.length - 2 || rsx > map.length - 2) {
            return false;
        }
        Random rand = new Random();
        Room r = null;
        int i = 0;
        while (i < 10) {
            int rLeft = rand.nextInt(map.length - 1 - rsx);
            int rTop = rand.nextInt(map.length - 1 - rsy);
            int rWidth = rsx;
            int rHeight = rsy;

            r = new Room(rLeft, rTop, rWidth, rHeight);
            if (!roomCollides(r)) {
                this.rooms.add(r);
                break;
            }
            i++;
        }
        if (r == null) {
            return false;
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
            setData(y, x, 1);
            setData(y + 1, x + 1, 1);
            setData(y - 1, x - 1, 1);
            setData(y + 2, x + 2, 1);
            setData(y - 2, x - 2, 1);
            if (x < b.getCenterX()) {
                x += 1;
                continue;
            }
            x += -1;
        }

        while (y != b.getCenterY()) {
            setData(y, x, 1);
            setData(y + 1, x + 1, 1);
            setData(y - 1, x - 1, 1);
            setData(y + 2, x + 2, 1);
            setData(y - 2, x - 2, 1);
            if (y < b.getCenterY()) {
                y += 1;
                continue;
            }
            y += -1;
        }
    }

    /**
     * Luo seinät huoneiden / käytävien ympärille.
     *
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
        if (getData(y, x) == 1) {
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
     * Aseta arvo kartan kohtaa (y,x).
     *
     * @param y kartan kohta y
     * @param x kartan kohta x
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
     * Palauttaa arvon kartan kohdasta (y,x).
     *
     * @param y kartan kohta y
     * @param x kartan kohta x
     *
     * @return palauttaa arvon kohdassa (y,x) tai 0 jos virheelliset
     * koordinaatit.
     */
    public int getData(int y, int x) {
        if (y < 0 || y >= this.map.length
                || x < 0 || x >= this.map.length) {
            return 0;
        }
        return this.map[y][x];
    }

    /**
     * palauttaa koko kartan.
     *
     * @return kartta taulukko muodossa
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
            return null;
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
     * Palauttaa huoneen jossa ei ole avatareja
     *
     * @return palauttaa huoneen jos sellainen löytyy, muutoin null.
     */
    public Room getEmptyRoom() {
        if (this.rooms.isEmpty()) {
            return null;
        }
        List<Room> emptyRooms = new ArrayList<>();
        for (Room r : this.rooms) {
            if (roomIsEmpty(r)) {
                emptyRooms.add(r);
            }
        }
        if (emptyRooms.isEmpty()) {
            System.out.println("No epty rooms left");
            return null;
        }
        Random rand = new Random();
        int bound = emptyRooms.size();
        return emptyRooms.get(Math.max(rand.nextInt(bound) - 1, 0));
    }

    private boolean roomIsEmpty(Room r) {
        for (int y = r.getTop() + 1; y < r.getBottom(); y++) {
            for (int x = r.getLeft() + 1; x < r.getRight(); x++) {
                if (this.map[y][x] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * muuttaa huoneen tulostettavaan muotoon.
     *
     * @return huone tulostettavassa muodossa.
     */
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
