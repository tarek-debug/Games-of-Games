package org.example;

import java.util.Scanner;
import java.util.Random;

//Test mode
public class GuessTheNumberTest {
	
	private Scanner scanner;
    private Random random;
    private int guesserScore;
    private int computerScore;
    
    public static void main(String[] args) {
        GuessTheNumber game = new GuessTheNumber();
        game.playGame();
        System.out.println("Guesser's score: " + game.getGuesserScore());  
        System.out.println("Computer's score: " + game.getComputerScore());
    }

    public GuessTheNumberTest() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.guesserScore = 0;
        this.computerScore = 0;
	}
		
	public int generateRange(int bound) {
	        return random.nextInt(bound);
	    }
		
	
	public void playGame() {
        System.out.println("Welcome to Guess The Number Game!");

        int lowerBound = generateRange(50) + 1;
        int upperBound = lowerBound + generateRange(50);

        if (lowerBound >= upperBound) {
            System.out.println("Invalid range! The lower bound must be less than the upper bound.");
            scanner.close();
            return;
        }

        System.out.println("The secret number is within the range: (" + lowerBound + " - " + upperBound + ")");
        
        int secretNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        System.out.println("Secret number is: "+ secretNumber);
        
        int maxAllowedGuesses = (upperBound - lowerBound + 1) / 2;
        int numberOfGuesses = random.nextInt(maxAllowedGuesses) + 1;

        System.out.println("You will have " + numberOfGuesses + " maximum number of guesses");


        if (secretNumber < lowerBound || secretNumber > upperBound) {
            System.out.println("Invalid number! The chosen number must be within the range.");
            scanner.close();
            return;
        }

        int remainingGuesses = numberOfGuesses;

        while (remainingGuesses > 0) {
            System.out.println("Please guess the secret number within the range (" + lowerBound + "-" + upperBound + "):");
            System.out.println("You have " + remainingGuesses + " remaining guesses");

            String userInput = getUserInputWithTimeout();
            if (userInput == null) {
                System.out.println("Timeout! You took too long to provide an answer.");
                computerScore++;
                return;
            }

            int guess;
            try {
                guess = Integer.parseInt(userInput); // Try converting input to a number
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                continue; // Skip the rest of the loop and prompt the user again
            }

            if (guess < lowerBound || guess > upperBound) {
                System.out.println("Invalid guess! Your guess is out of the specified range ("
                        + lowerBound + " - " + upperBound + ").");
                remainingGuesses--;
                if (remainingGuesses > 0) {
                    System.out.println("Try again! You have " + remainingGuesses + " guesses left.");
                }
            } else if (guess == secretNumber) {
                System.out.println("Yaay! That's correct");
                System.out.println("Guesser won this round!!");
                guesserScore++;
                break;
            } else {
                remainingGuesses--;
                System.out.println("Sorry, your guessed number is incorrect!");
                if (remainingGuesses > 0) {
                    System.out.println("You have " + remainingGuesses + " remaining guesses.");
                }
            }
        }

        if (remainingGuesses == 0) {
            System.out.println("Sorry, you've used all your guesses. The secret number was " + secretNumber + ".");
            System.out.println("Computer won!!");
            computerScore++;
        }
    }

    private String getUserInputWithTimeout() {
        final String[] result = new String[1];
        Thread inputThread = new Thread(() -> {
            result[0] = scanner.nextLine();
        });
        inputThread.start();

        try {
            inputThread.join(30000); // Wait for 30 seconds for the user to input
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // If the thread hasn't finished (timeout), return null
        return result[0];
    }
    
	public int getGuesserScore() {
	    return guesserScore;

    }
    public int getComputerScore() {
	    return computerScore;

    }

}
