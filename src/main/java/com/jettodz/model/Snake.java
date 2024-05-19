package com.jettodz.model;

import java.util.Deque;
import java.util.LinkedList;

public class Snake {
    private Deque<Point> body;
    private Direction direction;
    private final Board board;

    public Snake(Point start, Direction direction, Board board) {
        body = new LinkedList<>();
        body.add(start);
        this.direction = direction;
        this.board = board;
    }

    public void move() {
    Point head = body.getFirst();
    int newX = head.getX();
    int newY = head.getY();
    switch (direction) {
        case UP:
            newY = (newY - 1 + board.getHeight()) % board.getHeight();
            break;
        case DOWN:
            newY = (newY + 1) % board.getHeight();
            break;
        case LEFT:
            newX = (newX - 1 + board.getWidth()) % board.getWidth();
            break;
        case RIGHT:
            newX = (newX + 1) % board.getWidth();
            break;
    }
    body.addFirst(new Point(newX, newY));
    body.removeLast();
}

    public void grow() {
        Point tail = body.getLast();
        body.addLast(new Point(tail.getX(), tail.getY()));
    }

    public boolean checkCollision() {
        Point head = body.getFirst();
        //Check collision with body
        for (Point point : body) {
            if (point != head && point.getX() == head.getX() && point.getY() == head.getY()) {
                return true;
            }
        }

        //Check collision with walls
        return head.getX() < 0 || head.getX() >= board.getWidth() || head.getY() < 0 || head.getY() >= board.getHeight();
    }

    // getters and setters
    public Deque<Point> getBody() {
        return body;
    }

    public void setBody(Deque<Point> body) {
        this.body = body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction newDirection) {
        // Ignore if new direction is opposite to current direction
        if (direction.key % 2 != newDirection.key % 2)
            this.direction = newDirection;
    }

}