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
    private List<Avatar> avatars;

    /**
     * Konstruktori.
     *
     * @param gameController gameControlleri
     *
     */
    public AvatarController(GameController gameController) {
        this.gameController = gameController;
        this.avatars = new ArrayList<>();
    }

    /**
     * Luo pelaajan, viholliset ym. Avatarit tyhjiin huoneisiin
     *
     */
    public void spawnCharacters() {
        Map map = gameController.getCurrentMap();
        Room r = map.getRoom(0);
        if (r == null) {
            System.out.println("ERR! Empty room!");
            return;
        }
        avatars.clear();
        avatars.add(new Avatar(6, r.getCenterY(), r.getCenterX(), "prevladder"));
        Avatar prevLadder = avatars.get(0);
        this.player = new Player(3, r.getCenterY() + 1, r.getCenterX(), "player");
        map.setData(prevLadder.getPosY(), prevLadder.getPosX(), prevLadder.getSpriteValue());
        map.setData(player.getPosY(), player.getPosX(), player.getSpriteValue());

        r = map.getRoom(1);
        avatars.add(new Avatar(6, r.getCenterY(), r.getCenterX(), "nextladder"));
        Avatar nextLadder = avatars.get(1);
        map.setData(nextLadder.getPosY(), nextLadder.getPosX(), nextLadder.getSpriteValue());

        for (int i = 2; i < gameController.getCurrentMapID() * 2 + 3; i++) {
            r = map.getRoom(i);
            if (r == null) {
                System.out.println("Could not found room for enemy :(");
                break;
            }
            Avatar e = new Enemy(4, r.getCenterY(), r.getCenterX(), "enemy");
            avatars.add(e);
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
        int curMapId = gameController.getCurrentMapID();
        if (map.isWalkable(this.player.getPosY() + py, this.player.getPosX() + px, "player")) {
            map.setData(player.getPosY(), player.getPosX(), player.getPrevData());
            this.player.move(py, px);
            player.setPrevData(map.getData(player.getPosY(), player.getPosX()));
            map.setData(player.getPosY(), player.getPosX(), player.getSpriteValue());
            for (Avatar a : collidesWith(player)) {
                if (a.getType().equals("enemy")) {
                    System.out.println("rip");
                }
                if (a.getType().equals("nextladder")) {
                    System.out.println("Next level!");
                }
                if (a.getType().equals(("prevladder"))) {
                    System.out.println("prev level");
                }
            }
        }
        for (Avatar e : this.avatars) {
            if (!e.getType().equals("enemy") || e.distanceTo(this.player) > 10) {
                continue;
            }
            int[] newPos = getDirection(map, e.getPosY(), e.getPosX(), this.player.getPosY(), this.player.getPosX());
            map.setData(e.getPosY(), e.getPosX(), e.getPrevData());
            e.move(newPos[0], newPos[1]);
            e.setPrevData(map.getData(e.getPosY(), e.getPosX()));
            map.setData(e.getPosY(), e.getPosX(), e.getSpriteValue());
        }
    }

    private List<Avatar> collidesWith(Avatar a) {
        List<Avatar> list = new ArrayList<>();
        for (Avatar b : avatars) {
            if (b.getPosX() == a.getPosX() && b.getPosY() == a.getPosY()) {
                list.add(b);
            }
        }
        return list;
    }

    private int[] getDirection(Map map, int posY, int posX, int ty, int tx) {
        int[] p = new int[2];
        int smallest = 999;
        int dist = distanceTo(map, posY + 1, posX, ty, tx);
        if (map.isWalkable(posY + 1, posX, "enemy") && smallest > dist) {
            smallest = dist;
            p[0] = 1;
            p[1] = 0;
        }
        dist = distanceTo(map, posY - 1, posX, ty, tx);
        if (map.isWalkable(posY - 1, posX, "enemy") && smallest > dist) {
            smallest = dist;
            p[0] = -1;
            p[1] = 0;
        }
        dist = distanceTo(map, posY, posX + 1, ty, tx);
        if (map.isWalkable(posY, posX + 1, "enemy") && smallest > dist) {
            smallest = dist;
            p[0] = 0;
            p[1] = 1;
        }
        dist = distanceTo(map, posY, posX - 1, ty, tx);
        if (map.isWalkable(posY, posX - 1, "enemy") && smallest > dist) {
            p[0] = 0;
            p[1] = -1;
        }
        return p;
    }

    private int distanceTo(Map map, int cy, int cx, int ty, int tx) {
        int sumyx = 0;
        for (int y = Math.min(cy, ty); y <= Math.max(cy, ty); y++) {
            sumyx += map.getCostToEnter(y, cx);
        }
        for (int x = Math.min(cx, tx); x <= Math.max(cx, tx); x++) {
            sumyx += map.getCostToEnter(ty, x);
        }
        int sumxy = 0;
        for (int x = Math.min(cx, tx); x <= Math.max(cx, tx); x++) {
            sumxy += map.getCostToEnter(cy, x);
        }
        for (int y = Math.min(cy, ty); y <= Math.max(cy, ty); y++) {
            sumxy += map.getCostToEnter(y, tx);
        }

        return Math.min(sumyx, sumxy);
    }

    /**
     * Palauttaa pelaaja avatarin.
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
    public List<Avatar> getAvatars() {
        return this.avatars;
    }

}
