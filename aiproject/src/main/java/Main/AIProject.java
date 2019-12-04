package Main;

import analyzer.WordAnalyzer;
import clueFactory.WordNetFactory;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.*;
import net.sf.extjwnl.data.list.PointerTargetNodeList;
import net.sf.extjwnl.dictionary.Dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AIProject {
    public static void main(String[] args)  {

        String[] answers = {"book"};

        Dictionary dictionary = null;
        try {
            dictionary = Dictionary.getDefaultResourceInstance();
        } catch (JWNLException e) {
            e.printStackTrace();
        }
        WordAnalyzer analyzer = new WordAnalyzer(dictionary, answers[0]);
        if ( analyzer.isValid()) {
            Set<POS> available = analyzer.getPOSSet();
            WordNetFactory factory = new WordNetFactory(available, answers[0], analyzer);
            for (HashMap.Entry <String, ArrayList<String>> entry : factory.getGeneratedSolutions().entrySet()) {
                System.out.println( entry.getKey());
                System.out.println( entry.getValue());
            }
        }

    }
}
