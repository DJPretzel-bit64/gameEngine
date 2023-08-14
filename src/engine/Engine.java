package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;

public class Engine extends Canvas implements Runnable{
    @Serial
    private static final long serialVersionUID = 1L;

    public boolean running = false;
    private Input input;
    private Thread thread;
    private JFrame frame;
    Defaults userCode;
    int WIDTH;
    int HEIGHT;
    double fps;
    String title;

    public Engine(Defaults code) {
        this.userCode = code;
        this.WIDTH = code.WIDTH;
        this.HEIGHT = code.HEIGHT;
        this.fps = code.fps;
        this.title = code.title;
    }

    public void init() {
        this.frame = new JFrame();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.input = new Input();
        this.addKeyListener(input);
        this.frame.setTitle(title);
        this.frame.add(this);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
        this.start();
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public void setFps(double fps) {
        this.fps = fps;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private synchronized void start() {
        running = true;
        this.thread = new Thread(this, "engine.Engine");
        this.thread.start();
    }

    private synchronized void stop()  {
        running = false;
        try {
            this.thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / fps;
        double delta = 0;
        int frames = 0;

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                update();
                delta --;
                render();
                frames ++;
            }
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                this.frame.setTitle(title + " | " + frames + " fps");
                frames = 0;
            }
        }

        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        this.userCode.render(g);

        g.dispose();
        bs.show();
    }

    private void update() {
        this.userCode.update(this.input);
    }
}
