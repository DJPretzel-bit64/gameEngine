import engine.*;

import java.awt.*;

public class Game implements Defaults {
    Engine engine;
    int WIDTH = 1920;
    int HEIGHT = 1080;
    double fps = 144.0;

    public static void main(String[] args) {
        Game game = new Game();
    }

    public Game() {
        this.engine = new Engine(this);
        this.engine.setWIDTH(this.WIDTH);
        this.engine.setHEIGHT(this.HEIGHT);
        this.engine.setFps(this.fps);
        this.engine.init();
    }

    @Override
    public void update(Input input) {
    }

    @Override
    public void render(Graphics2D g) {
    }
}
