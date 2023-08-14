package engine;

import java.awt.*;

public class Entity {
    public Position position;
    int width, height;

    public Entity(Position position, int width, int height) {
        this.width = width;
        this.height = height;
        this.position = position;
    }

    public void render(Graphics g) {
        g.drawRect((int) this.position.x, (int) this.position.y, this.width, this.height);
    }
}
