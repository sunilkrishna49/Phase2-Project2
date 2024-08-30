package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketPage {
	WebDriver driver;
	WebDriverWait wait;
	double expectedCheckoutPrice;
	//Constructors
	public BasketPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	//Locators
	@FindBy(xpath="//div[text()='Personal Chicken Supreme']") WebElement basketItem; 
	@FindBy(css=".subtotal") WebElement subTotalElement;
	@FindBy(css=".display-supplement-value") WebElement restaurantChargesElement;
	@FindBy(css="div[class='flex flex-col pt-15 bg-white'] span:nth-child(3)") WebElement taxElement;
	@FindBy(css="span[class='ml-auto text-right'] span[data-synth='basket-value']") WebElement checkoutTotalElement;
	@FindBy(css="button[data-synth*='basket-item-remove']") WebElement removeItemButtonElement;
	
	
	//Action Methods
	public boolean isItemAddedInBasket() {
		return basketItem.isDisplayed();
	}
	public boolean validatePizzaPriceWithTax() {
	
		//Wait for Elements to be visible
//		wait.until(ExpectedConditions.visibilityOf(subTotalElement));
		
		
		//Extract and convert text values to numeric values
		double subTotal = Double.parseDouble(subTotalElement.getText().replace("₹", " "));
		double restaurantCharges = Double.parseDouble(restaurantChargesElement.getText().replace("₹", ""));
		double tax = Double.parseDouble(taxElement.getText().replace("₹", ""));
		double checkoutTotal = Double.parseDouble(checkoutTotalElement.getText().replace("₹", ""));
		
		expectedCheckoutPrice = subTotal + restaurantCharges + tax;
		return expectedCheckoutPrice == checkoutTotal;
	}
	
	public void removeItemFromBasket() {
		removeItemButtonElement.click();
	}
	

}
