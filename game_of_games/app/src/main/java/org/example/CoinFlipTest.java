package org.example;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//Test mode
public class CoinFlipTest {
    private int userScore = 0;
    private int computerScore = 0;

    //main method to run the game
    public static void main(String[] args){
        CoinFlip game = new CoinFlip();
        game.play();
    }
    

    //method to play the game
    public void play(){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        //setting best out of value
        System.out.println("Welcome to the Coin Flip Game!");
        System.out.println("  ");
        System.out.println("Enter an odd number for the 'best out of' rounds:");

        int rounds = scanner.nextInt();
        while (rounds <= 0 || rounds % 2 == 0) {
            System.out.println("Invalid input. Please enter a positive odd number:");
            rounds = scanner.nextInt();
        }

        //minimum value needed to win
        int winValue = (rounds/2) + 1;
        System.out.println("Great! We're playing best out of " + rounds + " rounds.");
        scanner.nextLine();

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        while (userScore < winValue && computerScore < winValue){
            
             //Computer guess
             String computerFlip = random.nextBoolean() ? "heads" : "tails";
             System.out.println("  ");
             System.out.println("The computer has flipped: " + computerFlip);
             System.out.println("  ");

            //User guess
            System.out.println("Enter your guess (heads or tails): ");
            System.out.println("  ");
            
            final CompletableFuture<String> userInputFuture = new CompletableFuture<>();

            // Schedule a timeout task
            scheduler.schedule(() -> {
                if (!userInputFuture.isDone()) {
                    userInputFuture.complete("timeout");
                }
            }, 30, TimeUnit.SECONDS);

            // Read user input in a separate thread
            Thread inputThread = new Thread(() -> {
                String userInput = scanner.nextLine().toLowerCase();
                userInputFuture.complete(userInput);
            });
            inputThread.start();

            String userGuess;
            try {
                userGuess = userInputFuture.get(); // Wait for user input or timeout
            } catch (InterruptedException | ExecutionException e) {
                userGuess = "timeout"; // Default to timeout in case of an exception
            }

            if (userGuess.equals("timeout")) {
                System.out.println("You took too long to respond! Computer gets a point.");
                computerScore++;
                System.out.println("Scoreboard: You: " + userScore + " Computer: " + computerScore);
                System.out.println();
                continue;
            }

            while (!userGuess.equals("heads") && !userGuess.equals("tails")){
                System.out.println("Invalid input. Please enter 'heads' or 'tails'");
                System.out.println("  ");
                userGuess = scanner.nextLine().toLowerCase();
            } 

            
            if(userGuess.equals(computerFlip)){
                userScore++;
                System.out.println("Congratulations! You guessed correctly. You won this round");
                System.out.println("  ");
            }
            else{
                computerScore++;
                System.out.println("Sorry! Wrong guess. Computer won this round");
                System.out.println("  ");
            }

            System.out.println("Scoreboard: You: " + userScore + " Computer: " + computerScore);
            System.out.println("  ");
        }

        scheduler.shutdown();
        
       //Final result
       if (userScore > computerScore){
            System.out.println("Congrats! You won the game");
            System.out.println("  ");
       }
       else{
            System.out.println("Sorry! The computer won the game");
            System.out.println("  ");
       }

       //scanner.close();
       System.out.println("Final Score: You " + userScore + " - " + computerScore + " Computer");
       System.out.println("Thank you for playing!");
       System.out.println("  ");
    }
    
    // Testing functions
    public int getUserScore() {
        return userScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public void resetScores() {
        userScore = 0;
        computerScore = 0;
    }
}
