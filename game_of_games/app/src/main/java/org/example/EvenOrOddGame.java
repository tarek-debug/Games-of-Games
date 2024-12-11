package org.example;

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class EvenOrOddGame {

    private int playerScore;
    private int computerScore;
    private final Random random;

    public EvenOrOddGame() {
        this.playerScore = 0;
        this.computerScore = 0;
        this.random = new Random();
    }

    public static void main(String[] args) {
        EvenOrOddGame game = new EvenOrOddGame();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the 'Even or Odd' Game!");
        String playerChoice = game.getPlayerChoice(scanner);
        String computerChoice = game.assignComputerRole(playerChoice);

        System.out.println("Computer has chosen: " + computerChoice);

        int bestOf = game.getBestOfRounds(scanner);
        int requiredWins = (bestOf / 2) + 1;
        int roundsPlayed = 0;

        while (game.playerScore < requiredWins && game.computerScore < requiredWins) {
            game.playRound(scanner, playerChoice);
            roundsPlayed++;

            if (bestOf == 3 && roundsPlayed == 2 && game.playerScore == 1 && game.computerScore == 1) {
                System.out.println("Best out of 3, Tied 1-1 after 2 rounds");
            }

            game.printScores();

            if (game.playerScore == requiredWins || game.computerScore == requiredWins) {
                break;
            }
        }

        game.declareWinner(playerChoice);
    }

    public String getPlayerChoice(Scanner scanner) {
        while (true) {
            System.out.print("Choose your role (Even/Odd or No agreement): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("even") || choice.equals("odd")) {
                return capitalize(choice);
            } else if (choice.equals("no agreement")) {
                String[] roles = {"Even", "Odd"};
                String playerRole = roles[random.nextInt(2)];
                String computerRole = playerRole.equals("Even") ? "Odd" : "Even";
                System.out.println("Randomly assigned roles: Player -> " + playerRole + ", Computer -> " + computerRole);
                return playerRole;
            } else {
                System.out.println("Invalid choice. Please enter 'Even', 'Odd', or 'No agreement'.");
            }
        }
    }

    public String assignComputerRole(String playerChoice) {
        return playerChoice.equalsIgnoreCase("Even") ? "Odd" : "Even";
    }

    public int getBestOfRounds(Scanner scanner) {
        while (true) {
            System.out.print("Enter the number of rounds to play (must be odd, e.g., 3, 5, 7): ");
            try {
                int rounds = Integer.parseInt(scanner.nextLine().trim());
                if (rounds > 0 && rounds % 2 != 0) {
                    return rounds;
                } else {
                    System.out.println("Please enter a positive odd number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid odd integer.");
            }
        }
    }

    public void playRound(Scanner scanner, String playerChoice) {
        System.out.println("\n--- New Round ---");

        // Computer chooses first
        int computerNumber = random.nextInt(10) + 1; // Ensure range is 1 to 10

        // Player chooses next
        int playerNumber = getPlayerNumberWithTimeout(scanner);

        int sum = playerNumber + computerNumber;
        System.out.println("Sum of numbers: " + sum);

        boolean sumIsEven = sum % 2 == 0;

        if ((sumIsEven && playerChoice.equalsIgnoreCase("Even")) || (!sumIsEven && playerChoice.equalsIgnoreCase("Odd"))) {
            playerScore++;
            System.out.println("Player wins this round!");
        } else {
            computerScore++;
            System.out.println("Computer wins this round!");
        }

        checkAndCorrectScoring(sumIsEven, playerChoice);
    }

    public int getPlayerNumberWithTimeout(Scanner scanner) {
        final AtomicBoolean inputReceived = new AtomicBoolean(false);
        final int[] playerNumber = new int[1];

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!inputReceived.get()) {
                    System.out.println("\nTimeout during number selection. Please reselect your number.");
                }
            }
        }, 30000); // 30 seconds timeout

        while (!inputReceived.get()) {
            System.out.print("Enter a positive integer (1-10): ");
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    continue;
                }
                playerNumber[0] = Integer.parseInt(input);
                if (playerNumber[0] >= 1 && playerNumber[0] <= 10) {
                    inputReceived.set(true);
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        timer.cancel();
        return playerNumber[0];
    }

    public void checkAndCorrectScoring(boolean sumIsEven, String playerChoice) {
        int expectedPlayerScore = 0;
        int expectedComputerScore = 0;

        if ((sumIsEven && playerChoice.equalsIgnoreCase("Even")) || (!sumIsEven && playerChoice.equalsIgnoreCase("Odd"))) {
            expectedPlayerScore = playerScore;
            expectedComputerScore = computerScore - 1;
        } else {
            expectedPlayerScore = playerScore - 1;
            expectedComputerScore = computerScore;
        }

        if (playerScore < 0 || computerScore < 0 ||
                (playerScore != expectedPlayerScore && computerScore != expectedComputerScore)) {
            System.out.println("Incorrect scoring detected. Correcting scores...");
            playerScore = Math.max(expectedPlayerScore, 0);
            computerScore = Math.max(expectedComputerScore, 0);
        }
    }

    public void declareWinner(String playerChoice) {
        System.out.println("\n--- Game Over ---");
        printScores();

        if (playerScore > computerScore) {
            System.out.println("Player wins the game!");
        } else if (computerScore > playerScore) {
            System.out.println("Computer wins the game!");
        } else {
            System.out.println("It's a tie! No overall winner.");
        }
    }

    public void printScores() {
        System.out.println("Scores -> Player: " + playerScore + ", Computer: " + computerScore);
    }

    public String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
