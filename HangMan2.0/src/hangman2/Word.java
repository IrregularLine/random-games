/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman2;

/**
 *
 * @author Diogo
 */
public class Word {

    String phrase = "";

    Word(int letters) {
        for (int i = 0; i < letters; i++) {
            phrase += "- ";
        }
    }

    private String replaceChar(Word phrase, char letter, int position) {
        String v[] = this.phrase.split(" ");
        v[position] = String.valueOf(letter);
        this.phrase = "";
        for (int i = 0; i < v.length;) {
            this.phrase += v[i] + " ";
        }

        return this.phrase;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
    
    
}
