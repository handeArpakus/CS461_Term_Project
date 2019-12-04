package analyzer;

import java.util.ArrayList;

public class StringAnalyzer {

    private StringAnalyzer () {}

    public static String [] getWords (String givenString) {
        String WORD_KEY = "Words: ";
        String END_OF_WORD_KEY = " --";

        int start =  givenString.indexOf(WORD_KEY);
        int end = givenString.indexOf(END_OF_WORD_KEY);
        String extract = givenString.substring( start + WORD_KEY.length() , end);
        String [] words = extract.split( ",");
        return words;
    }
    public static String getDescription( String givenString) {
        String description = "";
        return description;
    }
    public static ArrayList<String> getExampleSentences (String givenString) {
        ArrayList <String> sentences = new ArrayList<>();
        return sentences;
    }
}
