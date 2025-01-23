import javax.swing.*;

public class Window extends JFrame implements Runnable {
    public Window(){
        this.setSize(800, 600);
        this.setTitle("Pong");
        this.setResizable(false);
        this.setVisible( true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void run() {
        while (true) {
            // do whatever
        }
    }
}
