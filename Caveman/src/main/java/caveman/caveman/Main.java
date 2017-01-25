package caveman.caveman;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        MapController mapController = new MapController();
        mapController.createNewMap(10);
        mapController.addRoom(0, 5, 5);

        mapController.createNewMap(20);
        mapController.addRoom(1, 3, 3);
        mapController.addRoom(1, 3, 3);
        mapController.addRoom(1, 3, 3);

        mapController.createNewMap(30);
        mapController.addRoom(2, 6, 6);
        mapController.addRoom(2, 10, 10);
        mapController.addRoom(2, 3, 3);

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
