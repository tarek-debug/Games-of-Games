import java.util.Random;
import java.util.Scanner;

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

        while (game.playerScore < requiredWins && game.computerScore < requiredWins) {
            game.playRound(scanner, playerChoice);
            game.printScores();

            if (game.playerScore == requiredWins || game.computerScore == requiredWins) {
                break;
            }
        }

        game.declareWinner(playerChoice);
    }

    public String getPlayerChoice(Scanner scanner) {
        while (true) {
            System.out.print("Choose your role (Even/Odd): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("even") || choice.equals("odd")) {
                return capitalize(choice);
            } else {
                System.out.println("Invalid choice. Please enter 'Even' or 'Odd'.");
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

        int playerNumber = getPlayerNumber(scanner);
        int computerNumber = random.nextInt(11);

        System.out.println("Computer has chosen: " + computerNumber);

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
    }

    public int getPlayerNumber(Scanner scanner) {
        while (true) {
            System.out.print("Enter a positive integer: ");
            try {
                int number = Integer.parseInt(scanner.nextLine().trim());
                if (number >= 0) {
                    return number;
                } else {
                    System.out.println("Number must be non-negative.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
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
