package pageObjexts;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InitialSearchPageObject {
	
	@FindBy(id="country")
	public static WebElement countryDropdown;
	
	@FindBy(id="city")
	public static WebElement cityDropdown;
	
	@FindBy(id="model")
	public static WebElement modelTxtBx;
	
	@FindBy(id="pickup")
	public static WebElement pickupDate;
	
	@FindBy(id="dropoff")
	public static WebElement dropDate;
	
	@FindBy(className="btn-primary")
	public static WebElement submitBtn;
}
