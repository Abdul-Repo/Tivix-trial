package testCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import basePage.BasePage;
import myUtils.MyUtils;
import pageObjexts.UserDetailsPageObject;

public class UserDetailsPageTest extends BasePage {
	
	public static String homeTitle;

	@Test(groups="PositiveTest", enabled=true)
	public static void BookingCompletionTest(){
		PageFactory.initElements(driver, UserDetailsPageObject.class);
		InitialSearchResultPageTest.BookLowestFareTestMethod();
		UserDetailsPageObject.nameTextBox.sendKeys("a");
		UserDetailsPageObject.lastNameTextBox.sendKeys("a");
		UserDetailsPageObject.emailTextBox.sendKeys("@");
		UserDetailsPageObject.cardTextBox.sendKeys("1");
		UserDetailsPageObject.submitBtn.click();
		
		homeTitle = driver.getTitle();
		MyUtils.takeScreenShot("BookUnSuccess");
		Assert.assertEquals(homeTitle, "Booking Confirm", "Unable to navigate to Booking page");
		
		
		
	}

	

}
