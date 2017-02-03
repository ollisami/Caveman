package caveman;

public class GameController {

    private MapController mapController;
    private AvatarController charcterController;
    int currentMap = 0;

    public GameController() {
        this.mapController = new MapController();
        this.charcterController = new AvatarController(this);
    }

    public void createMap() {
        mapController.createNewMap(50);
        mapController.addRoom(0, 6, 6);
        mapController.addRoom(0, 10, 10);
        mapController.addRoom(0, 5, 5);
        mapController.addRoom(0, 6, 6);
        mapController.addRoom(0, 6, 6);
        mapController.addRoom(0, 10, 10);
        mapController.addRoom(0, 5, 5);
        mapController.addRoom(0, 6, 6);

        this.charcterController.spawnCharacters();
    }

    public void printCurrentMap() {
        Map m = getCurrentMap();
        System.out.println(m.toString());

    }

    public Map getCurrentMap() {
        if (!this.mapController.getMaps().isEmpty()) {
            return this.mapController.getMaps().get(currentMap);
        }
        return null;
    }

    public MapController getMapController() {
        return this.mapController;
    }

    public AvatarController getCharacterController() {
        return this.charcterController;
    }
}
