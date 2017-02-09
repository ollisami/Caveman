package caveman;
//http://zetcode.com/tutorials/javagamestutorial/movingsprites/
import caveman.map.MapController;
import caveman.map.Map;
import caveman.avatar.AvatarController;
import caveman.gfx.GraphicsController;

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
        System.out.println("repaint");
        this.graphicsController.paintMap();
    }

    public void createMap() {
        mapController.createNewMap(50);
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
    
    public void keyPressed(int y, int x){
        System.out.println("PRESSED y:" + y + " x:" + x);
        this.avatarController.moveAvatars(y,x);
        rePaint();
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
