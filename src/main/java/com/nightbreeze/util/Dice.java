package com.nightbreeze.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

public class Dice {

    private static final Random rand = new Random();

    // Pattern for the dice
    private static final Pattern patDice = Pattern.compile("(\\d+)[dD](\\d+)([+-]\\d+)?");

    // Roll the dice (integer format)
    public static int roll(int dice) {
        if (dice <= 0) {
            return 0;
        }
        return rand.nextInt(dice) + 1;
    }

    // Roll the dice (classic format: 1d10, 1d8, etc.)
    public static int roll(String dice) {
        Matcher m = patDice.matcher(dice);

        if (!m.matches()) {
            return 0;
        }

        try {
            int numberDice = Integer.parseInt(m.group(1));
            int sideDice = Integer.parseInt(m.group(2));
            int modifier = 0;

            if (m.group(3) != null) {
                modifier = Integer.parseInt(m.group(3));
            }

            if (sideDice <= 0 || numberDice <= 0) {
                System.err.println("Invalid dice value: " + dice);
                return 0;
            }

            int total = 0;
            for (int i = 0; i < numberDice; i++) {
                total += roll(sideDice);
            }

            return total + modifier;
        } catch (NumberFormatException e) {
            System.err.println("Invalid dice value: " + dice);
            return 0;
        }
    }

    // Roll d20 (because d20 is a pretty common roll in DnD)
    public static int rollD20(int modifier) {
        return roll(20) + modifier;
    }

    // Alert Dialog with the roll result
    public static void showRollResult(String title, int result, String details) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(details + ": " + result);
        alert.showAndWait();
    }
}
