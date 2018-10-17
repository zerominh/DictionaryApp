package com.zero;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        try {
            Field charset = Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

//	// write your code here
//        // insert from command line
//         DictionaryManagement dictionaryManagement = new DictionaryManagement();
//         dictionaryManagement.insertFromCommandline();
//
//         // command line
//        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
//        dictionaryCommandline.dictionaryBasic();
//
//
//        System.out.println("search");
//        dictionaryCommandline.getDictionaryManagement().dictionaryLookup();
//
//        // advance
//        dictionaryCommandline.dictionaryAdvanced();
//
//
//        dictionaryCommandline.addWordToDictionaryFromCommandLine();
//        dictionaryCommandline.showAllWords();
//
//        dictionaryCommandline.modifyWordDictionaryFromCommandLine();
//        dictionaryCommandline.showAllWords();
//        dictionaryCommandline.deleteWordFromCommandline();
//
//        dictionaryCommandline.showAllWords();
//
//
//
//        System.out.println("extract to out file");
//        dictionaryCommandline.getDictionaryManagement().dictionaryExportToFile();
//
//        System.out.println("Find with subword");
//        dictionaryCommandline.dictionarySearcher();


//        DictionaryApplication dictionaryApplication = new DictionaryApplication();
//        dictionaryApplication.runApplication(null);


        // command line
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        dictionaryCommandline.getDictionaryManagement().insertFromFile();
        dictionaryCommandline.getDictionaryManagement().sortDictionary();
        System.out.println("Start Dictionary");
        int option = -1;
        do {
            switch (option) {
                case -1:
                    break;
                case 0: {
                    dictionaryCommandline.getDictionaryManagement().dictionaryLookup();
                    waitPressEnter();
                    break;
                }
                case 1: {
                    dictionaryCommandline.insertFromCommandline();
                    waitPressEnter();
                    dictionaryCommandline.showAllWords();
                    break;
                }
                case 2: {
                    dictionaryCommandline.dictionarySearcher();
                    waitPressEnter();
                    break;
                }
                case 3: {
                    dictionaryCommandline.addWordToDictionaryFromCommandLine();
                    waitPressEnter();
                    dictionaryCommandline.showAllWords();
                    waitPressEnter();
                    break;
                }
                case 4: {
                    dictionaryCommandline.deleteWordFromCommandline();
                    waitPressEnter();
                    dictionaryCommandline.showAllWords();
                    waitPressEnter();
                    break;
                }
                case 5: {
                    dictionaryCommandline.modifyWordDictionaryFromCommandLine();
                    waitPressEnter();
                    dictionaryCommandline.showAllWords();
                    waitPressEnter();
                    break;
                }
                case 6: {
                    dictionaryCommandline.getDictionaryManagement().dictionaryExportToFile();
                    waitPressEnter();
                    break;
                }
                case 7: {
                    System.out.println("Good bye");
                    System.exit(0);

                    break;
                }
                case 8: {
                    clearConsole();
                    break;
                }
                default: {
                    System.out.println("I can't do it.");
                    break;
                }

            }
            System.out.println("Press enter to continue");
            showInfo();

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
            } else {

                scanner.nextLine();
                option = -2;
            }
//            System.out.println("your option: " + option);
        } while (true);

    }

    private static void showInfo() {
        System.out.println("\n\n\n\nPress number to choose option: ");
        System.out.println("0 Search");
        System.out.println("1 Insert Your Words");
        System.out.println("2 Search Sub First Word");
        System.out.println("3 Add Word");
        System.out.println("4 Delete Word");
        System.out.println("5 Edit Word");
        System.out.println("6 Export Dictionary To Out File");
        System.out.println("7 Exit");
        System.out.println("8 Clear screen");
        System.out.println("Input your option");
    }

    private static void waitPressEnter() {

        System.out.println("Press 2 enters to continue");
        scanner.nextLine();
        scanner.nextLine();
    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
