package CPSC340;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class EvenOrOddTestingTest {

    private EvenOrOddTesting game;

    @BeforeEach
    void setUp() {
        game = new EvenOrOddTesting();
    }

    @Test
    void testAssignComputerRole_Even() {
        assertEquals("Odd", game.assignComputerRole("Even"));
    }

    @Test
    void testAssignComputerRole_Odd() {
        assertEquals("Even", game.assignComputerRole("Odd"));
    }

    @Test
    void testGetPlayerChoice_ValidEven() {
        String input = "Even\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        assertEquals("Even", game.getPlayerChoice(scanner));
    }

    @Test
    void testGetPlayerChoice_NoAgreement() {
        String input = "No agreement\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        String playerChoice = game.getPlayerChoice(scanner);
        assertTrue(playerChoice.equals("Even") || playerChoice.equals("Odd"));
    }

    @Test
    void testGetPlayerChoice_InvalidThenValid() {
        String input = "Invalid\nOdd\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        assertEquals("Odd", game.getPlayerChoice(scanner));
    }

    @Test
    void testGetBestOfRounds_Valid() {
        String input = "3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        assertEquals(3, game.getBestOfRounds(scanner));
    }

    @Test
    void testGetBestOfRounds_InvalidThenValid() {
        String input = "4\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        assertEquals(5, game.getBestOfRounds(scanner));
    }

    @Test
    void testCapitalize() {
        assertEquals("Even", game.capitalize("even"));
        assertEquals("Odd", game.capitalize("oDd"));
        assertEquals("", game.capitalize(""));
    }

    @Test
    void testPlayRound() {
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
    
        String playerChoice = "Even";
        game.playRound(scanner, playerChoice);
    
        assertTrue(game.getPlayerScore() >= 0);
        assertTrue(game.getComputerScore() >= 0);
    }
    
    @Test
    void testCheckAndCorrectScoring() {
        game.checkAndCorrectScoring(true, "Even");
        assertTrue(game.getPlayerScore() >= 0);
        assertTrue(game.getComputerScore() >= 0);
    }
    
}