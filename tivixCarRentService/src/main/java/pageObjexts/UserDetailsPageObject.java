package pageObjexts;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserDetailsPageObject {

	@FindBy(id="name")
	public static WebElement nameTextBox;
	
	@FindBy(id="last_name")
	public static WebElement lastNameTextBox;
	
	@FindBy(id="card_number")
	public static WebElement cardTextBox;
	
	@FindBy(id="email")
	public static WebElement emailTextBox;
	
	@FindBy(className="btn-primary")
	public static WebElement submitBtn;
}
