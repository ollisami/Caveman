package caveman.avatar;

import caveman.GameController;
import caveman.map.Map;
import caveman.map.Room;
import java.util.ArrayList;
import java.util.List;

/**
 * Hallitsee pelin avatareita.
 *
 * @version 1.0
 * @author Sami Ollila
 */
public class AvatarController {

    private GameController gameController;
    private Avatar player;
    private List<Avatar> enemies;
    private int enemyCount = 20;

    public AvatarController(GameController gameController) {
        this.gameController = gameController;
        this.enemies = new ArrayList<>();
    }

    /**
     * Luo pelaajan, viholliset ym. Avatarit tyhjiin huoneisiin
     *
     */
    public void spawnCharacters() {
        Map map = gameController.getCurrentMap();
        Room r = map.getEmptyRoom();
        if (r == null) {
            System.out.println("ERR! Empty room!");
            return;
        }
        this.player = new Player(3, r.getCenterY(), r.getCenterX(), 100);
        map.setData(player.getPosY(), player.getPosX(), player.getSpriteValue());

        while (enemies.size() < enemyCount) {
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

    /**
     * Liikuttaa avatareja.Aluksi liikutetaan pelaajaa parametrina annettuun
     * suuntaan, jonka jälkeen liikutetaan viholliset kohti pelaajaa.
     *
     * @param py pelaajan suunta y
     * @param px pelaajan suunta x
     */
    public void moveAvatars(int py, int px) {
        Map map = gameController.getCurrentMap();
        if (map.isWalkable(this.player.getPosY() + py, this.player.getPosX() + px, "player")) {
            map.setData(player.getPosY(), player.getPosX(), 1);
            this.player.move(py, px);
            map.setData(player.getPosY(), player.getPosX(), player.getSpriteValue());
        }
        py = this.player.getPosY();
        px = this.player.getPosX();
        for (Avatar e : this.enemies) {
            if (e.distanceTo(this.player) > 10) {
                continue;
            }
            int[] newPos = getDirection(map, e.getPosY(), e.getPosX(), this.player.getPosY(), this.player.getPosX());
            map.setData(e.getPosY(), e.getPosX(), 1);
            e.move(newPos[0], newPos[1]);
            map.setData(e.getPosY(), e.getPosX(), e.getSpriteValue());
        }
//            int x = 0;
//            int y = 0;
//            if (e.getPosY() != py) {
//                if (e.getPosY() < py) {
//                    y = 1;
//                } else if (e.getPosY() > py) {
//                    y = -1;
//                }
//                if (map.isWalkable(e.getPosY() + y, e.getPosX() + x, "enemy")) {
//                    map.setData(e.getPosY(), e.getPosX(), 1);
//                    e.move(y, x);
//                    map.setData(e.getPosY(), e.getPosX(), e.getSpriteValue());
//                    continue;
//                }
//            }
//            y = 0;
//            if (e.getPosX() < px) {
//                x = 1;
//            } else if (e.getPosX() > px) {
//                x = -1;
//            }
//            if (map.isWalkable(e.getPosY() + y, e.getPosX() + x, "enemy")) {
//                map.setData(e.getPosY(), e.getPosX(), 1);
//                e.move(y, x);
//                map.setData(e.getPosY(), e.getPosX(), e.getSpriteValue());
//            }
//        }

    }

    private int[] getDirection(Map map, int posY, int posX, int ty, int tx) {
        int[] p = new int[2];
        int smallest = 999;

        if (map.isWalkable(posY + 1, posX, "enemy") && smallest > distanceTo(posY+1, posX, ty, tx)) {
            smallest = distanceTo(posY+1, posX, ty, tx);
            p[0] = 1;
            p[1] = 0;
        }
        if (map.isWalkable(posY - 1, posX, "enemy") && smallest > distanceTo(posY-1, posX, ty, tx)) {
            smallest = distanceTo(posY-1, posX, ty, tx);
            p[0] = -1;
            p[1] = 0;
        }
        if (map.isWalkable(posY, posX+1, "enemy") && smallest > distanceTo(posY, posX+1, ty, tx)) {
            smallest = distanceTo(posY, posX+1, ty, tx);
            p[0] = 0;
            p[1] = 1;
        }
        if (map.isWalkable(posY, posX-1, "enemy") && smallest > distanceTo(posY, posX-1, ty, tx)) {
            smallest = distanceTo(posY, posX-1, ty, tx);
            p[0] = 0;
            p[1] = -1;
        }
        System.out.println(p[0]);
        System.out.println(p[1]);
        return p;
    }

    private int distanceTo(int cy, int cx, int ty, int tx) {
        int distX = Math.abs(cx - tx);
        int distY = Math.abs(cy - ty);
        return distX + distY;
    }

    /**
     * Palauttaa pelaaja avatarin
     *
     * @return pelaaja avatar
     */
    public Avatar getPlayer() {
        return this.player;
    }

    /**
     * Palauttaa listan vihollisista.
     *
     * @return lista vihollisista.
     */
    public List<Avatar> getEnemies() {
        return this.enemies;
    }

}
