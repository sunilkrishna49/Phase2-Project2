package tests;


import org.testng.Assert;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;





import listeners.ExtentReportListener1;
import pageObjects.BasketPage1;
import pageObjects.CheckoutPage1;
import pageObjects.HomePage1;
import pageObjects.MenuPage1;
import pageObjects.VoucherPage1;

public class PizzaHutTestNGTest extends ExtentReportListener1 {
	WebDriver driver;
	WebDriverWait wait;
	HomePage1 homePage;
	MenuPage1 menuPage;
	BasketPage1 basketPage;
	CheckoutPage1 checkoutPage;
	VoucherPage1 voucherPage;
	String addSidesItemName;
	ExtentReportListener1 reportListener = new ExtentReportListener1();

	@BeforeClass
	public void setup() throws IOException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		String url = getUrlFromExcel("URLs","PizzaHut");
		driver.get(url);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		homePage = new HomePage1(driver);
		menuPage = new MenuPage1(driver);
		basketPage = new BasketPage1(driver);
		checkoutPage = new CheckoutPage1(driver);
		voucherPage = new VoucherPage1(driver);
		reportListener.logStep("User opens the pizzhut homepage", true);
	}

	@Test(priority = 1)
	@Parameters("location")
	public void testUserLocationSetting(String location) throws InterruptedException {
		homePage.enterLocation(location);
		Thread.sleep(3000);
		homePage.selectLocation();
		Thread.sleep(3000);
//		homePage.clickStartYourOrderButton();
		reportListener.logStep("User Location set to Maddilapalem", true);


	}

	@Test(priority = 2)
	public void validateDealsUrl() {
		String orderDealsURL = driver.getCurrentUrl();
		Assert.assertTrue(driver.getCurrentUrl().contains(orderDealsURL));
		reportListener.logStep("URL has text as deals", true);

	}

	@Test(priority = 3)
	public void addItemUnder200ToCart() {
		menuPage.clickSidesMenu();
		menuPage.addSideItemToCart();
		reportListener.logStep("Item that is below 200", true);
	}

	@Test(priority = 4)
	@Parameters("expectedProductName")
	public void productAddedUnderBasket(String expectedProductName) {
		boolean isProductAdded = basketPage.isProductAddedToBasket(expectedProductName);
		Assert.assertTrue(isProductAdded);
		reportListener.logStep("Product added under basket", isProductAdded);
	}

	@Test(priority = 5)
	public void checkoutButtonPriceNotShown() {
		boolean ischeckoutPriceElementPresent = checkoutPage.isCheckoutPriceShown();
		Assert.assertTrue(ischeckoutPriceElementPresent);
		reportListener.logStep("Checkout Button Price is not Shown", ischeckoutPriceElementPresent);
	}

	@Test(priority = 6)
	public void navigateToDrinks() {
		menuPage.clickDrinksMenu();
		reportListener.logStep("User navigated to Drinks Menu", true);
	}

	@Test(priority = 7)
	public void addTwoDrinks() {

		menuPage.addSevenupAndMirindaDrinks();
		reportListener.logStep("User added two drinks to the basket", true);

	}

	@Test(priority = 8)
	public void clickAndNavigateToCheckout() throws InterruptedException {
		checkoutPage.clickCheckoutButton();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
		reportListener.logStep("User clicked checkout button and navigated to checkout page", true);
	}

	@Test(priority = 9)
	public void onlinePaymentRadioButtonSelected() {
//		WebElement radioButton = driver.findElement(By.xpath("//i[contains(@class,'mt-4')]"));
//		System.out.println(radioButton.isSelected());

//		driver.findElement(By.xpath("//input[@id='checkout__deliveryAddress.interior']")).sendKeys("Flat1");
//		WebElement addressInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkout__deliveryAddress.interior']")));
//		addressInputElement.sendKeys("Flat2");
//		 WebElement radioButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mt-4")));
//		System.out.println(radioButtonElement.isSelected());
	}

	@Test(priority = 10)
	@Parameters({"name","mobile","email"})
	public void enterDetails(String name, String mobile, String email) {
		
		checkoutPage.enterCustomerDetails(name, mobile, email);
		reportListener.logStep("User entered name,mobile and email", true);
	}

	@Test(priority = 11)
	public void clickGiftCard() {
		checkoutPage.clickApplyGiftCard();
		reportListener.logStep("User clicked gift card button and navigated to Voucher", true);
	}

	@Test(priority = 12)
	public void clickVoucher() {

		voucherPage.clickCouponButton();
		reportListener.logStep("User sees a pop on the screen and clicked Voucher Option", true);
	}

	@Test(priority = 13)
	@Parameters("voucherCode")
	public void typeVoucherCode(String code) {

		voucherPage.typeVoucherCode(code);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		reportListener.logStep("User typed voucher code as 12345", true);
	}

	@Test(priority = 14)
	@Parameters("errorText")
	public void validateVoucherError(String errorText) {
		boolean isVoucherError = voucherPage.voucherErrorCheck(errorText);
		Assert.assertTrue(isVoucherError);
		reportListener.logStep("User sees an alert that an incorrect code as entered", true);
	}

	@Test(priority = 15)
	public void closeVoucherPopup() {
		voucherPage.clickVoucherClose();
		reportListener.logStep("User clicks the cross symbol and closes the voucher popup", true);
	}

	@Test(priority = 16)
	@Parameters ("titleText")
	public void navigateToBasketPage(String titleText) {
		boolean isNavigateToBasketPage = menuPage.checkNavigateBasketPage(titleText);
		Assert.assertTrue(isNavigateToBasketPage);
		reportListener.logStep("User again navigates back to basket page", true);
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		if (driver != null) {
			Thread.sleep(5000);
			driver.quit();
			reportListener.logStep("User terminates the window", true);
		}
	}
	
	//method to real URL from Excel sheet
	public String getUrlFromExcel(String sheetName, String cellName) throws IOException {
		FileInputStream fis = new FileInputStream("src/test/resources/testData/testData.xlsx");
		System.out.println(fis);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		String url = sheet.getRow(1).getCell(0).getStringCellValue();
		fis.close();
		return url;
	}

}
