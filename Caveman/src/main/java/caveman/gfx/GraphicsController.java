package caveman.gfx;

import caveman.GameController;
import javax.swing.JFrame;

/**
 * Hallitsee ruudulla esitettäviä grafiikoita.
 *
 * @version 1.0
 * @author Sami Ollila
 */
public class GraphicsController {

    private JFrame frame;
    private CanvasPanel canvas;

    /**
     * Konstruktori.
     *
     * @param gc GameControlleri
     */
    public GraphicsController(GameController gc) {
        this.canvas = new CanvasPanel();
        setFrame(gc);
    }

    /**
     * Uudelleen piirto.
     *
     * @param map kartta joka piirretään.
     */
    public void paint(int[][] map) {
        this.canvas.paintMap(map);
    }

    private void setFrame(GameController gc) {
        JFrame jframe = new JFrame();
        jframe.addKeyListener(new IsKeyPressed(gc));
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(this.canvas);
        jframe.setSize(1000, 1000);
        jframe.setResizable(false);
        jframe.setFocusable(true);
        jframe.setVisible(true);
        this.frame = jframe;
    }

    /**
     * Uudelleen JFramen.
     *
     * @return JFrame
     */
    public JFrame getFrame() {
        return this.frame;
    }
}
