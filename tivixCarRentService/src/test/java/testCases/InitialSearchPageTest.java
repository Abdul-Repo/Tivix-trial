package testCases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import basePage.BasePage;
import myUtils.MyUtils;
import pageObjexts.InitialSearchPageObject;
import pageObjexts.InitialSearchResultPageObject;

public class InitialSearchPageTest extends BasePage{

	public static String homeTitle;

	
	@Test(invocationCount=15, enabled=true, groups="NegitiveTest")
	public static void homePageStability(){
		driver.navigate().refresh();
		homeTitle= driver.getTitle();
		boolean flag=true;
		if(!prop.getProperty("homePageTitle").equals(homeTitle)){
			MyUtils.takeScreenShot("homePageStabilityError");
			Assert.assertEquals(false, flag, "Application Not Stable");
		} 
	}


	@Test(groups="NegitiveTest", enabled=true)
	public static void CountriesAllowedTest(){
		homeTitle= driver.getTitle();
		boolean flag= true;
		do{//using loop Since the application is not stable(to bypass the "Exception at /")
			if(prop.getProperty("homePageTitle").equals(homeTitle)){
				PageFactory.initElements(driver, InitialSearchPageObject.class);
				List<String>  DropdownOptions = MyUtils.getAllOptionInSelect(InitialSearchPageObject.countryDropdown);
				List<String> list = new ArrayList<String>();
				String Slist= prop.getProperty("country");
				list.add(Slist.split(",")[0]);
				list.add(Slist.split(",")[1]);
				list.add(Slist.split(",")[2]);
				flag = DropdownOptions.equals(list);
				break;
			} else{
				driver.get(driver.getCurrentUrl());
			}
		}while(prop.getProperty("homePageTitle").equals(homeTitle));
		Assert.assertTrue(flag, "Incorrect/Misspelt Country Name");
	}


	@Test(groups="NegitiveTest", enabled=true)
	public static void CheckCityRespectiveToCountry(){
		homeTitle= driver.getTitle();
		boolean pageChanged = false;
		do{//using loop Since the application is not stable(to bypass the "Exception at /")
			if(prop.getProperty("homePageTitle").equals(homeTitle)){
				PageFactory.initElements(driver, InitialSearchPageObject.class);
				PageFactory.initElements(driver, InitialSearchResultPageObject.class);
				MyUtils.dropdownSelect(InitialSearchPageObject.countryDropdown, "Poland", driver);
				MyUtils.dropdownSelect(InitialSearchPageObject.cityDropdown, "Paris", driver);
				InitialSearchPageObject.pickupDate.sendKeys("06062022");
				InitialSearchPageObject.dropDate.sendKeys("06062022");
				InitialSearchPageObject.submitBtn.click();
				if(InitialSearchResultPageObject.ResultTable.isDisplayed()){
					pageChanged = true;
					break;
				}else{
					pageChanged = false;
					break;
				}
			} else{
				driver.get(driver.getCurrentUrl());
			}
		}while(prop.getProperty("homePageTitle").equals(homeTitle));
		MyUtils.takeScreenShot("CheckCityRespectiveToCountry");
		Assert.assertFalse(pageChanged, "Navigates further even if incorrect city is selected");
	}

	@Test(groups="NegitiveTest", enabled=true)
	public static void PastDateBookingTest(){
		homeTitle= driver.getTitle();
		boolean pageChanged = false;
		do{//using loop Since the application is not stable(to bypass the "Exception at /")
			if(prop.getProperty("homePageTitle").equals(homeTitle)){
				PageFactory.initElements(driver, InitialSearchPageObject.class);
				PageFactory.initElements(driver, InitialSearchResultPageObject.class);
				MyUtils.dropdownSelect(InitialSearchPageObject.countryDropdown, "France", driver);
				MyUtils.dropdownSelect(InitialSearchPageObject.cityDropdown, "Paris", driver);
				InitialSearchPageObject.pickupDate.sendKeys("01012020");
				InitialSearchPageObject.dropDate.sendKeys("02022020");
				InitialSearchPageObject.submitBtn.click();
				if(InitialSearchResultPageObject.ResultTable.isDisplayed()){
					pageChanged = true;
					break;
				}else{
					pageChanged = false;
					break;
				}
			} else{
				driver.get(driver.getCurrentUrl());
			}
		}while(!prop.getProperty("homePageTitle").equals(homeTitle));
		MyUtils.takeScreenShot("PastDateBookingError");
		Assert.assertFalse(pageChanged, "Navigates to next Step even if PAST date is selected");
	}



}
