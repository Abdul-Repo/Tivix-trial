package basePage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


public class BasePage {


	
	public static Properties prop=null;
	public static WebDriver driver;
	public static String driverLocation; 

	
	public static Properties loadPropertyFile() {
		
		FileInputStream file= null;
		try {
			file = new FileInputStream("config.properties");
		} catch (FileNotFoundException e1) {e1.printStackTrace();}
		
		prop = new Properties();
		
		try {
			prop.load(file);
		} catch (IOException e) {e.printStackTrace();}
		
		return prop;
	}
	
	
	
	@BeforeClass
	public static void launchBrowser(){

		loadPropertyFile();
		 String browserName= prop.getProperty("browser") ;
		 String url= prop.getProperty("url");
		if(browserName.equalsIgnoreCase("chrome")){
			 driverLocation= prop.getProperty("chromedriverLocation");
			System.setProperty("webdriver.chrome.driver", driverLocation);
			 driver = new ChromeDriver();
		} else if(browserName.equalsIgnoreCase("firefox")||browserName.equalsIgnoreCase("fire fox")){
			 driverLocation= prop.getProperty("firefoxdriverLocation");
			System.setProperty("webdriver.gecko.driver", driverLocation);
			 driver = new FirefoxDriver();
		} else {
			System.out.println("PLEASE USE CHROME OR FIREFOX");
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 

	}
		
	
	
	
	
	@AfterClass
	public static void tearDown(){
		driver.quit();

	}



	
	
	
	
}
