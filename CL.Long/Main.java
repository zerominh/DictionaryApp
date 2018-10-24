public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        DictionaryManagement dicman = new DictionaryManagement(dictionary);
        DictionaryCommandLine dicCL = new DictionaryCommandLine(dictionary);

        dicCL.dictionaryAdvanced();
    }
}

