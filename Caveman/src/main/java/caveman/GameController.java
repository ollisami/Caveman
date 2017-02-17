package caveman;

import caveman.map.MapController;
import caveman.map.Map;
import caveman.avatar.AvatarController;
import caveman.gfx.GraphicsController;

/**
 * Hallitsee peliä ja muita controllereita.
 *
 * @version 1.0
 * @author Sami Ollila
 */
public class GameController {

    private MapController mapController;
    private AvatarController avatarController;
    private GraphicsController graphicsController;
    private int currentMap = 0;
    private int mapSize = 50;
    private int playerViewSize = 20;

    /**
     * Konstruktori.
     *
     */
    public GameController() {
        this.mapController = new MapController();
        this.avatarController = new AvatarController(this);
        this.graphicsController = new GraphicsController(this);
    }

    /**
     * Luodaan uusi kartta.
     *
     */
    public void createMap() {
        mapController.createNewMap(mapSize);
        this.avatarController.spawnCharacters();
    }

    /**
     * Apumetodi nykyisen kartan tulostamiseksi konsoliin.
     *
     */
    public void printCurrentMap() {
        Map m = getCurrentMap();
        System.out.println(m.toString());

    }

    /**
     * Palauttaa nykyisen kartan.
     *
     * @see mapController.getMap(int)
     * @return Map
     */
    public Map getCurrentMap() {
        return this.mapController.getMap(currentMap);
    }

    /**
     * Palauttaa nykyisen kartan id:n.
     *
     * @return int id
     */
    public int getCurrentMapID() {
        return currentMap;
    }

    /**
     * Palauttaa sen osan kartasta jonka pelaaja voi nähdä kerralla.
     *
     * @see caveman.map.Map.getPlayerView(Avatar, int)
     * @return kartta pelaajan näkökentästä.
     */
    public int[][] getPlayerView() {
        Map current = getCurrentMap();
        return current.getPlayerView(avatarController.getPlayer(), playerViewSize);
    }

    /**
     * Välittää avatarControllerille tiedon mihin suuntaan pelaaja tahtoo
     * liikkua.
     *
     * @param y y koordinaatti johon pelaaja tahtoo liikkua
     * @param x x koordinaatti johon pelaaja tahtoo liikkua
     *
     * @see caveman.avatar.AvatarController.moveAvatars(int, int)
     */
    public void keyPressed(int y, int x) {
        this.avatarController.moveAvatars(y, x);
        rePaint();
    }

    /**
     * Palauttaa mapControllerin.
     *
     * @return mapController
     */
    public MapController getMapController() {
        return this.mapController;
    }

    /**
     * Palauttaa avatarControllerin.
     *
     * @return mapController
     */
    public AvatarController getAvatarController() {
        return this.avatarController;
    }

    /**
     * Palauttaa graphicsControllerin.
     *
     * @return mapController
     */
    public GraphicsController getGraphicsController() {
        return this.graphicsController;
    }

    /**
     * Piirretään näytettävä JPanel uudestaan.
     *
     */
    public void rePaint() {
        this.graphicsController.paint(getPlayerView());
    }
}
