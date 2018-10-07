package com.zero;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class DictionaryManagement {
    public static final String FILE_DICT = "dictionaries.txt";
    public static final String OUT_FILE_DICT = "out_dictionaries.txt";

    private Dictionary dictionary;

    public Dictionary getDictionary() {
        return dictionary;
    }

    public DictionaryManagement() {dictionary = new Dictionary();}
    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bạn vui lòng nhập số lượng từ vựng");
        int numWords=0;

        // input num words
        do {
            if(scanner.hasNextInt()) {
                numWords = scanner.nextInt();

                if (numWords <= 0) {
                    System.out.println("Xin bạn hãy nhập số lượng từ hợp lệ!!!");
                }
            } else if(scanner.hasNextLine()) {
                scanner.nextLine();
                System.out.println("Xin bạn hãy nhập số lượng từ hợp lệ!!!");
            }

        } while (numWords <= 0);

        // input list word;
        System.out.println("Xin bạn hãy nhập từ Tiếng Anh dòng 1!!!");
        System.out.println("Xin bạn hãy nhập từ Tiếng Việt dòng 2!!!");
        String englishWord, vietnameseWord;
        scanner.nextLine();
        for(int i = 0; i < numWords; ++i) {
            System.out.println("Xin bạn hãy nhập từ Tiếng Anh:");
            englishWord = scanner.nextLine().toLowerCase().trim().replaceAll(" +", " ");
            System.out.println("Xin bạn hãy nhập từ Tiếng Việt:");
            vietnameseWord = scanner.nextLine().toLowerCase().trim().replaceAll(" +", " ");
            System.out.println(englishWord + ": " + vietnameseWord);
            dictionary.addWord(new Word(englishWord, vietnameseWord));

        }


    }
    public void showAllWords() {
        if(dictionary.isEmpty()) {
            System.out.println("Dictionary is empty");
        } else {
            System.out.println("No\t|English\t| Vietnamese");
            for(int i = 0; i < dictionary.size(); ++i) {
                System.out.print(i + "\t");
                System.out.println(dictionary.getWordAt(i).toString());
            }
        }
    }
    public void sortDictionary() {dictionary.sort();}
    public void insertFromFile() {
        ClassLoader loader = Main.class.getClassLoader();
        URL path = loader.getResource("com/zero/Main.class");
        System.out.println("path: " + path.toString());
        String[] prefixPath = path.toString().split("/");
        StringBuilder pBuilder = new StringBuilder();
        for (int i = 1; i < prefixPath.length-4; ++i) {
            pBuilder.append(prefixPath[i]);
            pBuilder.append('\\');
        }
        if(prefixPath[prefixPath.length - 4].charAt(prefixPath[prefixPath.length - 4].length()-1) != '!') {
            pBuilder.append(prefixPath[prefixPath.length - 4]);
            pBuilder.append('\\');
        }

        String line;

        try {

            BufferedReader bufferedReader =
                    new BufferedReader(new FileReader(pBuilder.append(FILE_DICT).toString()));

            while((line = bufferedReader.readLine()) != null) {
                dictionary.addWord(Utils.extractWordFromString(line));
            }
            // Always close files.
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void dictionaryLookup() {
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLine()) {
            scanner.nextLine();
        }
        System.out.println("Moi ban nhap tu tra cuu:");
        String enW = scanner.nextLine().toLowerCase().trim().replaceAll(" +", " ");
        String viW = dictionary.findMeaningOfWord(enW);
        if(viW != null) {
            System.out.println("Meaning: " + viW);
        } else {
            System.out.println("Xin loi toi khong tim thay tu "  + enW + " ban can.");
        }


    }
    public String searchDictionary(String enW) {
        return dictionary.findMeaningOfWord(enW);
    }
    public boolean isValidWord(Word w) {
        return !w.getWordTarget().equals("") && !w.getWordExplain().equals("");
    }
    public void addWordToDictionaryFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bạn hãy nhập muon them");
        System.out.println("Bạn hãy nhập từ Tiếng Anh:");
        String englishWord = scanner.nextLine().toLowerCase().trim().replaceAll(" +", " ");
        System.out.println("Bạn hãy nhập từ Tiếng Việt:");
        String vietnameseWord = scanner.nextLine().toLowerCase().trim().replaceAll(" +", " ");
        System.out.println(englishWord + ": " + vietnameseWord);
        Word w = new Word(englishWord, vietnameseWord);
        if(isValidWord(w)) {
            dictionary.addWord(w);
        } else {
            System.out.println("You have input invalid word");
        }

    }
    public void modifyExistingWordFromCommandline() {
        System.out.println("Bạn hãy nhập muon sua");
        System.out.println("Bạn hãy nhập từ Tiếng Anh:");
        String englishWord = Utils.getInputWord();
        System.out.println("Bạn hãy nhập tu moi");
        System.out.println("Bạn hãy nhập từ Tiếng Anh:");
        String newEngWord = Utils.getInputWord();
        System.out.println("Bạn hãy nhập từ Tiếng Viet:");
        String newViWord = Utils.getInputWord();
        boolean retValue = dictionary.modifyWord(new Word(englishWord, dictionary.findMeaningOfWord(englishWord)),
                new Word(newEngWord, newViWord));
        if(!retValue) {
            System.out.println("tu ban muon sua khong ton tai");
        }
    }
    public void deleteExistingWordFromCommandline() {
        System.out.println("Bạn hãy nhập tu muon xoa");
        System.out.println("Bạn hãy nhập từ Tiếng Anh:");
        String enW = Utils.getInputWord();
        boolean retValue = dictionary.deleteWord(new Word(enW, " "));
        if(retValue == false) {
            System.out.println("Tu ban muon xoa khong ton tai");
        } else {
            System.out.println("Ban da xoa thanh cong");
        }
    }
    public LinkedList<String> searchFirstSubWord(String sub) {
        System.out.println("searchFirstSubWord");
        return dictionary.searchFirstSubWord(sub);
    }
    public void dictionaryExportToFile() {
        ClassLoader loader = Main.class.getClassLoader();
        URL path = loader.getResource("com/zero/Main.class");
        String[] prefixPath = path.toString().split("/");
        StringBuilder pBuilder = new StringBuilder();
        for (int i = 1; i < prefixPath.length-1; ++i) {
            pBuilder.append(prefixPath[i]);
            pBuilder.append('\\');
        }


        try {

            BufferedWriter bufferedWriter =
                    new BufferedWriter(new FileWriter(pBuilder.append(OUT_FILE_DICT).toString()));
            ArrayList<Word> words = dictionary.getWords();
            StringBuilder line = new StringBuilder();
            for(Word w: words) {

                line.append(w.getWordTarget()).append("\t").append(w.getWordExplain()).append("\n");

            }
            bufferedWriter.write(line.toString());
            // Always close files.
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
