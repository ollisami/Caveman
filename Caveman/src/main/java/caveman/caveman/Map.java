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
                setData(y, x, 0);
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
            this.rooms.add(r);
        } else {
            return false;
        }
        int[][] data = r.getRoom();
        for (int y = 0; y < r.getHeight(); y++) {
            for (int x = 0; x < r.getWidth(); x++) {
                setData(y + r.getTop(), x + r.getLeft(), data[y][x]);
            }
        }
        if (this.rooms.size() > 1) {
            createCorridore(r, this.rooms.get(this.rooms.size() - 2));
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

    private void createCorridore(Room a, Room b) {
        int x = a.getCenterX();
        int y = a.getCenterY();

        while (x != b.getCenterX()) {
            setData(y, x, 1);
            setData(y + 1, x + 1, 1);
            setData(y - 1, x - 1, 1);
            setData(y + 2, x + 2, 1);
            setData(y - 2, x - 2, 1);
            if (x < b.getCenterX()) {
                x += 1;
                continue;
            }
            x += -1;
        }

        while (y != b.getCenterY()) {
            setData(y, x, 1);
            setData(y + 1, x + 1, 1);
            setData(y - 1, x - 1, 1);
            setData(y + 2, x + 2, 1);
            setData(y - 2, x - 2, 1);
            if (y < b.getCenterY()) {
                y += 1;
                continue;
            }
            y += -1;
        }
    }

    private void setWalls() {
        for (int y = 0; y < this.map.length; y++) {
            for (int x = 0; x < this.map.length; x++) {
                if (isBorder(y, x)) {
                    setData(y, x, 2);
                }
            }
        }
    }

    private boolean isBorder(int y, int x) {
//        if (map[y][x] == 2 && x - 1 > 0 && x + 1 < this.map[y].length && y - 1 > 0 && y + 1 < this.map.length) {
//            if (map[y][x + 1] == 1 && map[y][x - 1] == 1) {
//                return true;
//            } else if (map[y + 1][x] == 1 && map[y - 1][x] == 1) {
//                return true;
//            }
//            return false;
//        }

        if (getData(y, x) == 1) {
            if (getData(y + 1, x) == 0
                    || getData(y - 1, x) == 0
                    || getData(y, x + 1) == 0
                    || getData(y, x - 1) == 0) {
                return true;
            }
        }
        return false;
    }

    public void setData(int y, int x, int val) {
        if (y < 0 || y >= this.map.length) {
            System.out.println("ERR!");
            return;
        }
        if (x < 0 || x >= this.map[y].length) {
            System.out.println("ERR!");
            return;
        }
        this.map[y][x] = val;
    }

    public int getData(int y, int x) {
        if (y < 0 || y >= this.map.length || x < 0 || x >= this.map.length) {
            return 0;
        }
        return this.map[y][x];
    }

    public int[][] getMap() {
        return this.map;
    }

    @Override
    public String toString() {
        setWalls();
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
