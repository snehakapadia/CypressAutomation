package TestAutomation.TestAutomation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefinations {
	
	Properties config = new Properties();
	Properties xpath = new Properties();;
	WebDriver driver = null;
	HashMap<String, String> gHash = new HashMap<String, String>();
	
	
	@Before
	public void setup()
	{
		String configFileName = "config.properties";
		
		try {
			config.load(new FileInputStream(configFileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		configFileName = "xpath.properties";
		 
		try {
			xpath.load(new FileInputStream(configFileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Given("User navigates to {string} webpage")
	public void hitURL(String appName)
	{
		switch (config.getProperty("Browser").toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless"); 
			options.addArguments("enable-automation"); 
			options.addArguments("--no-sandbox"); 
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-browser-side-navigation"); 
			options.addArguments("--disable-gpu"); 
			driver = new ChromeDriver(options);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions optionsf = new FirefoxOptions();
			optionsf.addArguments("--headless"); 
			optionsf.addArguments("enable-automation"); 
			optionsf.addArguments("--no-sandbox"); 
			optionsf.addArguments("--disable-infobars");
			optionsf.addArguments("--disable-dev-shm-usage");
			optionsf.addArguments("--disable-browser-side-navigation"); 
			optionsf.addArguments("--disable-gpu"); 
			driver = new FirefoxDriver(optionsf);
		}
		
		driver.get(config.getProperty(appName));
		driver.manage().window().maximize();
	}
	
	@When("User clicks on {string} button")
	public void clickOnButton(String buttonName)
	{
		String xpathP = xpath.getProperty(buttonName).replace("<buttonName>", buttonName);
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		w.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathP)));
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathP))));
		driver.findElement(By.xpath(xpathP)).click();
	}
	
	@When("User enters {string} as {string}")
	public void enterData(String fieldName, String value)
	{
		if(fieldName.equals("Password"))
		{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		w.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath.getProperty(fieldName))));
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath.getProperty(fieldName)))));
		driver.findElement(By.xpath(xpath.getProperty(fieldName))).sendKeys(value);
		driver.findElement(By.xpath(xpath.getProperty(fieldName))).sendKeys(Keys.TAB);
	}
	
	@When("User selects {string} from {string}")
	public void selectList(String value, String fieldName)
	{
		Select s = new Select(driver.findElement(By.xpath(xpath.getProperty(fieldName))));
		s.selectByVisibleText(value);
	}
	
	@When("User enters data {string} in {string} in {string}")
	public void enterDataTitle(String value, String fieldName, String listName)
	{
		 String xpathPath = xpath.getProperty(fieldName).replace("<listName>", listName);
		 WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		 w.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathPath)));
		 w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathPath))));
		 if(value.toLowerCase().contains("timestamp"))
		 {
			 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			 
			 value = value.split("_TimeStamp")[0] + sdf1.format(timestamp);
			 gHash.put("titleName", value);
		 }
		 driver.findElement(By.xpath(xpathPath)).sendKeys(value);
		 driver.findElement(By.xpath(xpathPath)).sendKeys(Keys.ENTER); 
	}
	
	@Then("User verifies {string} in {string}")
	public void verifyTitle(String fieldName, String listName)
	{
		String xpathPath = xpath.getProperty(fieldName).replace("<listName>", listName);
		xpathPath = xpathPath.replace("<titleName>",gHash.get("titleName"));
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		w.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathPath)));
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathPath))));
		assertEquals(driver.findElement(By.xpath(xpathPath)).isDisplayed(), true);
	}
	
	@Then("User verifies {string} has {string} color")
	public void verifyLabel(String fieldName, String color)
	{
		String xpathPath = xpath.getProperty(fieldName).replace("<colorName>", color);
		xpathPath = xpathPath.replace("<titleName>",gHash.get("titleName"));
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		w.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathPath)));
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathPath))));
		assertEquals(driver.findElement(By.xpath(xpathPath)).isDisplayed(), true);
	}
	
	@When("User clicks on {string} as {string}")
	public void selectLabel(String fieldName, String color)
	{
		String xpathPath = xpath.getProperty(fieldName).replace("<colorName>", color);
		driver.findElement(By.xpath(xpathPath)).click(); 
	}

	@When("User clicks on {string} in {string}")
	public void clickOnAction(String actionName, String listName)
	{
	    String xpathPath = xpath.getProperty(actionName).replace("<listName>", listName);
	    if(xpathPath.contains("<titleName>"))
	    	xpathPath = xpathPath.replace("<titleName>",gHash.get("titleName"));
	    WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		w.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathPath)));
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathPath))));
	    driver.findElement(By.xpath(xpathPath)).click();

	}
	
	@After
	public void close(Scenario scenario)
	{
		if (scenario.isFailed()) {
	    	System.out.println("TearDown Failed");
	      // Take a screenshot...
	      final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	      scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
	    }
		driver.close();
		
	}

}
