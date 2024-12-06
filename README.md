# Games-of-Games
CPSC-340 Software Engineering Final Project

## **Overview**
 This project features a collection of fun and simple games that you can play from the console against the computer. Each game is designed to provide an engaging experience! The games included are:

1. **Coin Flip**: Guess whether the coin will land on heads or tails.  
2. **Find the Thimble**: Guess where the thimble is hidden on the left or the right.  
3. **Guess the Number**: Try to guess the number the computer is thinking of within a certain range.  
4. **Even or Odd**: Predict whether a randomly generated number is even or odd.  
5. **Find the Red Thread**: Locate the "red thread" in a sequence of randomly shuffled strings.

All games are accessed via the **Game of Games**, where you can select which game to play.

---

## **Features**
- A user-friendly console-based interface.
- Multiple simple, interactive games to choose from.
- Dynamic scorekeeping for each game.
- Replay options to continue playing or switch games.

---

## **Game Descriptions**

### **1. Coin Flip**
- **Objective:** Predict whether the coin flip will result in "heads" or "tails."  
- **Gameplay:**  
  - Choose the number of rounds (must be a positive odd number).  
  - Input your guess ("heads" or "tails").  
  - Compete against the computer, and the first to win the majority of rounds wins the game.

### **2. Find the Thimble**
- **Objective:** Guess whether the thimble is hidden in the left or the right.  
- **Gameplay:**  
  - Choose the number of rounds (must be a positive odd number).  
  - Input your guess ("right" or "left").  
  - Compete against the computer, and the first to win the majority of rounds wins the game.


### **3. Guess the Number**
- **Objective:** Guess the secret number the computer has selected.  
- **Gameplay:**  
  - Choose the number of rounds (must be a positive odd number).  
  - The computer randomly selects a number within a predefined range.  
  - The user guesses the number. 
  - Keep guessing until you get it right!

### **4. Even or Odd**
- **Objective:** Predict whether a randomly generated number is even or odd.  
- **Gameplay:**  
  - A random number is generated in each round.  
  - Input your prediction ("even" or "odd").  
  - Win the round if your guess matches the result.

### **5. Find the Red Thread**
- **Objective:** Locate the "red thread" in a shuffled sequence.  
- **Gameplay:**  
  - Choose the number of spools to draw per turn. 
  - User draws a spool, followed by the computer.
  - Whoever draws the red thread wins!

---

## **How to Play**

1. Run the **Game of Games** program.  
2. Choose one of the games from the menu by entering its corresponding number.  
3. Follow the instructions displayed for the selected game.  
4. After completing a game, you can replay it or return to the main menu to select another game.

---

## **Project Structure**

### **Main Classes**
- `GameOfGames.java`: Serves as the main entry point. Displays the game selection menu and directs the user to the chosen game.  
- `CoinFlip.java`: Implements the Coin Flip game.  
- `FindTheThimble.java`: Implements the Find the Thimble game.  
- `GuessTheNumber.java`: Implements the Guess the Number game.  
- `EvenOrOdd.java`: Implements the Even or Odd game.  
- `FindTheRedThread.java`: Implements the Find the Red Thread game.

### **Test Classes**
- `CoinFlipTest.java`, `FindTheThimbleTest.java`, etc.: Unit tests for each game's functionality.

---

## **Technologies Used**
- **Programming Language**: Java  
- **Development Tools**: Any Java IDE (e.g., IntelliJ IDEA, Eclipse) or command line with `javac` and `java`.

---

## **Setup and Installation**

1. Clone this repository to your local machine:  
   `bash git clone <repository_url>`
2. Open the project in your preferred Java IDE or navigate to the project folder via terminal.  
3. Compile the main entry point (`GameOfGames.java`):  
   `bash javac GameOfGames.java`
4. Run the program:  
   `bash java GameOfGames`

---

## **Future Enhancements**
- Add more games to the collection.  
- Create a graphical interface for improved user experience.

---

## **Contributors**
- Meti Habtegiorgis, Tarek Alsolame, Noella Uwayisenga, Jacob Kammerzall  

---

## **License**
This project is licensed under the MIT License. Feel free to use and modify it for personal or educational purposes.
