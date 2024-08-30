package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketPage1 {
	WebDriver driver;
	WebDriverWait wait;
	//Constructors
	public BasketPage1(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
	}
	
	
	
	//Locators
	@FindBy(css=".leading-tight.typography-4.basket-item-product-title.leading-tight.mb-5.flex-1.mr-5.font-bold.text-black") WebElement basketItemElement;
	
	
	
	
	//Action Methods
	public boolean isProductAddedToBasket(String expectedProductName) {
		String basketItemName = basketItemElement.getText();
		return basketItemName.equals(expectedProductName);
		
	}

}
