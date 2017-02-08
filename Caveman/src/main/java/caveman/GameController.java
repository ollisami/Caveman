package caveman;

import caveman.map.MapController;
import caveman.map.Map;
import caveman.avatar.AvatarController;
import caveman.gfx.GraphicsController;
import java.util.Random;

public class GameController {

    private MapController mapController;
    private AvatarController avatarController;
    private GraphicsController graphicsController;
    int currentMap = 0;

    public GameController() {
        this.mapController = new MapController();
        this.avatarController = new AvatarController(this);
        this.graphicsController = new GraphicsController(this);
    }
    
    public void rePaint() {
        this.graphicsController.repaint();
    }

    public void createMap() {
        mapController.createNewMap(50);
        Random rand = new Random();
        int rooms = rand.nextInt(10);
        int i = 0;
        while (i < rooms) {
            mapController.addRoom(0, rand.nextInt(10)+4, rand.nextInt(10)+4);
            i++;
        }
        this.avatarController.spawnCharacters();
    }

    public void printCurrentMap() {
        Map m = getCurrentMap();
        System.out.println(m.toString());

    }

    public Map getCurrentMap() {
        return this.mapController.getMap(currentMap);
    }
    
    public int[][] getPlayerView() {
        Map current = getCurrentMap();
        return current.getPlayerView(avatarController.getPlayer(), 10);
    }
    

    public MapController getMapController() {
        return this.mapController;
    }

    public AvatarController getCharacterController() {
        return this.avatarController;
    }
    
    public GraphicsController getGraphicsController() {
        return this.graphicsController;
    }
}
