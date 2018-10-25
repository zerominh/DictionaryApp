package DictionaryApp;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class DictionaryManagement {
    private Dictionary dictionary;
    public Dictionary getDictionary() {
        return dictionary;
    }
    private File file = new File("src/newdict.txt");

    public DictionaryManagement() {
        dictionary = new Dictionary();
    }

    public DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
    }


    /*
    Insert From CommandLine
     */
    public void insertFromCommandLine() {
        Scanner sc = new Scanner(System.in);
        int MAX = sc.nextInt();
        for (int i = 0; i < MAX; i++) {
            dictionary.getWordList().add(i, new Word());
            dictionary.getWordList().get(i).setWordTarget(sc.next());
            dictionary.getWordList().get(i).setWordExplain(sc.next());
        }
    }


    /*
    Read word from New Dict.txt
     */
    public void insertFromFile() {
        try
        {
            String line = null;
            Scanner scan = new Scanner(new BufferedReader(new FileReader(file)));
            while (scan.hasext())
            {
                line = scan.nextLine();
                String target = "";
                String explain = "";

                while (line.trim().length() != 0)
                {
                    if (line.indexOf("@") == 0)
                    {
                        line = line.replace("@", "");
                        target = line;
                        line = scan.nextLine();
                    }

                    else if (line.indexOf("^") == 0)
                    {
                        line = line.replace("^", "");
                        explain = line;
                        line = scan.nextLine();
                    }
                    else if (line.indexOf("$") == 0)
                    {
                        line = line.replace("$", "");
                        explain = explain + "\n" + line;
                        line = scan.nextLine();
                    }

                    else {
                        if (explain == "") {
                            explain = explain + line;
                        }
                        else {
                            explain = explain + "\n" + line;
                        }
                        line = scan.nextLine();
                    }
                }
                dictionary.addWord(new Word(target, explain));
            }
            scan.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }


    /*
    Add a new Word to New Dict.text
     */
    public void dictionaryExportToFile()
    {
        try
        {
            FileWriter fileWriter = new FileWriter(file);
            for(int i = 0; i < dictionary.size(); i++)
            {
                Word word = dictionary.getWordAt(i);
                fileWriter.write("@" + word.getWordTarget() + "\n"
                        + word.getWordExplain() + "\n\n");

            }
            fileWriter.close();
        }
        catch (IOException ex)
        {
//            System.out.println("Error when try to ghi de file!");
        }
    }

    /*Tinh nang bat buoc: DictionaryLookup*/
    public void dictionaryLookup() {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Input your search word:");
        String enW = scanner.nextLine();
        String viW = dictionary.findMeaningOfWord(enW);
        if(viW != null) {
            System.out.println("Meaning: " + viW);
        } else {
            System.out.println("Sorry, "  + enW + " is not existing");
        }
    }

    /*Tinh nang bat buoc: Search Dictionary*/

    public String searchDictionary(String enW) {
        return dictionary.findMeaningOfWord(enW);
    }

    /*
    Search First n sub words
    */
    public LinkedList<Word> searchFirstSubWord(String sub) {
//        System.out.println("search with First Sub Word: ");
        return dictionary.searchFirstSubWord(sub);
    }

    /*add new Word function in DictionaryManagement*/
    public void addWordToDictionaryFromCommandline() {
        dictionary.addWord();
        dictionary.sortWordList();

    }

    /*modify Word function in DictionaryManagement*/
    public void modifyExistingWordFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        String sc = scanner.nextLine();
        dictionary.modifyWord(sc);
    }

    /*delete existing word function in DictionaryManagement*/
    public void deleteExistingWordFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        String sc = scanner.nextLine();
        dictionary.removeWord(sc);
    }
}
