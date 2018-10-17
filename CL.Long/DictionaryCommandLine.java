public class DictionaryCommandLine {
    private Dictionary dictionary;

    public DictionaryCommandLine(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    /*Print Dictionary*/
    public void showAllWords()
    {
        System.out.println("No\t|English\t|Vietnamese");
        for (int i = 1; i <= dictionary.DictionaryList.size(); i++) {
            System.out.println(i + "\t|" +
                    dictionary.DictionaryList.get(i - 1).getWord_target() + "\t\t|" +
                    dictionary.DictionaryList.get(i - 1).getWord_explain());
        }
    }

    /*Dictionary Basic Function*/
    public void dictionaryBasic()
    {
        DictionaryManagement dicman = new DictionaryManagement(dictionary);
        dicman.insertFromCommandLine();
        this.showAllWords();
    }

    /*Dictionary Advanced Function*/
    public void dictionaryAdvanced()
    {
        DictionaryManagement dicman = new DictionaryManagement(dictionary);
        dicman.insertFromFile();
        this.showAllWords();
        dicman.dictionaryLookup();
    }
}
