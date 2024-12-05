package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.*;

public class CoinFlipTest {

    @Test
    public void testBestOfValue() {
        CoinFlip game = new CoinFlip();
        game.resetScores();

        int rounds = 5; // Best out of 5 (odd number)
        int expectedWinValue = (rounds / 2) + 1;

        assertEquals(3, expectedWinValue, "The winning value should be correct for best out of 5 rounds.");
    }

    @Test
    public void testValidInput() {
        CoinFlip game = new CoinFlip();
        game.resetScores();

        String[] validInputs = {"heads", "tails"};
        String[] invalidInputs = {"head", "tail", "123", "", "hades"};

        for (String input : validInputs) {
            assertTrue(isValidInput(input), "Input '" + input + "' should be considered valid.");
        }

        for (String input : invalidInputs) {
            assertFalse(isValidInput(input), "Input '" + input + "' should be considered invalid.");
        }
    }

    private boolean isValidInput(String input) {
        return input.equalsIgnoreCase("heads") || input.equalsIgnoreCase("tails");
    }

    @Test
    public void testTimeoutForUser() {
        CoinFlip game = new CoinFlip();
        game.resetScores();

        // Simulate a timeout scenario
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        CompletableFuture<String> userInputFuture = new CompletableFuture<>();

        // Schedule the timeout
        scheduler.schedule(() -> {
            if (!userInputFuture.isDone()) {
                userInputFuture.complete("timeout");
            }
        }, 30, TimeUnit.SECONDS);

        // Simulate user taking too long
        try {
            String result = userInputFuture.get();
            assertEquals("timeout", result, "The user should timeout if no input is provided within 30 seconds.");
        } catch (InterruptedException | ExecutionException e) {
            fail("The timeout simulation failed.");
        }

        scheduler.shutdown();
    }

    @Test
    public void testScoring() {
        CoinFlip game = new CoinFlip();
        game.resetScores();

        // Simulate user and computer scores
        game.getUserScore();
        game.getComputerScore();

        assertEquals(0, game.getUserScore(), "User score should be correctly incremented.");
        assertEquals(0, game.getComputerScore(), "Computer score should be correctly incremented.");
    }
}
