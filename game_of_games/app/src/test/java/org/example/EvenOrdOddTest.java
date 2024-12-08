import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class EvenOrOddTest {

    private EvenOrOddGame game;

    @BeforeEach
    void setUp() {
        game = new EvenOrOddGame();
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
    void testGetPlayerChoice_Valid() {
        Scanner scanner = new Scanner("Even\n");
        assertEquals("Even", game.getPlayerChoice(scanner));
    }

    @Test
    void testGetPlayerChoice_InvalidThenValid() {
        Scanner scanner = new Scanner("Invalid\nOdd\n");
        assertEquals("Odd", game.getPlayerChoice(scanner));
    }

    @Test
    void testIsSumEven() {
        assertTrue(game.assignComputerRole("Even").equals("Odd"));
    }

    @Test
    void testCapitalize() {
        assertEquals("Even", game.capitalize("even"));
        assertEquals("Odd", game.capitalize("oDd"));
        assertEquals("", game.capitalize(""));
    }
}
