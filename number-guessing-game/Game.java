import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
	private int no_of_guesses;
	private final Scanner scanner;

    public Game() {
		this.no_of_guesses = 5;
		this.scanner = new Scanner(System.in);
	}

	private void displayWelcomeMessage() {
		System.out.println("Welcome to the Number Guessing Game!");
		System.out.println("I'm thinking of a number between 1 and 100.");
		System.out.println("You have 5 chances to guess the correct number.");
	}

	private void selectGameDifficulty() {
		System.out.println("\nPlease select the difficulty level:");
		System.out.println("1. Easy (10 chances)");
		System.out.println("2. Medium (5 chances)");
		System.out.println("3. Hard (3 chances)\n");
		System.out.print("Enter your choice: ");
		int option = Integer.parseInt(scanner.nextLine());
		if (option == 1) {
			this.no_of_guesses = 10;
			System.out.println("\nGreat! You have selected the Easy difficulty level.");
		} else if (option == 2) {
			this.no_of_guesses = 5;
			System.out.println("\nGreat! You have selected the Medium difficulty level.");
		} else if (option == 3) {
			this.no_of_guesses = 3;
			System.out.println("\nGreat! You have selected the Hard difficulty level.");
		} else {
			System.out.println("\nYou have selected wrong option");
			System.out.println("Default: Easy difficulty level.");
			this.no_of_guesses = 10;
		}
		System.out.println("Let's start the game!");
	}

	private int generateRandomNumber() {
		return ThreadLocalRandom.current().nextInt(2, 100);
	}

	private void checkUserInput() {
        long startTime = System.currentTimeMillis();
		int randomNumber = generateRandomNumber();
		int attempt = 0;
		while (true) {
			System.out.print("\nEnter your guess: ");
			int input = Integer.parseInt(scanner.nextLine());
			attempt++;
			if (input == randomNumber) {
                long endTime = System.currentTimeMillis();
				System.out.println("Congratulations! You guessed the correct number in " + attempt + " attempts.");
				System.out.println("Time taken to guess: " + ((endTime - startTime) / 1000) + "s");
				break;
			}
			if (no_of_guesses == attempt) {
				System.out.println("Sorry! You have not able to guess the number.");
				System.out.println("I guessed: " + randomNumber);
				System.out.println("Better luck next time!");
				break;
			}
			if (input > randomNumber) {
				System.out.println("Incorrect! The number is less than " + input);
			} else {
				System.out.println("Incorrect! The number is greater than " + input);
			}
		}
	}

	private boolean playAgain() {
		System.out.print("\nPlay again? (Y/N): ");
		String option = scanner.nextLine();
		if ("n".equalsIgnoreCase(option) || "no".equalsIgnoreCase(option)) {
			System.out.println("Thanks for playing! Good Bye.");
			return false;
		} else if ("y".equalsIgnoreCase(option) || "yes".equalsIgnoreCase(option)) {
			System.out.println("OK! Lets start again.");
			return true;
		} else {
			System.out.println("Invalid option entered!");
			System.out.println("Thanks for playing! Good Bye.");
			return false;
		}
	}

	public void start() {
		displayWelcomeMessage();
        do {
            selectGameDifficulty();
            checkUserInput();
        } while (playAgain());
	}

}