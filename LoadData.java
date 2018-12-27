/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Class that loads all the possible words onto a variable.
 * 
 * @author Diogo
 */
public class LoadData {

    /**
     * Loads all the words of the file 'dictionaryy.txt' that have the same
     * length of letters as the word the user is thinking of, into a variable.
     *
     * @return Returns all the words of the same length.<br>Returns null case
     * there are no words of that length.
     */
    public static String loadWords() {
        int letters = ProcessData.howManyLetters();
        String line, possibleWord = "";
        Scanner inFile = null;
        try {
            inFile = new Scanner(new File("dictionaryy.txt"), "UTF-8");
        } catch (FileNotFoundException ex) {
            System.out.println("You goofed " + ex);
        }

        while (inFile.hasNext()) {
            line = inFile.nextLine();
            //if the length of the word is the same the number of letters of the word the user is thinking of then that word will go on the possibleWord variable
            if (line.length() == letters) {
                possibleWord += line + "\n";
            }
        }

        //remove the extra line break "\n"
        if (possibleWord.length() > 0) {
            possibleWord = possibleWord.substring(0, possibleWord.lastIndexOf("\n"));
            return possibleWord + "\n" + String.valueOf(letters);
        }
        return null;
    }

    /**
     * This method is only to make a new dictionary since the website from where
     * i took the word gave me words with spaces meh, it's not used in the
     * program.
     * <br>Isto serve só para fazer outro dicionário sem palavras com espaços
     * visto que o website de onde eu copiei as palavras me deu, palavras com 20
     * espaços... lel
     *
     * @throws FileNotFoundException ....it's kinda obvious
     * @throws UnsupportedEncodingException Caso o ficheiro não esteja no
     * formato <h2>"UTF-8"</h2>
     */
    public static void makeNewDictionary() throws FileNotFoundException, UnsupportedEncodingException {
        String novo = "";
        String linha;
        Scanner inFile = null;
        try {
            inFile = new Scanner(new File("dictionary.txt"), "UTF-8");
        } catch (FileNotFoundException ex) {
            System.out.println("You goofed " + ex);
        }

        //mehhhh
        Formatter newFile = new Formatter(new File("dictionaryy.txt"), "UTF-8");
        int contador = 0;

        while (inFile.hasNextLine()) {
            linha = inFile.nextLine();
            //inserts words that don't contains blank spaces
            if (!linha.contains(" ")) {
                novo += linha + "%n";
            }
            System.out.println(++contador);
        }
        newFile.format(novo);
        newFile.close();
    }

}
