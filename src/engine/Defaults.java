package engine;

import java.awt.*;

public interface Defaults {
    int WIDTH = 800;
    int HEIGHT = 600;
    int fps = 60;
    String title = "Game";
    void update(Input input);
    void render(Graphics2D g);
}
