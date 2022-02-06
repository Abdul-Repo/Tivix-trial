package pageObjexts;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InitialSearchResultPageObject {
	
	@FindBy(xpath="//table[@id='search-results']")
	public static WebElement ResultTable;
	
	@FindBy(xpath="//table[@id='search-results']//td[1]")
	public static List<WebElement> companyNames;
	
	@FindBy(xpath="//table[@id='search-results']//td[2]")
	public static List<WebElement> modelNames;
	
	@FindBy(xpath="//table[@id='search-results']//td[3]")
	public static List<WebElement> plateNum;
	
	@FindBy(xpath="//table[@id='search-results']//td[4]")
	public static List<WebElement> price;
	
	@FindBy(xpath="//table[@id='search-results']//td[5]")
	public static List<WebElement> pricePerDay;
	
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
	
	@FindBy(className="btn-primary")
	public static WebElement CnfmRent;


}
