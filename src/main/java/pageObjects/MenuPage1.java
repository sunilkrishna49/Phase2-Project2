package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuPage1 {
	WebDriver driver;
	WebDriverWait wait;
	//Constructors
	public MenuPage1(WebDriver driver) {
		this.driver = driver;
		this.wait  = new WebDriverWait(driver,Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	//Locators
	@FindBy(xpath="//a[contains(@data-synth,'link--sides--side')]") WebElement sidesMenuButton;
	@FindBy(xpath="//button[contains(@data-synth,'button--masala-keema-garlic-bread-single--one-tap')]//span//span[contains(text(),'Add')]") WebElement addSidesItem;
	@FindBy(xpath="//a[contains(@data-synth,'link--drinks--side')]") WebElement drinksMenuButton;
	@FindBy(css="button[data-synth='button--7-up-600ml--one-tap']") WebElement sevenUpDrinkElement;
	@FindBy(css="button[data-synth='button--mirinda-600ml--one-tap']") WebElement mirindaDrinkElement;
	@FindBy(xpath="//span[normalize-space()='Your Basket']") WebElement yourBasketElement;
	//Action Methods
	public void clickSidesMenu() {
		wait.until(ExpectedConditions.visibilityOf(sidesMenuButton));
		sidesMenuButton.click();
	}
	public void addSideItemToCart() {
		wait.until(ExpectedConditions.visibilityOf(addSidesItem));
		addSidesItem.click();
	}
	public void clickDrinksMenu() {
		drinksMenuButton.click();
	}
	public void addSevenupAndMirindaDrinks() {
		wait.until(ExpectedConditions.visibilityOf(sevenUpDrinkElement));
		wait.until(ExpectedConditions.visibilityOf(mirindaDrinkElement));
		sevenUpDrinkElement.click();
		mirindaDrinkElement.click();
	}
	public boolean checkNavigateBasketPage(String titleText) {
		return wait.until(ExpectedConditions.visibilityOf(yourBasketElement)).getText().contains(titleText);
	}

}
