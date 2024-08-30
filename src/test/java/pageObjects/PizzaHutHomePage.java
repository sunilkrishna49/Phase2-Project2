package pageObjects;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PizzaHutHomePage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	//Constructors
	public PizzaHutHomePage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(xpath = "//div[@data-item-id='geolocation-button']")
	WebElement findMyCurrentLocationButton;

	@FindBy(xpath = "//span[normalize-space()='Close']")
	WebElement closePopUpButton;

	@FindBy(css = "input[placeholder='Enter your location for delivery']")
	WebElement enterLocationInput;

	@FindBy(xpath = "//button[2]")
	WebElement firstDropDownOption;

	@FindBy(xpath = "//span[normalize-space()='Start your order']")
	WebElement startYourOrderButton;

	@FindBy(css = "body > div:nth-child(33) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4)")
	WebElement startYourOrderButton1;

	
	
	
	//Action Methods
	public void clickFindMyCurrentLocation() {
		findMyCurrentLocationButton.click();
	}
	public void clickClosePopUp() {
		closePopUpButton.click();
	}
	public void clickEnterLocation() {
		enterLocationInput.click();
	}
	public void typeLocation(String loc) {
		enterLocationInput.sendKeys(loc);
	}
	public void selectFirstOptionLocation() {
		firstDropDownOption.click();
	}
	public void clickStartYourOrderButton() {
		try {
			wait.until(ExpectedConditions.visibilityOf(startYourOrderButton)).click();
			System.out.println("Start Your order pop up is present");
			
		}catch(TimeoutException e) {
			System.out.println("Start Your order Pop up is not present");
			
		}
	}
	public void navigateDetailsPage() {
		clickStartYourOrderButton();
//		startYourOrderButton1.click();
	}
	
	
	
	
	

}
