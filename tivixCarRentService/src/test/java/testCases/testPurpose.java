package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import basePage.BasePage;
import myUtils.MyUtils;
import pageObjexts.InitialSearchPageObject;
import pageObjexts.InitialSearchResultPageObject;

public class testPurpose extends BasePage{

	public static void main(String[] args) {

		loadPropertyFile();
		launchBrowser();
		driver.get(prop.getProperty("url"));

		String homeTitle;
		boolean pageChanged = false;
		do{ //using loop Since the application is not stable
			homeTitle= driver.getTitle();
			if(prop.getProperty("homePageTitle").equals(homeTitle)){
				PageFactory.initElements(driver, InitialSearchPageObject.class);
				PageFactory.initElements(driver, InitialSearchResultPageObject.class);
				MyUtils.dropdownSelect(InitialSearchPageObject.countryDropdown, "Poland", driver);
				MyUtils.dropdownSelect(InitialSearchPageObject.cityDropdown, "Paris", driver);
				InitialSearchPageObject.pickupDate.sendKeys("06062022");
				InitialSearchPageObject.dropDate.sendKeys("06062022");
				InitialSearchPageObject.submitBtn.click();
				
				List<Integer> listOfamt = new ArrayList<Integer>();
				for (WebElement webElement : InitialSearchResultPageObject.pricePerDay) {
					String s = webElement.getText().trim().replace("$", "");
					listOfamt.add(Integer.parseInt(s));
				}
				
				
				System.out.println(listOfamt);
				break;
			} else{
				driver.get(driver.getCurrentUrl());
			}
		}while(prop.getProperty("homePageTitle").equals(homeTitle));

		Assert.assertFalse(pageChanged, "Navigates further even if incorrect city is selected");
	
		
		
	}
	

	
	

}
