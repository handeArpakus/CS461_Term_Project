package analyzer;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.*;
import net.sf.extjwnl.dictionary.Dictionary;

import java.util.*;

public class WordAnalyzer {

    private Dictionary dictionary;
    private String word;
    private IndexWordSet wordSet;
    private boolean isValid;
    private IndexWord indexWord;

    public WordAnalyzer(Dictionary dictionary, String word) {
        this.dictionary = dictionary;
        this.word = word;
        isValid = true;
        createWordSet();
    }

    private void createWordSet ()   {
        try {
            wordSet = dictionary.lookupAllIndexWords(word);
            if ( wordSet.getIndexWordCollection().size() == 0)
                isValid = false;
        }catch (JWNLException e) {
            e.printStackTrace();
        }
    }

    public Set<POS> getPOSSet () {
        return wordSet.getValidPOSSet();
    }

    public IndexWord getIndexWord(POS pos) {
        try {
            return dictionary.lookupIndexWord( pos, word);
        } catch (JWNLException e) {
            return null;
        }
    }

    public boolean isValid() {
        return isValid;
    }
}
