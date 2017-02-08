package caveman.map;

import caveman.map.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapController {

    private List<Map> maps;

    public MapController() {
        this.maps = new ArrayList<>();

    }

    public void createNewMap(int size) {
        if (size > 500) {
            size = 500;
        }
        if (size < 10) {
            size = 10;
        }
        Map m = new Map(size);
        Random rand = new Random();
        int rooms = rand.nextInt(10)+3;
        int i = 0;
        while (i < rooms) {
            m.addRoom(rand.nextInt(10)+4, rand.nextInt(10)+4);
            i++;
        }
        m.setWalls();
        this.maps.add(m);
    }

    public boolean addRoom(int index, int sizey, int sizex) {
        if (index > maps.size() - 1) {
            return false;
        }
        return maps.get(index).addRoom(sizey, sizex);
    }

    public List<Map> getMaps() {
        return this.maps;
    }
    
    public Map getMap(int index) {
        if(this.maps.size()-1 < index) {
            return null;
        }
        return this.maps.get(index);
    }

    public int getMapSize(int index) {
        if (index > maps.size() - 1) {
            return 0;
        }
        return maps.get(index).getMap().length;
    }

}
