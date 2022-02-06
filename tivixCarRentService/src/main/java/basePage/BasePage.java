package basePage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;



public class BasePage {



	public static Properties prop=null;
	public static WebDriver driver;
	public static String driverLocation; 
	static Logger logger = Logger.getLogger(BasePage.class);



	public static Properties loadPropertyFile() {

		FileInputStream file= null;
		try {file = new FileInputStream("config.properties");} catch (FileNotFoundException e1) {e1.printStackTrace();}
		prop = new Properties();
		try {prop.load(file);} catch (IOException e) {e.printStackTrace();}
		return prop;
	}



	@BeforeClass
	public static void launchBrowser(){
		PropertyConfigurator.configure("log4j.properties");
		loadPropertyFile();
		logger.info("Loading Property file");
		String browserName= prop.getProperty("browser") ;
		logger.info("Choosing Browser as per Property file");
		String url= prop.getProperty("url");
		if(browserName.equalsIgnoreCase("chrome")){
			driverLocation= prop.getProperty("chromedriverLocation");
			System.setProperty("webdriver.chrome.driver", driverLocation);
			driver = new ChromeDriver();
			logger.info("Chrome Selected");
		} else if(browserName.equalsIgnoreCase("firefox")||browserName.equalsIgnoreCase("fire fox")){
			driverLocation= prop.getProperty("firefoxdriverLocation");
			System.setProperty("webdriver.gecko.driver", driverLocation);
			driver = new FirefoxDriver();
			logger.info("firefox Selected");
		} else {
			logger.error("Select Chrome or fiefox");
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		logger.info("Loading Browser");
	}





	@AfterClass
	public static void tearDown(){
		logger.info("Quiting Browser");
		driver.quit();
	}







}
