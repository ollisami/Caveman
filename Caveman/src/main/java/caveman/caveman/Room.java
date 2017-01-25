package caveman.caveman;

public class Room {

    private int left;
    private int top;
    private int width;
    private int height;

    public Room(int left, int top, int width, int height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public int[][] getRoom() {
        int[][] p = new int[this.height][this.width];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                p[y][x] = 1;
            }
        }
        return p;
    }

    public int getTop() {
        return this.top;
    }

    public int getLeft() {
        return this.left;
    }

    public int getRight() {
        return left + width - 1;
    }

    public int getBottom() {
        return top + height - 1;
    }

    public int getCenterX() {
        return left + width / 2;
    }

    public int getCenterY() {
        return top - height / 2;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean collidesWith(Room other) {
        if (left > other.getRight() - 1
                || top > other.getBottom() - 1
                || getRight() < other.left + 1
                || getBottom() < other.top + 1) {
            return false;
        }
        return true;
    }
}
