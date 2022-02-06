package testCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import basePage.BasePage;
import myUtils.MyUtils;
import pageObjexts.UserDetailsPageObject;

public class UserDetailsPageTest extends BasePage {
	static Logger logger = Logger.getLogger(InitialSearchPageTest.class);

	public static String homeTitle;

	@Test(groups="PositiveTest", enabled=true)
	public static void BookingCompletionTest(){
		logger.info("Running BookingCompletionTest");
		PageFactory.initElements(driver, UserDetailsPageObject.class);
		InitialSearchResultPageTest.BookLowestFareTestMethod();
		UserDetailsPageObject.nameTextBox.sendKeys("a");
		UserDetailsPageObject.lastNameTextBox.sendKeys("a");
		UserDetailsPageObject.emailTextBox.sendKeys("@");
		UserDetailsPageObject.cardTextBox.sendKeys("1");
		UserDetailsPageObject.submitBtn.click();
		
		homeTitle = driver.getTitle();
		MyUtils.takeScreenShot("Booking page not found");
		logger.info("Unable to navigate to booking page");
		Assert.assertEquals(homeTitle, "Booking Confirm", "Unable to navigate to Booking page");
		
		
		
	}

	

}
