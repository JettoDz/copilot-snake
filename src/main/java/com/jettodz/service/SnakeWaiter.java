package com.jettodz.service;

import com.jettodz.model.Difficulty;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class SnakeWaiter {
    private Timer timer;
    private Difficulty difficulty;

    public SnakeWaiter(Game initialGame, Difficulty difficulty) {
        final Game[] game = new Game[1];
        game[0] = initialGame;
        this.difficulty = difficulty;

        timer = new Timer(difficulty.delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game[0].isGameOver()) {
                    stop();
                    int option = JOptionPane.showOptionDialog(null, "Game Over! What would you like to do next?", "Game Over",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {"Restart", "Exit"}, null);

                    if (option == 0) {
                        // Restart the game
                        var newDifficulty = Difficulty.resolveFromIndex(
                                JOptionPane.showOptionDialog(null, "Select difficulty", "Difficulty",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                                        new Object[] {Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD}, null)
                        );
                        game[0].setVisible(false);
                        game[0].dispose();
                        game[0] = new Game(newDifficulty);
                        timer.setDelay(newDifficulty.delay);
                        start();
                    } else {
                        game[0].dispatchEvent(new WindowEvent(game[0], WindowEvent.WINDOW_CLOSING));
                    }
                } else {
                    game[0].update();
                }
            }
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
}