package com.jettodz.service;

import com.jettodz.events.SnakeKeyListener;
import com.jettodz.model.*;
import com.jettodz.model.Point;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class Game extends JFrame {
    private final GamePanel gamePanel;
    private final Board board;
    private Snake snake;
    private Point fruit;
    private boolean gameOver;
    private int score;
    private Difficulty difficulty;

    private JLabel gameOverLabel;
    private JLabel scoreLabel;
    private SnakeKeyListener listener;

    public Game(Difficulty difficulty) {
        // Initialize the
        this.difficulty = difficulty;
        board = new Board(20, 20);
        snake = new Snake(new Point(10, 10), Direction.RIGHT, board);
        listener = new SnakeKeyListener(snake.getDirection());
        fruit = new Point(new Random().nextInt(20 - 1), new Random().nextInt(20 - 1));

        // Basic elements
        setTitle("Snake");
        addKeyListener(listener);
        setSize(board.getWidth() * 10, board.getHeight() * 10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create the GamePanel, where the snake and fruit will be drawn
        gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);
        // Create the game over label
        gameOverLabel = new JLabel();
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(gameOverLabel, BorderLayout.SOUTH);
        // Create the score label
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(scoreLabel, BorderLayout.NORTH);
        // Finish setting up the frame
        pack();
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    /**
     * Update the game state
     */
    public void update() {
        // Update the game state
        if (!gameOver) {
            snake.setDirection(listener.getDirection());
            snake.move();
            // Check for collisions
            if (snake.checkCollision()) {
                gameOver = true;
            }
            // Check if the snake has eaten the fruit
            checkEat();
        }
        if (gameOver) {
            gameOverLabel.setText("Game Over!");
        }
        gamePanel.repaint();
    }

    /**
     * Check if the snake has eaten the fruit
     */
    private void checkEat() {
        Point head = snake.getBody().getFirst();
        if (head.getX() == fruit.getX() && head.getY() == fruit.getY()) {
            snake.grow();
            fruit = new Point(new Random().nextInt(20 - 1), new Random().nextInt(20 - 1));
            score++;
            scoreLabel.setText("Score: " + score);
        }
    }


    /**
     * Panel where the snake and fruit are drawn
     */
    private class GamePanel extends JPanel {

        public GamePanel() {
            // Initialized the board in oxford gray
            setOpaque(true);
            setBackground(difficulty.equals(Difficulty.HARD) ? new Color (50, 50, 50) : new Color(70, 70, 70));
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(board.getWidth() * 10, board.getHeight() * 10);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the snake
            g.setColor(gameOver ? Color.BLACK :
                    difficulty.equals(Difficulty.HARD) ? Color.BLUE : Color.GREEN);
            for (Point point : snake.getBody()) {
                g.fillRect(point.getX() * 10, point.getY() * 10, 10, 10);
            }

            // Draw the head of the snake in a different color to distinguish it if it already ate the fruit
            if (snake.getBody().size() > 1) {
                Point head = snake.getBody().getFirst();
                g.setColor(difficulty.equals(Difficulty.HARD) ? Color.CYAN : Color.WHITE);
                g.fillRect(head.getX() * 10, head.getY() * 10, 10, 10);
            }

            // Draw the fruit
            if (!gameOver) {
                g.setColor(difficulty.equals(Difficulty.HARD) ? Color.YELLOW : Color.RED);
                g.fillRect(fruit.getX() * 10, fruit.getY() * 10, 10, 10);
            }
        }
    }

    // getters and setters
    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Point getFruit() {
        return fruit;
    }

    public void setFruit(Point fruit) {
        this.fruit = fruit;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
