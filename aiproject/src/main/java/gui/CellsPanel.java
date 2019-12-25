package gui;

import Main.ClueGenerator;
import accessToNewYorkTimes.Informations;
import net.sf.extjwnl.JWNLException;

import javax.swing.*;
import java.awt.*;

public class CellsPanel extends JPanel {

    // constant
    private final int CELL_SIZE = 100;

    // property
    private ClueGenerator clueGenerator;    // From this attribute, you can access new generated clues: getNewAcrossClues(), getNewDownClues()
    private Informations info;      // From this attribute, you access anything on the original NYTimes puzzle.

    // constructor
    public CellsPanel() throws InterruptedException, JWNLException {
        clueGenerator = new ClueGenerator();
        info = clueGenerator.getInformations();
    }

    // methods

    public void draw(Graphics g, int x, int y, boolean isBlack) {
        if( isBlack) {
            g.setColor( Color.BLACK);
            g.fillRect(x+50, y+50, CELL_SIZE, CELL_SIZE);
            g.setColor( Color.GRAY);
            g.drawRect( x+50, y+50, CELL_SIZE, CELL_SIZE);
        }
        else {
            g.setColor( Color.WHITE);
            g.fillRect(x+50, y+50, CELL_SIZE, CELL_SIZE);
            g.setColor( Color.GRAY);
            g.drawRect( x+50, y+50, CELL_SIZE, CELL_SIZE);
        }
    }

    public void drawLetter( Graphics g, int x, int y, String letter) {
        g.setFont( new Font("default", Font.PLAIN, 36));
        g.setColor( Color.RED);
        g.drawString( letter, x*CELL_SIZE+CELL_SIZE/2+50-10, y*CELL_SIZE+CELL_SIZE/2+50+10);
    }

    public void drawNumber( Graphics g, int x, int y, int number) {
        g.setFont( new Font("default", Font.BOLD, 20));
        g.setColor( Color.BLACK);
        g.drawString( ""+number, x*CELL_SIZE+CELL_SIZE/7+50, y*CELL_SIZE+CELL_SIZE/5+50);
    }

    @Override
    public void paintComponent( Graphics g) {
        super.paintComponent( g);
        int x = 0;
        int y = 0;

        boolean [][] blackCells = info.getBlackCells();
        String[][] solutions = info.getSolution();
        int[][] numbers = info.getLittleNumbers();

        for( int i = 0; i < 5; i++) {
            x = 0;
            for( int j = 0; j < 5; j++) {
                draw( g, x, y, blackCells[i][j] );
                if( solutions[i][j] != null) {
                    drawLetter( g, j, i, solutions[i][j]);
                    if( numbers[i][j] != 0)
                        drawNumber( g, j, i, numbers[i][j]);
                }

                x = x + CELL_SIZE;
            }

            y = y + CELL_SIZE;
        }
    }

    public Informations getInfo() {
        return info;
    }

    public ClueGenerator getClueGenerator() {
        return clueGenerator;
    }
}