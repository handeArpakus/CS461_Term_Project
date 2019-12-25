package Main;

import gui.PuzzlePanel;
import net.sf.extjwnl.JWNLException;

import javax.swing.*;
import java.awt.*;

public class AIProject {

    public static void main(String[] args) throws JWNLException, InterruptedException {

        PuzzlePanel panel = new PuzzlePanel();

        JFrame frame = new JFrame( "WORD++");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        //frame.setState( JFrame.ICONIFIED);

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        frame.setExtendedState( frame.getExtendedState() | frame.MAXIMIZED_BOTH);

        frame.add( panel);
        frame.setLocationRelativeTo( null);

        frame.setVisible( true);


        /*
        ClueGenerator clueG = new ClueGenerator();

        System.out.println( "\nNEW ACROSS CLUES\n");
        for( int i = 0; i < clueG.getAcrossWords().length; i++){
            System.out.println( clueG.getAcrossWords()[i] + ": "+ clueG.getNewAcrossClues()[i]);
        }

        System.out.println( "\nNEW DOWN CLUES\n");
        for( int i = 0; i < clueG.getDownWords().length; i++){
            System.out.println( clueG.getDownWords()[i] + ": "+ clueG.getNewDownClues()[i]);
        }
*/
    }
}
