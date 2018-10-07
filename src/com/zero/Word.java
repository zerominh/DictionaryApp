package com.zero;

public class Word {
    private String wordTarget, wordExplain;

    public String getWordExplain() {
        return wordExplain;
    }

    public String getWordTarget() {
        return wordTarget;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public Word(String t, String e) {wordTarget=t; wordExplain=e;}
    public String getNFirstCharactersOfWordTarget(int n) {
        if(n >= 1) {
            if(wordTarget.length() >= n) {
                return wordTarget.substring(0, n);
            }
            else {
                StringBuilder s = new StringBuilder();
                s.append(wordTarget);
                int diff = n - wordTarget.length();
                while(diff > 0) {
                    s.append(" ");
                    --diff;
                }
                return  s.toString();
            }
        } else {
            throw new Error("number element must >= 1");
        }
    }

    public String toString() {
        return wordTarget + "\t" + wordExplain;
    }
}
