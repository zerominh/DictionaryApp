package com.zero;

import java.util.LinkedList;
import java.util.Scanner;

public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement;

    public DictionaryManagement getDictionaryManagement() {
        return dictionaryManagement;
    }

    public DictionaryCommandline() {
        dictionaryManagement = new DictionaryManagement();
    }
    public void showAllWords() {
        dictionaryManagement.showAllWords();
    }
    public void insertFromCommandline() {
        dictionaryManagement.insertFromCommandline();
        dictionaryManagement.sortDictionary();
    }
    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline();
        dictionaryManagement.insertFromFile();
        dictionaryManagement.sortDictionary();
        showAllWords();
    }
    public void dictionaryAdvanced() {
        dictionaryManagement.insertFromFile();
        dictionaryManagement.sortDictionary();
        showAllWords();
        dictionaryManagement.dictionaryLookup();
    }
    public void addWordToDictionaryFromCommandLine() {
        dictionaryManagement.addWordToDictionaryFromCommandline();
        dictionaryManagement.sortDictionary();
    }

    public void modifyWordDictionaryFromCommandLine() {
        dictionaryManagement.modifyExistingWordFromCommandline();
        dictionaryManagement.sortDictionary();
    }

    public void deleteWordFromCommandline() {
        dictionaryManagement.deleteExistingWordFromCommandline();
    }
    public void dictionarySearcher() {
        System.out.println("Input sub start word: ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        LinkedList<String> retList = dictionaryManagement.searchFirstSubWord(str);
        System.out.println("Found words:");
        for(String s: retList) {
            System.out.println(s);
        }
    }
}
