package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPage {
	
	WebDriver driver;
	//Constructors
	public  MenuPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Locators
	@FindBy(xpath = "//div[@class='hidden 2xl:flex w-full']//span[@class='rounded-full bg-white']")
	WebElement vegRadioButton;
	
	@FindBy(xpath="//a[contains(@data-synth,'link--pizzas--side')]") WebElement pizzasMenuButton;
	
	@FindBy(xpath="//button[contains(@data-synth,'button--chicken-supreme-pan-personal--one-tap')]") WebElement addItemButton;
	
	@FindBy(css="a[data-synth='link--drinks--side'] span") WebElement drinksMenuButton;
	
	@FindBy(xpath="//a[1]//div[3]//div[1]//button[1]//span[1]//span[1]") WebElement addPepsiItem;
	
	//Action Methods
	public  boolean isVegRadioButtonSelected() {
		return vegRadioButton.isSelected();
	}
	public void clickPizzasMenu() {
		pizzasMenuButton.click();
	}
	public void addItemToBasket() {
		addItemButton.click();
	}
	public void clickDrinksMenu() {
		drinksMenuButton.click();
	}
	
	public void addPepsiToBasket() {
		addPepsiItem.click();
	}
	
	
	
	
	

}
