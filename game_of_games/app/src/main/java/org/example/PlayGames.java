package org.example;

import java.util.Scanner;

public class PlayGames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("\n--- Welcome to the Game of Games ---");
        System.out.println(" ");
        while (!exit) {
            System.out.println("Choose a game to play:");
            System.out.println(" ");
            System.out.println("1. Coin Flip");
            System.out.println("2. Find the Thimble");
            System.out.println("3. Guess the Number");
            System.out.println("4. Even or Odd");
            System.out.println("5. Find the Red Thread");
            System.out.println("6. Exit");
            System.out.println(" ");
            System.out.print("Enter your choice (1-6): ");
            System.out.println(" ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    CoinFlip coinFlipGame = new CoinFlip();
                    coinFlipGame.play();
                    break;

                case 2:
                    FindtheThimble findTheThimbleGame = new FindtheThimble();
                    findTheThimbleGame.play();
                    break;

                case 3:
                    GuessTheNumber guessTheNumberGame = new GuessTheNumber();
                    guessTheNumberGame.playGame();
                    break;

                case 4:
                    EvenOrOddGame.main(args);;
                    break;

                case 5:
                    FindTheRedThread.main(args);
                    break;

                case 6:
                    System.out.println("Thank you for playing! Goodbye!");
                    System.out.println(" ");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println(" ");
            }
        }
        scanner.close();
    }
}
