package caveman.map;

import java.util.ArrayList;
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
    private int roomCount = 200;

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
        size = Math.min(5000, size);
        size = Math.max(50, size);
        Map m = new Map(size);
        Random rand = new Random();
        int rooms = rand.nextInt(roomCount) + 3;
        int count = 0;
        int tries = 0;
        while (count < rooms) {
            int rsy = rand.nextInt(10) + 5;
            int rsx = rand.nextInt(10) + 5;
            int rLeft = rand.nextInt(size - 1 - rsx);
            int rTop = rand.nextInt(size - 1 - rsy);
            if (m.addRoom(new Room(rLeft, rTop, rsx, rsy))) {
                count++;
                continue;
            }
            tries++;
            if (tries > 100) {
                System.out.println("Error! something went wrong creating the map.");
                break;
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
