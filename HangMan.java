package hangman;

/**
 *Class that holds the main method of the program.
 * 
 * @author Diogo
 */
public class HangMan {

    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String possibleWord = LoadData.loadWords();
        int m[] = new int[26];
        int meh[] = new int[1];
        meh[0] = Integer.parseInt(possibleWord.substring(possibleWord.lastIndexOf("\n") + 1));

        possibleWord = possibleWord.substring(0, possibleWord.lastIndexOf("\n"));

        if (possibleWord != null) {
            while (ProcessData.howManyWords(possibleWord) > 1 && meh[0] > 0) {
                possibleWord = ProcessData.letsGuess(possibleWord, m, meh);
                System.out.println(possibleWord);
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
