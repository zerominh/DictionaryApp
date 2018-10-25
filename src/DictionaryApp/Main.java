package DictionaryApp;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        try {
            Field charset = Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }


        DictionaryCommandLine dicCL = new DictionaryCommandLine();
        dicCL.getDictionaryManagement().insertFromFile();
        dicCL.getDictionaryManagement().getDictionary().sortWordList();
        System.out.println("Start Dictionary");
        dicCL.showAllWordTarget();
    }
}