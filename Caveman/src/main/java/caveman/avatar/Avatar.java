package caveman.avatar;

public class Avatar {

    private int spriteVal;
    private int posX;
    private int posY;
    private int health;

    public Avatar(int spriteVal, int y, int x, int health) {
        this.spriteVal = spriteVal;
        this.posX = x;
        this.posY = y;
        this.health = health;
    }

    public void move(int y, int x) {
        this.posX += x;
        this.posY +=y;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public int getHealth() {
        return this.health;
    }

    public int getSpriteValue() {
        return this.spriteVal;
    }
    
}
