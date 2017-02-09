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

    public void moveAvatars(int py, int px) {
        Map map = gameController.getCurrentMap();
        if (map.getData(this.player.getPosY() + py, this.player.getPosX() + px) == 1) {
            map.setData(player.getPosY(), player.getPosX(), 1);
            this.player.move(py, px);
            map.setData(player.getPosY(), player.getPosX(), player.getSpriteValue());
        }
        py = this.player.getPosY();
        px = this.player.getPosX();
        
        for (Avatar e : this.enemies) {
            int x = 0;
            int y = 0;
            if (e.getPosY() != py) {
                if (e.getPosY() < py) {
                    y = 1;
                } else if (e.getPosY() > py) {
                    y = -1;
                }
                if (map.getData(e.getPosY() + y, e.getPosX() + x) != 2) {
                    map.setData(e.getPosY(), e.getPosX(), 1);
                    e.move(y, x);
                    map.setData(e.getPosY(), e.getPosX(), e.getSpriteValue());
                    continue;
                }
            }
            if (e.getPosX() < px) {
                x = 1;
            } else if (e.getPosX() > px) {
                x = -1;
            }
            if (map.getData(e.getPosY() + y, e.getPosX() + x) != 2) {
                map.setData(e.getPosY(), e.getPosX(), 1);
                e.move(y, x);
                map.setData(e.getPosY(), e.getPosX(), e.getSpriteValue());
            }
        }
    }

    public Avatar getPlayer() {
        return this.player;
    }

    public List<Avatar> getEnemies() {
        return this.enemies;
    }
}
