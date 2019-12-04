package Main;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.*;
import net.sf.extjwnl.dictionary.Dictionary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DicExample {

    public static void main(String[] args)  {


        Dictionary dictionary = null;
    /*    try {
            dictionary = Dictionary.getDefaultResourceInstance();
        } catch (JWNLException e) {
            e.printStackTrace();
        }*/


        IndexWordSet set = null;
        try {
            set = dictionary.lookupAllIndexWords("asdjaslkdjkasljdkas");
        } catch (Exception e) {
            System.out.println( "sdasdasdas");
        }
        System.out.println( set.toString());


     /*   ArrayList<String> synonyms = new ArrayList<>();
        IndexWord indexWord2 = dictionary.lookupIndexWord(POS.NOUN, "book");
        List<Synset> synSets = indexWord2.getSenses();

       for (Synset synset : synSets)
        {
            List<Word> words = synset.getWords();

            for (Word word : words)
            {
                synonyms.add(word.getLemma());
            }
        }
        System.out.println( synonyms);*/

    }
}