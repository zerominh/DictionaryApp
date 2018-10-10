package com.zero;

import java.util.*;

public class Dictionary {
    public static final  int MAX = 30;
    private class WordComparator implements Comparator<Word> {
        public int n= 1;
        @Override
        public int compare(Word w1, Word w2) {
            return w1.getNFirstCharactersOfWordTarget(n).compareTo(w2.getNFirstCharactersOfWordTarget(n));
        }
    }
    private ArrayList<Word> words;
    private HashMap<String, String> mapWords;

    public ArrayList<Word> getWords() {
        return words;
    }

    public Dictionary(){
        words = new ArrayList<>();
        mapWords = new HashMap<>();
    }
    public void addWord(Word w) {
        if(mapWords.get(w.getWordTarget()) == null) {
            words.add(w);
            System.out.println("word added: " + w.getWordTarget() + ": " + w.getWordExplain());
            mapWords.put(w.getWordTarget(), w.getWordExplain());
//            sort();
        }

    }
    public boolean isEmpty() {
        return words.isEmpty();
    }
    public int size() {
        return words.size();
    }
    public Word getWordAt(int index) {
        if(index < 0) {
            throw  new Error("Invalid value");
        }
        if(words.size()==0) {
            throw  new Error("Dictionary is empty");
        }
        if(words.size() <= index) {
            throw  new Error("Index is invalid, greater than dictionary length");
        }

        return words.get(index);
    }

    public void sort() {
        words.sort(Comparator.comparing(Word::getWordTarget));
    }
    public String findMeaningOfWord(String enW) {
//        int retValue = Collections.binarySearch(words, new Word(enW, " "), Comparator.comparing(Word::getWordTarget));
//        if(retValue >= 0) {
//            return words.get(retValue).getWordExplain();
//        } else {
//            return null;
//        }
        return mapWords.get(enW);




    }
    public boolean deleteWord(Word w) {
        if(mapWords.get(w.getWordTarget()) != null) {
            mapWords.remove(w.getWordTarget(), w.getWordExplain());


            int retValue = Collections.binarySearch(words, w, Comparator.comparing(Word::getWordTarget));
            if(retValue >= 0) {
                 words.remove(retValue);
            }
            return true;
        }
        return false;
    }
    public boolean modifyWord(Word oldW, Word newW) {
        if(mapWords.get(oldW.getWordTarget()) != null) {
            deleteWord(oldW);
            addWord(newW);
            sort();
            return true;
        } else {
            return false;
        }
    }
    public LinkedList<String> searchFirstSubWord(String sub) {
        WordComparator wordComparator = new WordComparator();
        wordComparator.n = sub.length();
        int retValue = Collections.binarySearch(words, new Word(sub, " "), wordComparator);
        LinkedList<String> retList = new LinkedList<>();
        if(retValue >= 0) {
            retList.addFirst(getWordAt(retValue).getWordTarget());

            int begin, end;
            begin = retValue -1 >= 0 ? retValue -1 : retValue;
            end = retValue +1 < words.size() ? retValue +1 : retValue;
//            System.out.println("begin: " + Integer.toString(begin));
//
//            System.out.println("end: " + Integer.toString(end));
//            System.out.println("sub: " + getWordAt(begin).getNFirstCharactersOfWordTarget(sub.length()) + "..");
            while(getWordAt(begin).getNFirstCharactersOfWordTarget(sub.length()).equals(sub) && begin >= 0
                    && begin < retValue) {
//                System.out.println("equals begin");
                retList.addFirst(getWordAt(begin).getWordTarget());
                --begin;
                if(begin < 0) break;

            }
            while(getWordAt(end).getNFirstCharactersOfWordTarget(sub.length()).equals(sub) && end < words.size()
                && retValue < end) {
//                System.out.println("equals end");
                retList.addLast(getWordAt(end).getWordTarget());
                ++end;
                if(end >= words.size()) break;
            }
            return retList;
        }
        return retList;

    }

}
