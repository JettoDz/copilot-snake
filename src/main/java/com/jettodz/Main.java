package com.jettodz;

import com.jettodz.model.Difficulty;
import com.jettodz.service.Game;
import com.jettodz.service.SnakeWaiter;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        // Select difficulty from JOptionPane
        var difficulty = Difficulty.resolveFromIndex(
                JOptionPane.showOptionDialog(null, "Select difficulty", "Difficulty",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                        new Object[] {Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD}, null)
        );

        var game = new Game(difficulty);
        var waiter = new SnakeWaiter(game, difficulty);
        waiter.start();
    }
}