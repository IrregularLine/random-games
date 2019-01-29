package hangman;

import java.util.Random;
import java.util.Scanner;

/**
 * Processes the data based on the guesses of the program and the answers of the
 * user.
 *
 * @author Diogo
 */
public class ProcessData {

    /**
     * Asks the user how many letters the word he's thinking of contains,
     * returns that number.
     *
     * @return Number of letters the word the user is thinking of and the
     * program is gonna look for (needs to be bigger than 0).
     */
    public static int howManyLetters() {
        System.out.println("The word has to be proper without blank spaces.\n\nHow many letters does the word you're thinking of contain?");
        Scanner in = new Scanner(System.in);
        int letters = 0;
        while (letters <= 0) {
            try {
                letters = in.nextInt();
            } catch (Exception e) {
                System.out.println("Insert a number.");

                // in case the input is not a number, we need to read the next line else the Scanner bugs out
                in.nextLine();
            }
        }
        return letters;
    }

    /**
     * Method to automatize the process of the game.
     *
     * @param possibleWord All possible words the user may be thinking of.
     * @param m Vector of 26 spaces (again, 26 letters of the English alphabet),
     * if the position v[0]=1, it means that we already guessed the letter 'a'
     * and we cannot return that value (because we already guessed that letter),
     * otherwise we can return that value.
     * @param guesses The number of CORRECT guesses the program can ask the
     * user, because if the words contains 7 letters and it already guessed all
     * corectly it won't keep asking (this is for the specific cases of
     * anagrams, words of the same length with all the same letters arranged in
     * a different order).
     * @return All the possible words the user may thinking of.
     */
    public static String letsGuess(String possibleWord, int m[], int[] guesses) {
        int v[] = new int[26];
        fillFrequentLetters(possibleWord, v);
        int asciiLetter = mostFrequentLetters(v, m) + 97; //97 in ASCII code is the letter 'a'

        String letter = (char) asciiLetter + "";

        boolean maybe = guessLetter(asciiLetter);
        if (maybe == true) {
            guesses[0]--;
            possibleWord = newPossibleWords(possibleWord, true, letter);
        } else {
            possibleWord = newPossibleWords(possibleWord, false, letter);
        }
        return possibleWord;
    }

    /**
     * Method that increments a vector of 26 spaces, and each of those spaces
     * represents a letter (v[0]: letter 'a', v[1]: letter 'b',....,v[25]:
     * letter 'z'), based on how many words (of the possible words) contains
     * those specific letters.
     *
     * @param possibleWord Variable that contains all possible words the user
     * might be thinking of.
     * @param v Vector of 26 spaces (26 letters in the English alphabet),
     * everytime a word contains any of those 26 letters, the number count goes
     * up by 1.
     */
    private static void fillFrequentLetters(String possibleWord, int[] v) {
        String linha;

        Scanner var = new Scanner(possibleWord);
        while (var.hasNextLine()) {
            linha = var.nextLine();
            if (linha.contains("a")) {
                v[0]++;
            }
            if (linha.contains("b")) {
                v[1]++;
            }
            if (linha.contains("c")) {
                v[2]++;
            }
            if (linha.contains("d")) {
                v[3]++;
            }
            if (linha.contains("e")) {
                v[4]++;
            }
            if (linha.contains("f")) {
                v[5]++;
            }
            if (linha.contains("g")) {
                v[6]++;
            }
            if (linha.contains("h")) {
                v[7]++;
            }
            if (linha.contains("i")) {
                v[8]++;
            }
            if (linha.contains("j")) {
                v[9]++;
            }
            if (linha.contains("k")) {
                v[10]++;
            }
            if (linha.contains("l")) {
                v[11]++;
            }
            if (linha.contains("m")) {
                v[12]++;
            }
            if (linha.contains("n")) {
                v[13]++;
            }
            if (linha.contains("o")) {
                v[14]++;
            }
            if (linha.contains("p")) {
                v[15]++;
            }
            if (linha.contains("q")) {
                v[16]++;
            }
            if (linha.contains("r")) {
                v[17]++;
            }
            if (linha.contains("s")) {
                v[18]++;
            }
            if (linha.contains("t")) {
                v[19]++;
            }
            if (linha.contains("u")) {
                v[20]++;
            }
            if (linha.contains("v")) {
                v[21]++;
            }
            if (linha.contains("w")) {
                v[22]++;
            }
            if (linha.contains("x")) {
                v[23]++;
            }
            if (linha.contains("y")) {
                v[24]++;
            }
            if (linha.contains("z")) {
                v[25]++;
            }
        }
    }

    /**
     * Method that finds the (first) most frequent letter (if there are more
     * than 1 letters which appear the same Max number of times, it returns the
     * first Max letter.<br><h4>Note:</h4> The Random class is used else a
     * mistake that would repeat that returned a guessed letter over and over
     *
     * @param v Vector of 26 spaces (26 letters in the English alphabet),
     * everytime a word contains any of those 26 letters, the number count goes
     * up by 1.
     * @param m Vector of 26 spaces (again, 26 letters of the English alphabet),
     * if the position v[0]=1, it means that we already guessed the letter 'a'
     * and we cannot return that value (because we already guessed that letter),
     * otherwise we can return that value
     * @return The index of a letter (0 = 'a', 1 = 'b',... z = '25') that is the
     * most frequent letter and wasn't guessed yet.
     */
    private static int mostFrequentLetters(int[] v, int[] m) {
        Random randomness = new Random();
        int maxIndex = randomness.nextInt(27);
        int max = 0;
        for (int i = 0; i < 26; i++) {
            if (v[i] > max && m[i] != 1) {
                max = v[i];
                maxIndex = i;
            }
        }
        m[maxIndex] = 1;
        return maxIndex;
    }

    /**
     * Asks the user if the letter that was guessed (to be in the word the user
     * is thinking of), belongs to the word or not.
     *
     * @param asciiLetter Integer number where 97 = 'a', 98 = 'b',... 123 = 'z'.
     * @return True, if it guessed the letter correctly.<br>False, if it didn't
     * guess the word correctly.
     */
    private static boolean guessLetter(int asciiLetter) {
        Scanner in = new Scanner(System.in);
        System.out.println("Does the word you're thinking of contain the letter: " + (char) asciiLetter + "\nPress 'y' if yes, Press 'n' it doesn't contain the letter");
        char option = in.next().charAt(0);

        return option == 'y';
    }

    /**
     * Method that based on if it guessed the letter correctly or not, does a
     * While loop of all the possible words and filters out the non-possible
     * words.
     *
     * @param possibleWord Previous possile words, not based on the last guess
     * made by the user.
     * @param b Boolean value, true if it guessed right, false if it guessed
     * wrong.
     * @param letter Letter that was guessed previously.
     * @return All new possible words based on the result of the previous
     * guessed letter.
     */
    private static String newPossibleWords(String possibleWord, boolean b, String letter) {
        Scanner var = new Scanner(possibleWord);
        String newWord = "", line;
        while (var.hasNext()) {
            line = var.nextLine();
            if (line.contains(letter) == b) {
                newWord += line + "\n";
            }
        }

        return newWord;
    }

    /**
     * In case the words are Anagrams (words that contains the same number of
     * letters, and all the same letters arranged in a different way, for
     * example 'fluster' and 'restful'), it will guess the word randomly.
     *
     * @param possibleWord All possible words the user may be thinking of.
     * @return A random word of all the possible ones.
     */
    public static String randomGuess(String possibleWord) {
        String v[] = possibleWord.split("\n");

        Random randomness = new Random();

        return v[randomness.nextInt(v.length)];
    }

    /**
     * Method to know how many possible words there are, does this until there's
     * only 1 word, or there's multiple word's and it guessed all the letters
     * (so the words are anagrams).
     *
     * @param possibleWord All the possible words that the user is thinking of.
     * @return The number of possible words.
     */
    public static int howManyWords(String possibleWord) {
        Scanner var = new Scanner(possibleWord);
        int contador = 0;
        while (var.hasNextLine()) {
            var.nextLine();
            contador++;
        }
        return contador;
    }
}
