package clueFactory;

import analyzer.RelationshipExplorer;
import analyzer.StringAnalyzer;
import analyzer.WordAnalyzer;
import net.sf.extjwnl.data.*;
import net.sf.extjwnl.data.list.PointerTargetNode;
import net.sf.extjwnl.data.list.PointerTargetNodeList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class WordNetFactory {

    private WordAnalyzer wordAnalyzer;
    private String originalSolution;
    private Set <POS> available;
    private HashMap <String, ArrayList<String>> generatedSolutions;

    public WordNetFactory(Set<POS> available, String originalSolution, WordAnalyzer wordAnalyzer) {
        this.originalSolution = originalSolution;
        this.available = available;
        this.wordAnalyzer = wordAnalyzer;
        generatedSolutions = new HashMap<>();
        generateSolutions();
    }
    private void generateSolutions() {
        generateSenses();
        generateAntonyms();
        generateAttributes();
        generateChildren();
        generateParents();
        generateSynonyms();
    }
    private void generateSenses () {
        generatedSolutions.put( "senses", new ArrayList<>());
       for ( POS pos: available) {
           IndexWord indexWord = wordAnalyzer.getIndexWord( pos);
           List <Synset> senses = indexWord.getSenses();
              for ( Synset sense: senses)  {
                  List <Word> words = sense.getWords();
                  for ( Word word: words) {
                      generatedSolutions.get("senses").add( word.getLemma());
                  }
              }
           }

    }
    private void generateAntonyms() {
        generatedSolutions.put( "antonyms", new ArrayList<>());
        for ( POS pos : available) {
            IndexWord indexWord = wordAnalyzer.getIndexWord( pos);
            List <Synset> senses = indexWord.getSenses();
            for (Synset sense :senses) {
                PointerTargetNodeList antonyms = RelationshipExplorer.getAntonyms( sense);
                    for (PointerTargetNode node : antonyms) {
                        String[] words = StringAnalyzer.getWords(node.toString());
                        //    System.out.println( node.getWord().toString());
                        for (String word : words)
                            generatedSolutions.get("antonyms").add(word);
                    }


            }
        }
    }
    // POS has to be noun, in order to find the words' attributes.
    private void generateAttributes() {
        generatedSolutions.put( "attributes", new ArrayList<>());

        if ( available.contains( POS.NOUN)) {
            IndexWord indexWord = wordAnalyzer.getIndexWord( POS.NOUN);
            List <Synset> senses = indexWord.getSenses();
            for ( Synset sense: senses) {
                PointerTargetNodeList attributes = RelationshipExplorer.getAttributes( sense);
                for ( PointerTargetNode node : attributes) {
                    String [] words = StringAnalyzer.getWords( node.toString());
                    for ( String word : words)
                        generatedSolutions.get( "attributes").add( word);
                }
            }
        }
    }
    private void generateChildren() {
        generatedSolutions.put( "children", new ArrayList<>());

        for ( POS pos : available) {
            IndexWord indexWord = wordAnalyzer.getIndexWord( pos);
            List<Synset> senses = indexWord.getSenses();
            for ( Synset sense : senses) {
                PointerTargetNodeList children = RelationshipExplorer.findChildren( sense);
                    for (PointerTargetNode node : children) {
                        String[] words = StringAnalyzer.getWords(node.toString());
                        for (String word : words)
                            generatedSolutions.get("children").add(word);
                    }


            }
        }
    }
    private void generateParents () {
        generatedSolutions.put("parents", new ArrayList<>());

        for ( POS pos : available) {
            IndexWord indexWord = wordAnalyzer.getIndexWord( pos);
            List<Synset> senses = indexWord.getSenses();
            for ( Synset sense : senses) {
                PointerTargetNodeList parents = RelationshipExplorer.findParents( sense);
                for ( PointerTargetNode node : parents) {
                    String [] words = StringAnalyzer.getWords( node.toString());
                    for ( String word : words)
                        generatedSolutions.get( "parents").add( word + " " + pos.getLabel());
                }
            }
        }
    }
    private void generateSynonyms() {

    }

    public HashMap<String, ArrayList<String>> getGeneratedSolutions() {
        return generatedSolutions;
    }
}
