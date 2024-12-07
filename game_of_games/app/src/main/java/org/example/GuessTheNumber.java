package GameOfGames;
import java.util.Scanner;

public class GuessTheGame {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println(" Chooser, Please enter the lower bound of the range of the number you are about to chose");
		int lowerBound = scanner.nextInt();


		System.out.println("Chooser, Please enter the higher bound of the range of the number you are about to chose");
		int upperBound = scanner.nextInt();

		if (lowerBound >= upperBound) {
	            System.out.println("Invalid range! The lower bound must be less than the upper bound.");
	            scanner.close();
	            return;
	        }
	    System.out.println("Chooser, please specify the number of guesses the player 2 can make");
		int numberOfTheGuesses = scanner.nextInt();

		
		System.out.println("Chooser, choose a secret number within the range ");
	    int secretNumber = scanner.nextInt();


	    if (secretNumber < lowerBound || secretNumber > upperBound) {
	            System.out.println("Invalid number! The chosen number must be within the range.");
	            scanner.close();
	            return;
	        }
	   // int player2guess = scanner.nextInt();
	    
        int remainingGuesses = numberOfTheGuesses;
        int guesserScore = 0;
        int chooserScore = 0;
	    while (remainingGuesses > 0){

	     	System.out.println("Guesser, please guess the secret number within the range (" + lowerBound + "-" + upperBound + "):  ");
	     	System.out.println("You have " + remainingGuesses + " remaining guesses");
	     	int player2guess = scanner.nextInt();

	     	boolean guessCorrect = false;
	     	
	     	//int guesserScore = 0;


	     	if (player2guess == secretNumber){
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
            System.out.println("Chooser won!!");
            chooserScore++;
        }
	    System.out.println("Chooser's score : " + chooserScore + " ");
	    System.out.println("Guesser's score : " + guesserScore + " ");


	}
	
}
