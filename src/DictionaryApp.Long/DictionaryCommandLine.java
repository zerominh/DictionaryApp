package DictionaryApp;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class DictionaryCommandLine {
    private DictionaryManagement dictionaryManagement;

    public DictionaryManagement getDictionaryManagement() {
        return dictionaryManagement;
    }

    public DictionaryCommandLine() {
        dictionaryManagement = new DictionaryManagement();
    }


    /*Print Dictionary*/
    public void showAllWords()
    {
        System.out.println("No\t|English\t|Vietnamese");
        for (int i = 1; i <= dictionaryManagement.getDictionary().getWordList().size(); i++) {
            System.out.println(i + "\t|" +
                    dictionaryManagement.getDictionary().getWordList().get(i - 1).getWordTarget() + "\t\t|" +
                    dictionaryManagement.getDictionary().getWordList().get(i - 1).getWordExplain());
        }
    }

    public void showAllWordTarget() {
        System.out.println("No\t|English");
        for (int i = 1; i <= dictionaryManagement.getDictionary().getWordList().size(); i++) {
            System.out.println(i + "\t|" +
                    dictionaryManagement.getDictionary().getWordList().get(i - 1).getWordTarget() + "\n");
        }
    }



    /*Dictionary Basic Function*/
    public void dictionaryBasic()
    {
        dictionaryManagement.insertFromCommandLine();
        this.showAllWords();
    }



    /*Dictionary Advanced Function*/
    public void dictionaryAdvanced() throws IOException {
        dictionaryManagement.insertFromFile();
        this.showAllWords();
        dictionaryManagement.dictionaryLookup();
    }


    public void addWordToDictionaryFromCommandLine() {
        dictionaryManagement.addWordToDictionaryFromCommandline();
        dictionaryManagement.getDictionary().sortWordList();
    }

    public void modifyWordDictionaryFromCommandLine() {
        dictionaryManagement.modifyExistingWordFromCommandline();
        dictionaryManagement.getDictionary().sortWordList();
    }

    public void deleteWordFromCommandline() {
        dictionaryManagement.deleteExistingWordFromCommandline();
    }


    /*dictionarySearcher*/
    public void dictionarySearcher() {
//        System.out.println("Input sub start word: ");
//        Scanner scanner = new Scanner(System.in);
//        String sc = scanner.nextLine();
//        LinkedList<String> retList = dictionaryManagement.searchFirstSubWord(sc);
//        System.out.println("Found words:");
//        for(String s: retList) {
//            System.out.println(s);
//        }
    }
}
