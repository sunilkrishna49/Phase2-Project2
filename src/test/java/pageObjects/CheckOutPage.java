package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutPage {
	
	WebDriver driver;
	WebDriverWait wait;
	double actualTotalPrice;
	double priceAfterPepsiAdded;
	//Constructors
	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	//Locators
	@FindBy(css=".mr-auto.text-left") WebElement itemCountElement; 
	@FindBy(css="span[class='ml-auto text-right'] span[data-synth='basket-value']") WebElement totalPriceElement;
	@FindBy(xpath="//span[contains(text(),'Checkout')]") WebElement checkoutButtonElement;
	@FindBy(xpath="//div[@class='pt-20 bg-white p-20 m-20 rounded text-center shadow relative']") WebElement minimumOrderPopupElement;
	
	//Action Methods
	public boolean validateItemCount(int expectedItemCount) {
		//wait for the element to be visible
		wait.until(ExpectedConditions.visibilityOf(itemCountElement));
		
		//extract the item count text and convert into an integer
		String ItemCountText = itemCountElement.getText().replace(" item", "").replace("s","").trim();
		int actualItemCount = Integer.parseInt(ItemCountText);
		
		return actualItemCount == expectedItemCount;
	}
	
	public boolean validateTotalPrice(double expectedTotalPrice) {
		wait.until(ExpectedConditions.visibilityOf(totalPriceElement));
		String totalPriceText = totalPriceElement.getText().replace("₹", "");
		actualTotalPrice = Double.parseDouble(totalPriceText);
		return actualTotalPrice == expectedTotalPrice;
	}
	
	public void clickCheckoutButton() {
		checkoutButtonElement.click();
	}
	public boolean isMinimumOrderPopup() {
		wait.until(ExpectedConditions.visibilityOf(minimumOrderPopupElement));
		return minimumOrderPopupElement.isDisplayed();
	}
	public boolean priceTagRemovedFromCheckoutButton(){
		return wait.until(ExpectedConditions.invisibilityOf(totalPriceElement));
		
	}
	public boolean isTotalPriceMoreThanBefore() {
		
		wait.until(ExpectedConditions.visibilityOf(totalPriceElement));
		String totalPriceText = totalPriceElement.getText().replace("₹", "");
		priceAfterPepsiAdded = Double.parseDouble(totalPriceText);
		return priceAfterPepsiAdded > actualTotalPrice;
		
	}
	

}
