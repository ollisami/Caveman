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
        size = Math.max(10, size);
        Map m = new Map(size);
        Random rand = new Random();
        int rooms = rand.nextInt(roomCount) + 3;
        int i = 0;
        while (i < rooms) {
            m.addRoom(rand.nextInt(10) + 4, rand.nextInt(10) + 4);
            i++;
        }
        m.setWalls();
        this.maps.add(m);
    }

    /**
     * Lisää uuden huoneen.
     *
     * @param index kartan indeksi johon huone lisätään
     * @param sizey huoneen korkeus
     * @param sizex huoneen leveys
     * @see map.addRoom(int,int)
     * @return true jos lisäys onnistuu, muutoin false.
     */
    public boolean addRoom(int index, int sizey, int sizex) {
        if (index > maps.size() - 1) {
            return false;
        }
        return maps.get(index).addRoom(sizey, sizex);
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
