package caveman.caveman;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Map {

    private int[][] map;
    private List<Room> rooms;

    public Map(int size) {
        this.map = new int[size][size];
        // alustetaan kartta
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                this.map[y][x] = 0;
            }
        }
        rooms = new ArrayList<>();
    }

    public boolean addRoom(int rsy, int rsx) {
        Random rand = new Random();
        if (rsy > map.length - 1 || rsx > map.length - 1) {
            return false;
        }
        int rLeft = rand.nextInt(map.length - 1 - rsx);
        int rTop = rand.nextInt(map.length - 1 - rsy);
        int rWidth = rsx;
        int rHeight = rsy;

        Room r = new Room(rLeft, rTop, rWidth, rHeight);
        if (!roomCollides(r)) {
            rooms.add(r);
        } else {
            return false;
        }
        int[][] data = r.getRoom();
        for (int y = 0; y < r.getHeight(); y++) {
            for (int x = 0; x < r.getWidth(); x++) {
                this.map[y + r.getTop()][x + r.getLeft()] = data[y][x];
            }
        }
        return true;
        //TODO: rooms dont go all the way right, fix it
    }

    private boolean roomCollides(Room r) {
        for (Room other : this.rooms) {
            if (r.collidesWith(other)) {
                return true;
            }
        }
        return false;
    }

    public int[][] getMap() {
        return this.map;
    }

    @Override
    public String toString() {
        String p = "";
        for (int y = 0; y < this.map.length; y++) {
            for (int x = 0; x < this.map[y].length; x++) {
                p += map[y][x];
            }
            p += "\n";
        }
        return p;
    }
}
