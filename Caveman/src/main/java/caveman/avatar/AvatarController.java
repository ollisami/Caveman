package caveman.avatar;

import caveman.GameController;
import caveman.map.Map;
import caveman.map.Room;
import java.util.ArrayList;
import java.util.List;

public class AvatarController {

    private GameController gameController;
    private Avatar player;
    private List<Avatar> enemies;

    public AvatarController(GameController gameController) {
        this.gameController = gameController;
        this.enemies = new ArrayList<>();
    }

    public void spawnCharacters() {
        Map map = gameController.getCurrentMap();
        Room r = map.getEmptyRoom();
        if (r == null) {
            System.out.println("ERR! Empty room!");
            return;
        }
        this.player = new Player(3, r.getCenterY(), r.getCenterX(), 100);
        map.setData(player.getPosY(), player.getPosX(), player.getSpriteValue());

        int enemyAmount = 4; //Change this!-----------------------------------
        while (enemies.size() < enemyAmount) {
            Room room = map.getEmptyRoom();
            if (room == null) {
                System.out.println("Could not found room for enemy :(");
                break;
            }
            Enemy e = new Enemy(4, room.getCenterY(), room.getCenterX(), 100);
            enemies.add(e);
            map.setData(e.getPosY(), e.getPosX(), e.getSpriteValue());
        }
    }

    public Avatar getPlayer() {
        return this.player;
    }

    public List<Avatar> getEnemies() {
        return this.enemies;
    }
}
