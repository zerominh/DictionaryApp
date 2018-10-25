package DictionaryApp;/*
 * Class: Create the Array List contain new Word
 * */

import java.util.*;

public class Dictionary {

    private ArrayList<Word> WordList = new ArrayList<>();
    private HashMap<String, String> mapWords;

    public ArrayList<Word> getWordList() {
        return WordList;
    }

    public HashMap<String, String> getMapWords() {
        return mapWords;
    }

    /*
        Compare n first character of each WordTarget after get
         */
    private class WordComparator implements Comparator<Word> {
        public int n = 1;

        @Override
        public int compare(Word w1, Word w2) {
            return w1.getNFirstCharactersOfWordTarget(n).compareTo(w2.getNFirstCharactersOfWordTarget(n));
        }
    }


    public Dictionary() {
        mapWords = new HashMap<>();
    }

    /*
    ...update and check the information of List...
     */
    public boolean isEmpty() {
        return WordList.isEmpty();
    }

    public int size() {
        return WordList.size();
    }


    /*
    add new word to DictionaryList with both target & explain
    */
    public void addWord(Word word) {
        if (mapWords.get(word.getWordTarget()) == null) {
            WordList.add(word);
//            System.out.println("word added: " + word.getWordTarget() + ": " + word.getWordExplain());
            mapWords.put(word.getWordTarget(), word.getWordExplain());
            sortWordList();
        }
    }

    /*Add word without Target and Explain (input from keyboard)*/
    public void addWord() {
        Word word = new Word();
        Scanner sc = new Scanner(System.in);

//        System.out.println("Input Word-Target from keyboard: ");
        word.setWordTarget(sc.nextLine());
//        System.out.println("Input Word-Explain from keyboard: ");
        word.setWordExplain(sc.nextLine());

        if (mapWords.get(word.getWordTarget()) == null) {
            WordList.add(word);
//            System.out.println("word added: " + word.getWordTarget() + ": " + word.getWordExplain());
            mapWords.put(word.getWordTarget(), word.getWordExplain());
            sortWordList();
        }
    }


    /*
    get word at locate n in the ArrayList<Word> WordList
     */
    public Word getWordAt(int index) {
        if (index < 0) {
            throw new Error("Invalid value");
        }
        if (WordList.size() == 0) {
            throw new Error("Dictionary is empty");
        }
        if (WordList.size() <= index) {
            throw new Error("Index is invalid, greater than dictionary length");
        }
        return WordList.get(index);
    }


    /*
    sort all words in ArrayList<Word> WordList
     */
    public void sortWordList() {
        WordList.sort(Comparator.comparing(Word::getWordTarget));
    }


    /*
    find meaning of a English word
     */
    public String findMeaningOfWord(String enW) {
        return mapWords.get(enW);
    }

    /*delete word with WordTarget or the Numerical order*/
    public void removeWord(Word newWord) {
        WordList.remove(newWord);
    }

    public boolean removeWord(String enW) {
        if (mapWords.get(enW) != null) {
            for (int i = 0; i < WordList.size(); i++) {
                if (WordList.get(i).getWordTarget().equals(enW)) {
                    WordList.remove(i);
                    mapWords.remove(enW);
                }
            }
            return true;
        }
        return false;
    }

    public void removeWord(int i) {
        WordList.remove(WordList.get(i - 1));
    }


    /*
    Modify the exist Word
     */
    //Replace the oldWord by newWord
    public void modifyWord(Word oldW, Word newW) {
        if (mapWords.get(oldW.getWordTarget()) != null) {
            removeWord(oldW);
            addWord(newW);
            sortWordList();
        } else {
//            System.out.println("The Word does not exist!");
        }
    }

    //Modify the Target or Explain of a existWord
    public void modifyWord(String enW) {
        if (mapWords.get(enW) != null) {
            Scanner sc = new Scanner(System.in);
            for (int i = 0; i < WordList.size(); i++) {
                if (WordList.get(i).getWordTarget().equals(enW)) {
//                    System.out.println("Input the new Target for this word: ");
                    WordList.get(i).setWordTarget(sc.nextLine());
                } else if (WordList.get(i).getWordExplain().equals(enW)) {
//                    System.out.println("Input the new Explain for this word: ");
                    WordList.get(i).setWordExplain(sc.nextLine());
                }
            }
        } else {
//            System.out.println("The Word does not exist!");
        }
    }


    /*
    search first n sub word
     */
    public LinkedList<Word> searchFirstSubWord(String sub) {
        WordComparator wordComparator = new WordComparator();
        wordComparator.n = sub.length();
        int retValue = Collections.binarySearch(WordList, new Word(sub, " "), wordComparator);
        LinkedList<Word> retList = new LinkedList<>();
        if (retValue >= 0) {
            retList.addFirst(getWordAt(retValue));
            int begin, end;
            begin = retValue - 1 >= 0 ? retValue - 1 : retValue;
            end = retValue + 1 < WordList.size() ? retValue + 1 : retValue;
            while (getWordAt(begin).getNFirstCharactersOfWordTarget(sub.length()).equals(sub) && begin >= 0
                    && begin < retValue) {
                retList.addFirst(getWordAt(begin));
                --begin;
                if (begin < 0) break;
            }
            while (getWordAt(end).getNFirstCharactersOfWordTarget(sub.length()).equals(sub) && end < WordList.size()
                    && retValue < end) {
                retList.addLast(getWordAt(end));
                ++end;
                if (end >= WordList.size()) break;
            }
            return retList;
        }
        return retList;
    }
}




