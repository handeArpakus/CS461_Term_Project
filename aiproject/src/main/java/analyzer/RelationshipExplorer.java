package analyzer;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.list.PointerTargetNode;
import net.sf.extjwnl.data.list.PointerTargetNodeList;

/*
    Found relationships are :
    1- Antonyms
    2- Attributes
    3- Children
    4- Parents
    5- Synonyms
 */
public class RelationshipExplorer {
    private RelationshipExplorer() {}
    public static PointerTargetNodeList getAntonyms(Synset synset) {
        try {
            return PointerUtils.getAntonyms( synset);
        }catch (JWNLException e){
            return null;
        }
    }
    public static PointerTargetNodeList getAttributes (Synset synset) {
        try {
            return PointerUtils.getAttributes( synset);
        } catch (JWNLException e) {
            return null;
        }
    }
    public static PointerTargetNodeList findChildren(Synset synset) {
        try {
            return PointerUtils.getDirectHyponyms( synset);
        }catch (JWNLException e) {
            return  null;
        }

    }
    public static PointerTargetNodeList findParents(Synset synset) {
        try {
            return PointerUtils.getDirectHypernyms(synset);
        }
        catch ( JWNLException e) {
            return null;
        }
    }
    public static PointerTargetNodeList getSynonym(Synset synset) {
        try {
            return PointerUtils.getSynonyms(synset);
        } catch (JWNLException e) {
            return null;
        }

    }
}
