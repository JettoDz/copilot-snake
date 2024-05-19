package com.jettodz.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.jettodz.model.Direction;

public class SnakeKeyListener implements KeyListener {
    private Direction direction;

    public SnakeKeyListener(Direction initialDirection) {
        this.direction = initialDirection;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                direction = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                direction = Direction.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                direction = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                direction = Direction.RIGHT;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public Direction getDirection() {
        return direction;
    }
}