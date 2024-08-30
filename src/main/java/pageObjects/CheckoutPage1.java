package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage1 {
	WebDriver driver;
	WebDriverWait wait;
	//Constructors
	public CheckoutPage1(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);	
	}
	
//	WebElement nameInputElement = wait
//			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkout__name']")));
//	nameInputElement.sendKeys("John Doe");
//	WebElement mobileInputElement = wait
//			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkout__phone']")));
//	mobileInputElement.sendKeys("9985000000");
//	WebElement emailInputElement = wait
//			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkout__email']")));
//	emailInputElement.sendKeys("jhondoe@gmail.com");
	//Locators
	@FindBy(css=".ml-auto.text-right") WebElement checkoutPriceElement;
	@FindBy(xpath="//span[contains(text(),'Checkout')]") WebElement checkoutButton;
	@FindBy(xpath="//input[@id='checkout__name']") WebElement nameInputElement;
	@FindBy(xpath="//input[@id='checkout__phone']") WebElement mobileInputElement;
	@FindBy(xpath="//input[@id='checkout__email']") WebElement emailInputElement;
	@FindBy(xpath="//div[@class='sc-fzqMdD eohbfP']") WebElement applyGiftCardElement;
	
	
	//Action Methods
	public boolean isCheckoutPriceShown() {
		return wait.until(ExpectedConditions.invisibilityOf(checkoutPriceElement));
	}
	public void clickCheckoutButton() {
		checkoutButton.click();
	}
	public void enterCustomerDetails(String name, String mobile, String email) {
		wait.until(ExpectedConditions.visibilityOf(nameInputElement)).sendKeys(name);
		wait.until(ExpectedConditions.visibilityOf(mobileInputElement)).sendKeys(mobile);
		wait.until(ExpectedConditions.visibilityOf(emailInputElement)).sendKeys(email);
	}
	public void clickApplyGiftCard() {
		applyGiftCardElement.click();
	}

}
