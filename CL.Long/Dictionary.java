/*
* Class: Create the Array List contain new Word
* */

import java.util.ArrayList;

public class Dictionary {
    public ArrayList<Word> DictionaryList = new ArrayList<>();

    /*
    add new word to DictionaryList with both target & explain
    */
    public void addWord(String word_target, String word_explain) {
        DictionaryList.add(new Word(word_target, word_explain));
    }

    /*
    add new word to DictionaryList without target & explain
    */
    public void addWord() {
        DictionaryList.add(new Word());
    }

    public void removeWord(Word newWord) {
        DictionaryList.remove(newWord);
    }

    public void removeWord(int i) {
        DictionaryList.remove(DictionaryList.get(i - 1));
    }
}



