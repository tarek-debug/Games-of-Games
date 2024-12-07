package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class GameofGamesTest {

    @Test
    public void testMenuDisplay() {
        // Simulate user input for "6" (exit option)
        String simulatedInput = "6\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Capture system output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the program
        GameofGames.main(new String[]{});

        // Verify menu and exit message
        String output = outputStream.toString();
        assertTrue(output.contains("--- Welcome to the Game of Games ---"));
        assertTrue(output.contains("1. Coin Flip"));
        assertTrue(output.contains("2. Find the Thimble"));
        assertTrue(output.contains("3. Guess the Number"));
        assertTrue(output.contains("4. Even or Odd"));
        assertTrue(output.contains("5. Find the Red Thread"));
        assertTrue(output.contains("6. Exit"));
        assertTrue(output.contains("Thank you for playing! Goodbye!"));
    }

    @Test
    public void testInvalidInput() {
        // Simulate invalid input followed by "6" (exit)
        String simulatedInput = "0\n6\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Capture system output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the program
        GameofGames.main(new String[]{});

        // Verify invalid input message
        String output = outputStream.toString();
        assertTrue(output.contains("Invalid choice. Please try again."));
    }
}
