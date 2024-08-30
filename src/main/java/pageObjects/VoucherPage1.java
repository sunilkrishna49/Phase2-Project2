package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VoucherPage1 {
	WebDriver driver;
	WebDriverWait wait;
	//Constructors
	public VoucherPage1(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
		
	}
	//Locators
	
	@FindBy(xpath="//span[normalize-space()='Coupon']") WebElement couponButtonElement;
	@FindBy(xpath="//input[@placeholder='e.g. ABC1234']") WebElement inputVoucherCodeElement;
	@FindBy(css="div[class='sc-fznJRM ciBEcK'] span") WebElement voucherErrorElement;
	@FindBy(css=".icon-remove-3.absolute.top-0.right-0.mr-20.mt-20") WebElement voucherPopCloseElement;
	
	//Action Methods
	public void clickCouponButton() {
		wait.until(ExpectedConditions.visibilityOf(couponButtonElement)).click();
		
	}
	public void typeVoucherCode(String code) {
		wait.until(ExpectedConditions.visibilityOf(inputVoucherCodeElement)).sendKeys(code);	
	}
	public boolean voucherErrorCheck(String errorText) {
		return wait.until(ExpectedConditions.visibilityOf(voucherErrorElement)).getText().contains(errorText);
	}
	public void clickVoucherClose() {
		voucherPopCloseElement.click();
	}
	
	
	

}
