package GameOfGames;

import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber {
	
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

    public GuessTheNumber() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.guesserScore = 0;
        this.computerScore = 0;
    
	}
		
	public int generateRange(int bound) {
	        return random.nextInt(bound);
	    }
		
	
	public void playGame() {
		
		System.out.println ("Welcome to Guess The Number Game!");
		
		int lowerBound = generateRange(50) + 1;
        int upperBound = lowerBound + generateRange(50);

		if (lowerBound >= upperBound) {	
			
	        System.out.println("Invalid range! The lower bound must be less than the upper bound.");
	        scanner.close();
	       // guesserScore++;
	        return;
	     }
		
        System.out.println("The secret number is within the range: (" + lowerBound + " - " + upperBound + ")");

		
		int secretNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
		
		int maxAllowedGuesses = (upperBound - lowerBound + 1) / 2;
		int numberOfGuesses = random.nextInt(maxAllowedGuesses) + 1;
		
		System.out.println("You will have " + numberOfGuesses + " maximum number of guesses");

		
		System.out.println("Chooser, choose the secret number within the range given ");


	    if (secretNumber < lowerBound || secretNumber > upperBound) {
	            System.out.println("Invalid number! The chosen number must be within the range.");
	            scanner.close();
	            return;
	        }
	    
        int remainingGuesses = numberOfGuesses;
        
//        int guesserScore = 0;
//        int computerScore = 0;
        
	    while (remainingGuesses > 0){

	     	System.out.println("Guesser, please guess the secret number within the range (" + lowerBound + "-" + upperBound + "):  ");
	     	System.out.println("You have " + remainingGuesses + " remaining guesses");
	     	int guess = scanner.nextInt();

	     	boolean guessCorrect = false;
	     	
	     	//int guesserScore = 0;
	     	
	     	if (guess < lowerBound || guess > upperBound) {
                // Handle out-of-range guesses
                System.out.println("Invalid guess! Your guess is out of the specified range (" 
                                   + lowerBound + " - " + upperBound + ").");
                remainingGuesses--;
                if (remainingGuesses > 0) {
                    System.out.println("Try again! You have " + remainingGuesses + " guesses left.");
                }
            }

	     	else if (guess == secretNumber){
	     		guessCorrect = true; 
	     		System.out.print("Yaay! that's correct");
	     		System.out.print("Guesser won !!");
	     		guesserScore++;
	     		break;

	     		}
	     	else {
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
	public int getGuesserScore() {
	    return guesserScore;

    }
    public int getComputerScore() {
	    return computerScore;

    }

}
