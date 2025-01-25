import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame implements Runnable {

    public Graphics2D g2;
    public KL keyListener = new KL();
    public ML mouseListener = new ML();
    public Text startGame, exitGame, pong;
    public boolean isRunning = true;

    public Rect hzLine;
    public Rect vtLine;

    public MainMenu() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

        hzLine = new Rect(
                Constants.SCREEN_WIDTH / 2.0,
                0,
                1,
                Constants.SCREEN_HEIGHT,
                Constants.PADDLE_COLOR);

        vtLine = new Rect(
                0,
                Constants.SCREEN_HEIGHT / 2.0,
                Constants.SCREEN_WIDTH,
                1,
                Constants.PADDLE_COLOR);

        this.pong = new Text("Pong", new Font("Times New Roman", Font.PLAIN, 100),
                Constants.SCREEN_WIDTH / 2.0 - 100, 230, Color.WHITE);
        this.startGame = new Text("Start Game", new Font("Times New Roman", Font.PLAIN, 40),
                Constants.SCREEN_WIDTH / 2.0 - 90.0, Constants.SCREEN_HEIGHT / 2.0, Color.WHITE);
        this.exitGame = new Text("Exit", new Font("Times New Roman", Font.PLAIN, 40),
                Constants.SCREEN_WIDTH / 2.0 - 35.0, Constants.SCREEN_HEIGHT / 2.0 + 60, Color.WHITE);

        g2 = (Graphics2D) getGraphics();
    }

    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);

        if (mouseListener.getMouseX() > startGame.x && mouseListener.getMouseX() < startGame.x + startGame.width /2.25 &&
                mouseListener.getMouseY() > startGame.y - startGame.height / 1.25 &&
                mouseListener.getMouseY() < startGame.y) {
            startGame.color = new Color(159, 159, 159);

            if (mouseListener.isMousePressed()) {
                startGame.color = new Color(230, 230, 59);
                Main.changeState(1);
            }
        } else {
            startGame.color = Color.WHITE;
        }

        if (mouseListener.getMouseX() > exitGame.x && mouseListener.getMouseX() < exitGame.x + exitGame.width /2.25 &&
                mouseListener.getMouseY() > exitGame.y - exitGame.height / 1.25 &&
                mouseListener.getMouseY() < exitGame.y) {
            exitGame.color = new Color(159, 159, 159);

            if (mouseListener.isMousePressed()) {
                exitGame.color = new Color(230, 230, 59);
                Main.changeState(2);
                System.exit(1);
            }
        } else {
            exitGame.color = Color.WHITE;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        hzLine.draw(g2);
        vtLine.draw(g2);
        pong.draw(g2);
        startGame.draw(g2);
        exitGame.draw(g2);
    }

    public void stop(){
       isRunning = false;
    }

    @Override
    public void run() {
        double lastFrameTime = 0.0;
        while (isRunning) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);

        }
        this.dispose();
        return;
    }
}
