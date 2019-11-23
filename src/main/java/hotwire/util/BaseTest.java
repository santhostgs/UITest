package hotwire.util;



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import hotwire.util.DriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class BaseTest {

	private final static String ENVIRONMENT_PROP_PATH = "Resources/prop/Environment.properties";
	private final static String TEST_DATA_PATH = "Resources/prop/Testdata.properties";
	public String Browser = "Chrome";

 
  
  
  @BeforeTest
  public void Setup() {
	  //Browser = System.getProperty("browser");
	  WebDriver driver = null;
	  if(Browser.equalsIgnoreCase("Firefox")) {
		  System.setProperty("webdriver.gecko.driver", "Resources/browserdrivers/geckodriver.exe");
		  driver = new FirefoxDriver();
	  }else {
		  System.setProperty("webdriver.chrome.driver", "Resources/browserdrivers/chromedriver.exe");
		   driver=new ChromeDriver();	
	  }	  
	  DriverManager.setWebDriver(driver);
	  DriverManager.getDriver().manage().window().maximize();
  }
  
  @BeforeMethod
  public void beforeMethod() throws InterruptedException {
	  String URL = env_property_file_reader("BaseURL");
	  DriverManager.getDriver().manage().deleteAllCookies();
	  DriverManager.getDriver().get(URL);
	  sleepFor(5);
  }

  @AfterTest
  public void afterMethod() {
	  DriverManager.getDriver().quit();
  }
  
  //***************************Reusable Methods**************************************
  
	//To read data from environment properties file
	public static String env_property_file_reader(String propertyname) {
		File file = new File(ENVIRONMENT_PROP_PATH);

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return prop.getProperty(propertyname);
	}
	
	//To read data from Testdata properties file
		public static String testdata_property_file_reader(String propertyname) {
			File file = new File(TEST_DATA_PATH);

			FileInputStream fileInput = null;
			try {
				fileInput = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Properties prop = new Properties();

			// load properties file
			try {
				prop.load(fileInput);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			return prop.getProperty(propertyname);
		}
	
	//Explicit sleep
		public void sleepFor(int i) {
			try {
				  Thread.sleep(i*1000);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		//wait and get Element
		public WebElement getElement(By element, int sec) {
			Wait<WebDriver> wait = new WebDriverWait(DriverManager.getDriver(), sec);
			try {
				WebElement we = wait.until(ExpectedConditions.presenceOfElementLocated(element));
				return we;
			} catch (WebDriverException e) {
				return null;
			}
		}
		
		//Get current date and add no_of_days in it and return
		public String getDesiredDate(int no_of_days) {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Calendar c = Calendar.getInstance();    
			c.add(Calendar.DATE, no_of_days);
			return dateFormat.format(c.getTime());
		}
		
		
		
		
}
