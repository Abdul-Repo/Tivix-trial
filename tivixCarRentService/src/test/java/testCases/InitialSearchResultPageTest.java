package testCases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import basePage.BasePage;
import myUtils.MyUtils;
import pageObjexts.InitialSearchResultPageObject;

public class InitialSearchResultPageTest extends BasePage {


	public static void BookLowestFareTestMethod(){
		do{
		if(prop.getProperty("homePageTitle").equals(driver.getTitle())){
			PageFactory.initElements(driver, InitialSearchResultPageObject.class);
			MyUtils.dropdownSelect(InitialSearchResultPageObject.countryDropdown, "France", driver);
			MyUtils.dropdownSelect(InitialSearchResultPageObject.cityDropdown, "Paris", driver);
			InitialSearchResultPageObject.pickupDate.sendKeys("06062022");
			InitialSearchResultPageObject.dropDate.sendKeys("07062022");
			InitialSearchResultPageObject.submitBtn.click();
			String x1="//tr/td[5][contains(.,'";
			String x2="')]/following-sibling::td";
			List<Integer> listOfamt = new ArrayList<Integer>();
			for (WebElement webElement : InitialSearchResultPageObject.pricePerDay) {
				String s = webElement.getText().trim().replace("$", "");
				listOfamt.add(Integer.parseInt(s));
			}
			int lowest = Collections.min(listOfamt);
			WebElement rentBtn =driver.findElement(By.xpath(x1+lowest+x2));
			rentBtn.click();
			if(!prop.getProperty("homePageTitle").equals(driver.getTitle())){
				driver.get(driver.getCurrentUrl());
			}
			InitialSearchResultPageObject.CnfmRent.click();
			if(!prop.getProperty("homePageTitle").equals(driver.getTitle())){
				driver.get(driver.getCurrentUrl());
			}
		}else{
			driver.get(driver.getCurrentUrl());
		}
		}while(!prop.getProperty("homePageTitle").equals(driver.getTitle()));
	}

	
	
	@Test(groups="PositiveTest", enabled=true )
	public static void BookLowestFareTest(){
		boolean flag = false;
		do{ //using loop Since the application is not stable(to bypass the "Exception at /")
			if(prop.getProperty("homePageTitle").equals(driver.getTitle())){
				PageFactory.initElements(driver, InitialSearchResultPageObject.class);
				MyUtils.dropdownSelect(InitialSearchResultPageObject.countryDropdown, "France", driver);
				MyUtils.dropdownSelect(InitialSearchResultPageObject.cityDropdown, "Paris", driver);
				InitialSearchResultPageObject.pickupDate.sendKeys("06062022");
				InitialSearchResultPageObject.dropDate.sendKeys("07062022");
				InitialSearchResultPageObject.submitBtn.click();
				
				String x1="//tr/td[5][contains(.,'";
				String x2="')]/following-sibling::td";

				List<Integer> listOfamt = new ArrayList<Integer>();
				for (WebElement webElement : InitialSearchResultPageObject.pricePerDay) {
					String s = webElement.getText().trim().replace("$", "");
					listOfamt.add(Integer.parseInt(s));
				}
				int lowest = Collections.min(listOfamt);
				WebElement rentBtn =driver.findElement(By.xpath(x1+lowest+x2));
				rentBtn.click();
				MyUtils.takeScreenShot("Confirm Page");
				InitialSearchResultPageObject.CnfmRent.click();
				if(driver.findElement(By.xpath("//h2[text()=' Summary:']")).isDisplayed()){
					flag = true;
					break;

				}else{
					flag = false;
				}
			} else{
				driver.navigate().refresh();

			flag =driver.findElement(By.xpath("//h2[text()=' Summary:']")).isDisplayed();
			}
		}while(!prop.getProperty("homePageTitle").equals(driver.getTitle()));
		Assert.assertTrue(flag, "Navigates Successfully to user form filing");
	}



}
