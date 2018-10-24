import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {
    private Dictionary dictionary;

    public DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void insertFromCommandLine() {
        Scanner sc = new Scanner(System.in);
        int MAX = sc.nextInt();
        for (int i = 0; i < MAX; i++) {
            dictionary.DictionaryList.add(i, new Word());
            dictionary.DictionaryList.get(i).setWord_target(sc.next());
            dictionary.DictionaryList.get(i).setWord_explain(sc.next());
        }
    }

    public void insertFromFile() {
        String fileName = "C:\\Users\\Pham Long\\Documents\\Code\\Java\\Assignment Dictionary\\dictionary.txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String[] wordsArray;

            while((line=bufferedReader.readLine()) != null) {
                dictionary.addWord();
                wordsArray = line.split("\t");

                for (int i = 0; i < wordsArray.length; i++) {
                    if (i % 2 == 0)
                        dictionary.DictionaryList.get(dictionary.DictionaryList.size() - 1).setWord_target(wordsArray[i]);
                    else if (i % 2 == 1)
                        dictionary.DictionaryList.get(dictionary.DictionaryList.size() - 1).setWord_explain(wordsArray[i]);
                }
            }
            bufferedReader.close();
        }

        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    public void dictionaryLookup() {
        int temp = 0;
        Scanner sc = new Scanner(System.in);
        String inputWord = sc.next();
        for (int i = 0; i < dictionary.DictionaryList.size(); i++) {
            if (dictionary.DictionaryList.get(i).getWord_target().equals(inputWord))
                System.out.println(dictionary.DictionaryList.get(i).getWord_explain());
            else
                temp++;
        }
        if (temp == (dictionary.DictionaryList.size()))
            System.out.println("Cannot translate this Word!");
    }
}