package caveman;

public class Main {

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.createMap();
        gameController.printCurrentMap();
    }

}