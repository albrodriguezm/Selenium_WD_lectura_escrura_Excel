package com.sophos;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

import static org.junit.Assert.*;

public class DataDrivenTesting_SWD_Test {

    private WebDriver driver;
    private WriteExcelFile writesFile;
    private ReadExcelFile readFile;

    private By Subcripcion = By.xpath("/html/body/div[8]//div/div/div[3]/button[1]");

    private final By searchBoxLocator = By.xpath("/html/body/div[1]/header/div[3]/div/div[3]/div/div/input");
    private final By searchBtnLocator = By.xpath("/html/body/div[1]/header/div[3]/div/div[3]/div[2]/button");
    private By resultTextLocator = By.cssSelector("#testId-SearchLandingContainer-totalResults");
    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        writesFile = new WriteExcelFile();
        readFile = new ReadExcelFile();
        driver.get("https://www.falabella.com.co/");

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test() throws IOException, InterruptedException {
        String filepath = "./src/test/resources/archivo/test.xlsx";
        String searchText = readFile.getCellValue(filepath, "Hoja1", 0 , 0);
        driver.findElement(searchBoxLocator).sendKeys(searchText);
        Thread.sleep(1000);
        driver.findElement(searchBtnLocator).click();
        //driver.findElement(searchBtnLocator).sendKeys();
        Thread.sleep(1000);
        String resultText = driver.findElement(resultTextLocator).getText();


        System.out.println("Page result text:" + resultText);


        readFile.readExcel(filepath, "Hoja1");

        writesFile.writeCellValue(filepath, "Hoja1", 0, 1, resultText);

        readFile.readExcel(filepath,"Hoja1");


    }
}
