package engine;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {
    private final boolean[] keys = new boolean[66568];
    private Direction lastDir = Direction.right;
    private boolean left, right, up, down;

    public void update() {
        this.left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        this.right = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_RIGHT];
        this.up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        this.down = keys[KeyEvent.VK_R] || keys[KeyEvent.VK_DOWN];
    }

    public Direction getDir() {
        this.update();
        if (this.left && lastDir != Direction.right) {
            lastDir = Direction.left;
            return Direction.left;
        }
        if (this.right && lastDir != Direction.left) {
            lastDir = Direction.right;
            return Direction.right;
        }
        if (this.up && lastDir != Direction.down) {
            lastDir = Direction.up;
            return Direction.up;
        }
        if (this.down && lastDir != Direction.up) {
            lastDir = Direction.down;
            return Direction.down;
        }
        return lastDir;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}