package accessToNewYorkTimes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Informations {

    final int MAX_SIZE = 5;
    private boolean [][] blackCells;
    private String [] acrossClues;
    private String [] downClues;
    private int [][] littleNumbers;
    private String [][] solution;
    private String date;
    private int [] acrossClueNumbers;
    private int [] downClueNumbers;

    public Informations() throws InterruptedException {

        blackCells = new boolean [MAX_SIZE][MAX_SIZE];
        acrossClues = new String [ MAX_SIZE];
        downClues = new String [MAX_SIZE];
        littleNumbers = new int [ MAX_SIZE][ MAX_SIZE];
        solution = new String [MAX_SIZE][MAX_SIZE];
        date = "";
        acrossClueNumbers = new int [MAX_SIZE];
        downClueNumbers = new int [MAX_SIZE];
        getInformationsfromWeb();
    }

    public boolean [][] getBlackCells() {
        return blackCells;
    }
    public String printBlackCells() {
        String data = "";
        for ( int i = 0; i < MAX_SIZE; i++) {
            for ( int j = 0; j < MAX_SIZE; j++) {
                data = data + blackCells[i][j] + " ";
            }
            data = data + "\n";
        }
        return data;
    }

    public String [] getAcrossClues () {
        return acrossClues;
    }
    public String printAcrossClues () {
        String data = "";
        for ( int i = 0; i < MAX_SIZE; i++) {
            data = data + acrossClues[i] + "\n";
        }
        return data;
    }
    public String [] getDownClues() {
        return downClues;
    }
    public String printDownClues () {
        String data = "";
        for ( int i = 0; i < MAX_SIZE; i++) {
            data = data + downClues[i] + "\n";
        }
        return data;
    }

    public int [][] getLittleNumbers() {
        return littleNumbers;
    }
    public String printlittleNumbers() {
        String data = "";
        for ( int i = 0; i < MAX_SIZE; i++) {
            for ( int j = 0; j < MAX_SIZE; j++) {
                data = data + littleNumbers[i][j] + " ";
            }
            data = data + "\n";
        }
        return data;
    }

    public String [][] getSolution() {
        return solution;
    }
    public String printSolution() {
        String data = "";
        for ( int i = 0; i < MAX_SIZE; i++) {
            for ( int j = 0; j < MAX_SIZE; j++) {
                data = data + solution[i][j] + " ";
            }
            data = data + "\n";
        }
        return data;
    }

    public String getDate() {
        return date;
    }
    public int [] getAcrossClueNumbers () {
        return acrossClueNumbers;
    }
    public String printAcrossClueNumbers () {
        String data = "";
        for ( int i = 0; i < MAX_SIZE; i++) {
            data = data + acrossClueNumbers[i] + " ";
        }
        return data;
    }

    public int [] getDownClueNumbers () {
        return downClueNumbers;
    }
    public String printDownClueNumber () {
        String data = "";
        for ( int i = 0; i < MAX_SIZE; i++) {
            data = data + downClueNumbers[i] + " ";
        }
        return data;
    }

    private void getInformationsfromWeb() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pehli\\Desktop\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.nytimes.com/crosswords/game/mini");  // Go to URL

        driver.manage().window().maximize();  // Maximize Screen

        WebElement element;

        element = driver.findElement(By.className("buttons-modalButton--1REsR"));   // Click on OK 'Ready to Start?'
        element.click();

        Actions act = new Actions ( driver);

        element = driver.findElement(By.xpath("//button[text()='reveal']"));		// Click on Reveal
        element.click();

        /// Wait time
        Thread.sleep(1000);

        List<WebElement> elements =  driver.findElements(By.xpath("//a[text()='Puzzle']")); // Click on Puzzle By.xpath("//a[text()='Puzzle']")
        WebElement curr = elements.get(1);
        act.moveToElement(curr).click().build().perform();


        /// Wait time
        Thread.sleep(1000);

        elements = driver.findElements(By.className("buttons-modalButton--1REsR"));   // Click on Reveal 'Are you sure to reveal?'
        curr = elements.get(1);
        curr.click();

        /// Wait time
        Thread.sleep(1000);

        act.moveByOffset(0, 0).click().build().perform();		// Click on Exit

        /// Wait time
        Thread.sleep(1000);

        //Take elements

        extractDate( driver);
        extractClues(driver);
        extractPuzzleContent(driver);

        driver.close();

        driver.quit();
    }

    private  void extractClues (WebDriver driver) {

        List <WebElement> elements = driver.findElements(By.className("Clue-text--3lZl7"));
        List <WebElement> numbers = driver.findElements(By.className("Clue-label--2IdMY"));

        for (int i = 0; i < MAX_SIZE; i++) {
                acrossClues[i] = elements.get(i).getText();
                acrossClueNumbers[i] = Integer.parseInt(numbers.get(i).getText());

        }
        for ( int i = MAX_SIZE; i < 2*MAX_SIZE; i++) {
            downClues [ i-MAX_SIZE] = elements.get(i).getText();
            downClueNumbers [i-MAX_SIZE] = Integer.parseInt(numbers.get(i).getText());
        }
    }

/*	private void extractSolution(WebDriver driver) {
		List <WebElement> elements = driver.findElements(By.xpath("//"));
	}*/

    private void extractPuzzleContent( WebDriver driver) {

        List <WebElement> elements = driver.findElements(By.tagName("g"));
        for (int i = 0; i < 4; i++)
            elements.remove(0);
        elements.remove(25);



        int i = 0, j = 0;
        int count = 0;
        for ( WebElement e: elements) {
            List <WebElement>  children = e.findElements(By.tagName("rect"));
            List <WebElement>  siblings = e.findElements(By.tagName("text"));

            for (WebElement child: children) {
                if ( child.getAttribute("class").equals("Cell-block--1oNaD Cell-nested--x0A1y") )
                    blackCells [i][j++] = true;
                else {
                    if (siblings.size() != 0) {
                        if ( siblings.size() == 2) {
                            solution[i][j] = siblings.get(0).getText();
                        }
                        else {
                            littleNumbers [i][j] = Integer.parseInt(siblings.get(0).getText());
                            solution [i][j] = siblings.get(2).getText();
                        }
                    }

                    blackCells [i][j++] = false;
                }
            }
            if ( j == MAX_SIZE) {
                j = 0;
                i++;
            }
        }
    }

    private void extractDate ( WebDriver driver) {
        WebElement element = driver.findElement(By.className("PuzzleDetails-date--1HNzj"));
        date = element.getText();
    }
}