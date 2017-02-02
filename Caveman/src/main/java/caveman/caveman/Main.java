package caveman.caveman;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        MapController mapController = new MapController();

        mapController.createNewMap(50);
        mapController.addRoom(0, 6, 6);
        mapController.addRoom(0, 10, 10);
        mapController.addRoom(0, 5, 5);
        mapController.addRoom(0, 6, 6);
        mapController.addRoom(0, 10, 10);
        mapController.addRoom(0, 5, 5);

        List<Map> maps = mapController.getMaps();

        int i = 1;
        for (Map m : maps) {
            System.out.println("Map " + i + ": ");
            System.out.println(m.toString());
            System.out.println("");
            i++;
        }
    }

}
