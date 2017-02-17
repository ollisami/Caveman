package caveman.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Hallitsee pelin karttoja.
 *
 * @version 1.0
 * @author Sami Ollila
 */
public class MapController {

    private List<Map> maps;
    private int roomCount = 20;

    /**
     * Konstruktori.
     *
     */
    public MapController() {
        this.maps = new ArrayList<>();
    }

    /**
     * Luo uuden kartan.
     *
     * @param size uuden kartan koko
     *
     */
    public void createNewMap(int size) {
        size = Math.min(500, size);
        size = Math.max(20, size);
        Map m = new Map(size);
        Random rand = new Random();
        int rooms = rand.nextInt(roomCount) + 3;
        int count = 0;
        while (count < 2) {
            int rsy = rand.nextInt(10) + 4;
            int rsx = rand.nextInt(10) + 4;
            int rLeft = rand.nextInt(size - 1 - rsx);
            int rTop = rand.nextInt(size - 1 - rsy);
            Room r = new Room(rLeft, rTop, rsx, rsy);
            if (m.addRoom(r)) {
                r.setData(r.getCenterX(), r.getCenterY(), 7);
                count++;
            }
        }
        while (count < rooms) {
            int rsy = rand.nextInt(10) + 4;
            int rsx = rand.nextInt(10) + 4;
            int rLeft = rand.nextInt(size - 1 - rsx);
            int rTop = rand.nextInt(size - 1 - rsy);
            if (m.addRoom(new Room(rLeft, rTop, rsx, rsy))) {
                count++;
            }
        }
        m.setWalls();
        this.maps.add(m);
    }

    /**
     * Palauttaa listan kaikista kartoista.
     *
     *
     * @return lista kaikista kartoista
     */
    public List<Map> getMaps() {
        return this.maps;
    }

    /**
     * Palauttaa kartan indeksissä.
     *
     * @param index indeksi josta karttaa haetaan
     *
     * @return palauttaa kartan tai null jos karttaa ei ole.
     */
    public Map getMap(int index) {
        if (this.maps.size() - 1 < index) {
            return null;
        }
        return this.maps.get(index);
    }

    /**
     * Palauttaa kartan koon indeksissä.
     *
     * @param index indeksi josta karttaa haetaan.
     *
     * @return int kartan koko tai 0 jossa karttaa ei löydy
     */
    public int getMapSize(int index) {
        if (index > maps.size() - 1) {
            return 0;
        }
        return maps.get(index).getMap().length;
    }

}
