package caveman;

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
        int i = 0;
        while (i < 10) {
            Room r = map.getRandomRoom();
            if (r == null) {
                System.out.println("Could not found room for player :(");
                break;
            }
            if (map.getData(r.getCenterY(), r.getCenterX()) != 1) {
                i++;
                continue;
            }
            this.player = new Player(9, r.getCenterY(), r.getCenterX(), 100);
            map.setData(player.getPosY(), player.getPosX(), player.getSpriteValue());
            break;
        }

        int enemyAmount = 4;
        int j = 0;
        while (enemies.size() < enemyAmount && j <= 0) {
            Room r = map.getRandomRoom();
            if (r == null) {
                System.out.println("Could not found room for player :(");
                break;
            }
            if (map.getData(r.getCenterY(), r.getCenterX()) != 1) {
                j++;
                continue;
            }
            Enemy e = new Enemy(5, r.getCenterY(), r.getCenterX(), 100);
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
