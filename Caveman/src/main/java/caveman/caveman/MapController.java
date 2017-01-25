package caveman.caveman;

import java.util.ArrayList;
import java.util.List;

public class MapController {

    private List<Map> maps;

    public MapController() {
        this.maps = new ArrayList<>();
    }

    public void createNewMap(int size) {
        if(size > 500) size = 500;
        if(size < 10) size = 10;
        this.maps.add(new Map(size));
    }
    
    public boolean addRoom(int index, int size_y, int size_x) {
        if(index > maps.size()-1) {
            return false;
        }
        return maps.get(index).addRoom(size_y, size_x);
    }
    
    public List<Map> getMaps() {
        return this.maps;
    }
    
    public int getMapSize(int index) {
        if(index > maps.size()-1) {
            return 0;
        }
        return maps.get(index).getMap().length;
    }

}
