package com.zero;

import java.util.Scanner;

public class Utils {

    public static Word extractWordFromString(String s) {
        s.trim().toLowerCase();
        int i = 0;
        StringBuilder enWBuilder = new StringBuilder();

        while(s.charAt(i) != ' ' && (i < s.length())) {
            enWBuilder.append(s.charAt(i));
            ++i;
        }
        int startTab = s.indexOf("    ");
//        System.out.println("tab at" + startTab);
        if(startTab >= 0 && startTab > i) {
            enWBuilder.append(s.substring(i, startTab));
            i = startTab + 1;

        }


        return new Word(enWBuilder.toString(),
                s.substring(i).trim().replaceAll(" +", " "));
    }
    public static String getInputWord() {
        Scanner scanner = new Scanner(System.in);
        return  scanner.nextLine().toLowerCase().trim().replaceAll(" +", " ");
    }
    public static String formatString(String inString) {
        return inString.trim().toLowerCase().replace(" +", " ").replace("\n", "");
    }
}
