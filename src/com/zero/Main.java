package com.zero;

public class Main {

    public static void main(String[] args) {
	// write your code here
        // insert from command line
         DictionaryManagement dictionaryManagement = new DictionaryManagement();
         dictionaryManagement.insertFromCommandline();

         // command line
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        dictionaryCommandline.dictionaryBasic();


        System.out.println("search");
        dictionaryCommandline.getDictionaryManagement().dictionaryLookup();

        // advance
        dictionaryCommandline.dictionaryAdvanced();


        dictionaryCommandline.addWordToDictionaryFromCommandLine();
        dictionaryCommandline.showAllWords();

        dictionaryCommandline.modifyWordDictionaryFromCommandLine();
        dictionaryCommandline.showAllWords();
        dictionaryCommandline.deleteWordFromCommandline();

        dictionaryCommandline.showAllWords();



        System.out.println("extract to out file");
        dictionaryCommandline.getDictionaryManagement().dictionaryExportToFile();

        System.out.println("Find with subword");
        dictionaryCommandline.dictionarySearcher();




//        DictionaryApplication dictionaryApplication = new DictionaryApplication();
//        dictionaryApplication.runApplication(null);
    }
}
