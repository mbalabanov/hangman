package at.bfi.hangman;

import java.util.Scanner;

public class HangmanGame {
	
	public static void main(String[] args) {
		
		System.out.println("\n\nHerzlich willkommen zu Hangman!\n\nBitte errate das Wort, indem Du einen Buchstaben eingibst.\n"); 
		
		String randomWord = getRandomWord();
		int numberOfRounds = randomWord.length() * 2; 
		String knownLetters = "";
		String displayWord = getDisplayWord(randomWord, knownLetters.toCharArray());
		boolean gameOver = false;
		
		while ( !gameOver ) {
			System.out.println("");			
			System.out.print("Bisher geraten: " + displayWord + ".    ");
			
			char userGuess = getUserInput(randomWord);
			
			if (findCharacter(randomWord.toCharArray(), userGuess)) {
				knownLetters += userGuess;
			}
			
			displayWord = getDisplayWord(randomWord, knownLetters.toCharArray());
			
			if (!findCharacter(displayWord.toCharArray(), '_')) {
				System.out.println("\nDu hast es erraten! \"" + displayWord + "\"");
				System.out.println("Juhu!!! Du hast gewonnen!!!");
				gameOver = true;
			} else {
				if ( numberOfRounds <= 0 ) {
					System.out.print("\nDeine Zeit ist um!");
					System.out.println("\n\nJuhu!!! Du hast leider verloren!!!");
					gameOver = true;
				} else {
					numberOfRounds--;
					System.out.println("Du hast noch " + numberOfRounds + " Runden, um das Wort zu erraten.");
				}
			}
			

		};
		
	}

    public static char getUserInput(String randomWord) {
        Scanner scanner = new Scanner(System.in);
        char ch = 0;

        while (true) {
            System.out.print("Was wählst du für ein Zeichen? ");
            
            if (scanner.hasNext()) {
                String input = scanner.next();
                
                if (input.length() == 1) {
                    ch = input.charAt(0);

                    if (Character.isLetter(ch)) {
                        return ch;
                    } else if ( findCharacter(randomWord.toCharArray(), ch) ) {
                    	System.out.println("Du hast diesen Buchstaben bereits. Gib einen anderen Buchstaben ein. ");
                    } else {
                        System.out.println("Das ist kein Buchstabe, bitte nur Buchstaben aus dem Alphabet eingeben. ");
                    }

                } else {
                    System.out.println("Das waren mehrere Buchstaben, bitte nur einen einzelnen Buchstaben eingeben. ");
                }

            }
        }
    }

	private static String getDisplayWord(String randomWord, char[] knownLetters) {
		String displayWord = "";
		for (char letter : randomWord.toCharArray()) {
			
			if (findCharacter(knownLetters, letter)) {
				displayWord += letter + " ";
			} else {
				displayWord += "_ ";
			}
			
		}
		
		return displayWord;
	}

	private static String getRandomWord() {
		String[] words = {
				"Einzelbett",
				"Fachmann",
				"Jacke",
				"Bursche",
				"Preis",
				"Konsum",
				"Wein",
				"Kiste",
				"Bescheid",
				"Kleider",
				"Moderator",
				"Mittelpunkt",
				"leise",
				"jenseits",
				"synthetisch",
				"Decke",
				"Mandarine",
				"anbieten",
				"demokratisch",
				"prominent"};
		
		int randomIndex = (int) (Math.random() * 20);

		return words[randomIndex];
	}
	
    public static boolean findCharacter(char[] letters, char letter) {
        for (int i = 0; i < letters.length; i++) {
            if (Character.toLowerCase(letters[i]) == Character.toLowerCase(letter)) {
                return true;
            }
        }
        return false;
    }
	
}
