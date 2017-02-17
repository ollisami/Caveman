package caveman;

public class Main {

    /**
     * Main metodi.
     *
     * @param args argumentit
     */
    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.createMap();
        gameController.rePaint();
    }

}
