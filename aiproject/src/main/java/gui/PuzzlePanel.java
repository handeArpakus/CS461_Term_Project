package gui;

import net.sf.extjwnl.JWNLException;

import javax.swing.*;
import java.awt.*;

public class PuzzlePanel extends JPanel {

    // properties
    private CellsPanel cellsPanel;
    private JTextField date;
    private JTextArea acrossCluesText;
    private JTextArea downCluesText;
    private JTextField groupNickname;

    // TO BE ADDED
    private JTextArea newAcrossCluesText;
    private JTextArea newDownCluesText;


    // constructor
    public PuzzlePanel() throws InterruptedException, JWNLException {
        JPanel cluesPanel = new JPanel();
        JPanel newCluesPanel = new JPanel();
        JPanel dateAndNickPanel = new JPanel();
        cellsPanel = new CellsPanel();

        acrossCluesText = new JTextArea(
                "ACROSS\n" + "\n");


        downCluesText = new JTextArea(
                "DOWN\n" + "\n");

        newAcrossCluesText = new JTextArea(
                "NEW CLUES (ACROSS)\n" + "\n");

        newDownCluesText = new JTextArea(
                "NEW CLUES (DOWN)\n" + "\n");


        writeCluesTexts();

        date = new JTextField( cellsPanel.getInfo().getDate());
        date.setFont(new Font("default", Font.PLAIN, 20));
        date.setEditable( false);

        groupNickname = new JTextField( "WORD++");
        groupNickname.setFont(new Font("default", Font.PLAIN, 20));
        groupNickname.setEditable( false);

       FlowLayout layout = new FlowLayout();
    //    layout.setVgap(70);
      //  layout.setHgap(20);
        dateAndNickPanel.setLayout( layout);
        dateAndNickPanel.setBackground(Color.WHITE);
        dateAndNickPanel.add( date);
        dateAndNickPanel.add( groupNickname);


        FlowLayout layout2 = new FlowLayout();
         layout2.setVgap(100);
        layout2.setHgap(50);
        cluesPanel.setLayout ( layout2);
        cluesPanel.setBackground(Color.WHITE);
        cluesPanel.add( acrossCluesText);
        cluesPanel.add( downCluesText);

        FlowLayout layout3 = new FlowLayout();
        layout3.setVgap(100);
        layout3.setHgap(120);
        newCluesPanel.setLayout( layout3);
        newCluesPanel.add( newAcrossCluesText);
        newCluesPanel.add( newDownCluesText);
        newCluesPanel.setBackground( Color.WHITE);


        cellsPanel.setBackground(Color.WHITE);
        JPanel leftPanel = new JPanel();
        cellsPanel.setPreferredSize( new Dimension( 960,360));
        dateAndNickPanel.setPreferredSize( new Dimension(960 ,50));
        leftPanel.add( cellsPanel);
        leftPanel.add( dateAndNickPanel);
        BoxLayout boxLayout = new BoxLayout( leftPanel, BoxLayout.Y_AXIS);
        leftPanel.setLayout( boxLayout);

        JPanel rightPanel = new JPanel();

        rightPanel.setBackground( Color.WHITE);
        cluesPanel.setPreferredSize( new Dimension(960, 250));
        newCluesPanel.setPreferredSize( new Dimension( 500, 540));
        rightPanel.add( cluesPanel);
        rightPanel.add( newCluesPanel);
        BoxLayout bl2 = new BoxLayout( rightPanel, BoxLayout.Y_AXIS);
        rightPanel.setLayout( bl2);

        leftPanel.setPreferredSize( new Dimension(960, 540));
        rightPanel.setPreferredSize( new Dimension( 960,540));
        add( leftPanel);
        add( rightPanel);
        BoxLayout bl3 = new BoxLayout( this, BoxLayout.X_AXIS);
        setLayout( bl3);


      //  add ( dateAndNickPanel, BorderLayout.SOUTH);

    }

    public void writeCluesTexts() {
        String[] acrossClues = cellsPanel.getInfo().getAcrossClues();
        String[] downClues = cellsPanel.getInfo().getDownClues();
        int[] acrossCluesNumbers = cellsPanel.getInfo().getAcrossClueNumbers();
        int[] downCluesNumbers = cellsPanel.getInfo().getDownClueNumbers();


        for( int i = 0; i < acrossClues.length; i++)
            acrossCluesText.append( acrossCluesNumbers[i] + ". " + acrossClues[i] + "\n");

        for( int i = 0; i < downClues.length; i++)
            downCluesText.append( downCluesNumbers[i] + ". " + downClues[i] + "\n");

        acrossCluesText.setFont(new Font("default", Font.PLAIN, 15));
        acrossCluesText.setEditable( false);
        downCluesText.setFont(new Font("default", Font.PLAIN, 15));
        downCluesText.setEditable( false);

        // NEW CLUES
        String[] newAcrossClues = cellsPanel.getClueGenerator().getNewAcrossClues();
        String[] newDownClues = cellsPanel.getClueGenerator().getNewDownClues();

        for( int i = 0; i < newAcrossClues.length; i++)
            newAcrossCluesText.append( acrossCluesNumbers[i] + ". " + newAcrossClues[i] + "\n");

        for( int i = 0; i < newDownClues.length; i++)
            newDownCluesText.append( downCluesNumbers[i] + ". " + newDownClues[i] + "\n");

        newAcrossCluesText.setFont(new Font("default", Font.PLAIN, 15));
        newAcrossCluesText.setEditable( false);
        newDownCluesText.setFont(new Font("default", Font.PLAIN, 15));
        newDownCluesText.setEditable( false);


    }
}