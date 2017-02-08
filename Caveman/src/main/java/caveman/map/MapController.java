package caveman.map;

import caveman.map.Map;
import java.util.ArrayList;
import java.util.List;

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
        this.maps.add(new Map(size));
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
