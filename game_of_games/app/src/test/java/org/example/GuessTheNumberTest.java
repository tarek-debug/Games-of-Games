package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

class GuessTheNumberTest {

    @Test
    void testCorrectGuess() {
        GuessTheNumber game = new GuessTheNumber();
        
        // correct guess
        String input = "40\n60\n50\n"; 
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);  
        
        game.playGame();
        
        assertEquals(0, game.getGuesserScore(), "Guesser should win with the correct guess");
    }

    @Test
    void testIncorrectGuess() {
        GuessTheNumber game = new GuessTheNumber();
        
        // 40, 60, and 70 incorrect guesses)
        String input = "40\n60\n70\n"; // All incorrect guesses
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);  // Redirect the input to use the simulated input
        
        game.playGame();
        
        assertEquals(1, game.getComputerScore(), "Computer should win because the guesser didn't guess correctly");
    }

    @Test
    void testInvalidRange() {
        GuessTheNumber game = new GuessTheNumber();
        // user puts invalid range
        String input = "60\n50\n70\n"; // This is irrelevant since the range will be invalid
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        game.playGame();
        
        //range is invalid, then the computer wins
        assertEquals(1, game.getComputerScore(), "Computer should win due to invalid range");
    }
   
}


