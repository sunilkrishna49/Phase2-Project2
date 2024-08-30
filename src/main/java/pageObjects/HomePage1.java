
package pageObjects;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage1 {
	WebDriver driver;
	WebDriverWait wait;
	//Constructors
	public HomePage1(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
		
	}
		//Locators
	@FindBy(css="input[placeholder='Enter your location for delivery']") WebElement locationInputElement;
	@FindBy(xpath="//button[2]") WebElement locationSelectionButton;
	@FindBy(xpath = "//span[normalize-space()='Start your order']")
	WebElement startYourOrderButton;
	@FindBy(css = "body > div:nth-child(33) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4)")
	WebElement startYourOrderButton1;
	
	
	
		//Action Methods
	public void enterLocation(String location) {
		locationInputElement.sendKeys(location);
	}
	
	public void clickStartYourOrderButton() {
		try {
			wait.until(ExpectedConditions.visibilityOf(startYourOrderButton)).click();
			System.out.println("Start Your order pop up is present");
			
		}catch(TimeoutException e) {
			System.out.println("Start Your order Pop up is not present");
			
		}
	}
	
	public void selectLocation() {
		locationSelectionButton.click();	
	}
	

}
