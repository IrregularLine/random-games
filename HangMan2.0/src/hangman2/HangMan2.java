/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman2;

/**
 * Class that holds the main method of the program.
 *
 * @author Diogo
 */
public class HangMan2{

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String possibleWord = LoadData.loadWords();
        int m[] = new int[26];
        int meh[] = new int[1];
        int meh2=0;

        if (possibleWord != null) {
            meh[0] = Integer.parseInt(possibleWord.substring(possibleWord.lastIndexOf("\n") + 1));
            meh2 = meh[0];

            possibleWord = possibleWord.substring(0, possibleWord.lastIndexOf("\n"));
        }

        String visualizeLetters[] = new String [26];
        
        if (possibleWord != null) {
            while (ProcessData.howManyWords(possibleWord) > 1 && meh[0] > 0) {
                possibleWord = ProcessData.letsGuess(possibleWord, m, meh, visualizeLetters, meh2);
            }
            if (ProcessData.howManyWords(possibleWord) == 1) {
                System.out.println("Is the word you're thinking of: " + possibleWord);
            } else {
                System.out.println(".... Is it: " + ProcessData.randomGuess(possibleWord));
            }
        } else {
            System.out.println("There were no words of the length choosen by you.\nTAA-DAAA.");
        }
    }
}
