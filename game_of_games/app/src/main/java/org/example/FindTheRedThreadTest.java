package org.example;

import java.util.Scanner;
import java.util.Random;

//Test mode
public class FindTheRedThreadTest {
	public static void main(String[] args) {
		
		/**
		 *  Initializing all variables
		 */
		
		boolean won = false;
		boolean playerdraws = false;
		int pullnum;
		int total;
		int[] table;
		boolean[] truetable;
		int[] shuffletable;
		Random random = new Random();
		String[] colors = {"orange", "yellow", "lime", "green", "cyan", "blue", "violet", "purple", "pink", "white", "black", "grey"};
		
		// Prompting user for thread count
	
		Scanner scanner = new Scanner(System.in);  // Create a Scanner object
		System.out.print("Please enter total number of threads: ");

		String input = scanner.nextLine();  // Read user input
		
		int inputnum = Integer.parseInt(input);
		System.out.println();
		
		// Making sure thread count is valid
	
		while (inputnum <= 2) {
			
			System.out.print("Invalid number. Please enter an integer greater than 2: ");
			
			input = scanner.nextLine();  // Read user input
			inputnum = Integer.parseInt(input);
			System.out.println();
			
		}
		
		if (inputnum >= 50) {
			System.out.println("The game will take a while if the total number of threads is " + inputnum + ". Please enter your number again for confirmation.");
			
				System.out.print("Please enter an integer greater than 2: ");
				
				
				input = scanner.nextLine();  // Read user input
				inputnum = Integer.parseInt(input);
				System.out.println();
				
			}
				
			while (inputnum <= 2) {
			
			System.out.print("Invalid number. Please enter an integer greater than 2: ");
			
			input = scanner.nextLine();  // Read user input
			inputnum = Integer.parseInt(input);
			System.out.println();
			
			}
			
		total = inputnum;	
		System.out.print("Please enter an integer for the number of threads to draw by: ");
			
		input = scanner.nextLine();  // Read user input
		inputnum = Integer.parseInt(input);
		pullnum = inputnum;
		System.out.println();
		
		while (inputnum < 1 || inputnum > total) {
			
			System.out.print("Invalid number. Please enter an integer greater than 0 and less than " + total + ": ");
			
			input = scanner.nextLine();  // Read user input
			inputnum = Integer.parseInt(input);
			System.out.println();
			
		}
		
		// Initializing arrays used for table shuffling
		
		table = new int[total];
		shuffletable = new int[total];
		truetable = new boolean[total];
		
		for (int i = 0; i < table.length; i++) {
			table[i] = i;
			truetable[i] = false;
			shuffletable[i] = -1;
		}
		
		System.out.println("Shuffling...");
		System.out.println();
		
		// Actual shuffling of the thread table
		
		for (int i = 0; i < table.length; i++) {
			int randomnum = random.nextInt(table.length);
			boolean placed = false;
			
			while (placed == false) {
				if (truetable[randomnum] == false) {
					placed = true;
					truetable[randomnum] = true;
					shuffletable[randomnum] = i;
				} else {
					randomnum = random.nextInt(table.length);
				}
			}
		}	
		
		System.out.println("Done!");
		System.out.println();
		
		System.out.println("Note for testing: The game works by reading the game table left to right, until the 0th entry (aka the red thread) is drawn. Drawn entries are marked with a -1.");
		System.out.println();
		
		System.out.println("Current table: ");
		for (int i = 0; i < shuffletable.length; i++) {
			System.out.print(shuffletable[i] + " ");
		}
		System.out.println();
		System.out.println();
		
		System.out.println("Picking first player...");
		System.out.println();
		
		// Picking first player
		
		int randomnum = random.nextInt(2);
		
		if (randomnum == 0) {
			playerdraws = true;
			System.out.println("You were selected to go first!");
		} else {
			System.out.println("The computer was selected to go first!");
		}
		
		System.out.println();
		
		int counter = 0;
		int drawnnum = -1;
		
		// Game loop
		
		while (won == false) {
			
			if (playerdraws) {
				
				for (int i = 0; i < pullnum; i++) {
				System.out.print("It's your turn! Type something to draw a thread: ");
				
				input = scanner.nextLine();
				
				drawnnum = shuffletable[counter];
				
				if (drawnnum == 0) {
					System.out.println("You drew the red thread!");
					won = true;
					playerdraws = true;
					break;
				} else {
					int randcolor = random.nextInt(colors.length);
					System.out.println("You drew a " + colors[randcolor] + " thread. Bummer!");
					shuffletable[counter] = -1;
					counter++;
					
		
					System.out.println();
				}
				System.out.println("Current table: ");
				for (int j = 0; j < shuffletable.length; j++) {
					System.out.print(shuffletable[j] + " ");
				}
				System.out.println();
				System.out.println();
				}
				playerdraws = false;
				
			} else {
				for (int i = 0; i < pullnum; i++) {
				System.out.println("It's the computer's turn!");
				drawnnum = shuffletable[counter];
				
				if (drawnnum == 0) {
					System.out.println("The computer drew the red thread!");
					won = true;
					playerdraws = false;
					break;
				} else {
					int randcolor = random.nextInt(colors.length);
					System.out.println("The computer drew a " + colors[randcolor] + " thread. Bummer!");
					shuffletable[counter] = -1;
					counter++;
					
					System.out.println();
				}
				System.out.println("Current table: ");
				for (int j = 0; j < shuffletable.length; j++) {
					System.out.print(shuffletable[j] + " ");
				}
				System.out.println();
				System.out.println();
			}
				playerdraws = true;
			}
			
		}
		
		// Ending messages
		
		System.out.println();
		if (playerdraws == true) {
			System.out.println("You lost. Better luck next time!");
		} else {
			System.out.println("You won! Congratulations!");
		}
		
	}	
}
