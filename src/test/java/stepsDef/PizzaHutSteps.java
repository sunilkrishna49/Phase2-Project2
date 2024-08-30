package stepsDef;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.*;
import listeners.ExtentReportListener;
import pageObjects.BasketPage;
import pageObjects.CheckOutPage;
import pageObjects.MenuPage;
import pageObjects.PizzaHutHomePage;

public class PizzaHutSteps {

	WebDriver driver = Hooks.getDriver(); // gets the webdriver instance from the hooks class
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	PizzaHutHomePage pizzahutHomePage;
	MenuPage menuPage;
	BasketPage basketPage;
	CheckOutPage checkoutPage;
	ExtentReportListener reportListener = new ExtentReportListener();
	double initialPriceTotal;

	@Given("User launch Pizzahut application with {string}")
	public void user_launch_pizzahut_application_with(String url) {

		pizzahutHomePage = new PizzaHutHomePage(driver);
		menuPage = new MenuPage(driver);
		basketPage = new BasketPage(driver);
		checkoutPage = new CheckOutPage(driver);
		driver.get(url);
		reportListener.logStep("User launch Pizzahut application with URL: " + url, true);

	}

	@When("User waits for auto location black pop-up screen")
	public void user_waits_for_auto_location_black_pop_up_screen() throws InterruptedException {
//		pizzahutHomePage.clickFindMyCurrentLocation();
		Thread.sleep(3000);
		reportListener.logStep("User waited for auto location black pop-up screen", true);
	}

	@Then("User closes the pop-up screen")
	public void user_closes_the_pop_up_screen() {
//		pizzahutHomePage.clickClosePopUp();
		reportListener.logStep("User closes the pop-up screen", true);
	}

	@Then("User see pop up for delivery asking for enter location")
	public void user_see_pop_up_for_delivery_asking_for_enter_location() {
		pizzahutHomePage.clickEnterLocation();
		reportListener.logStep("User sees pop-up for delivery asking for enter location", true);
	}

	@Then("User type address as {string}")
	public void user_type_address_as(String Location) throws InterruptedException {
		pizzahutHomePage.typeLocation(Location);
		Thread.sleep(3000);
		reportListener.logStep("User type address " + Location, true);

	}

	@Then("User select first auto populate drop down option")
	public void user_select_first_auto_populate_drop_down_option() throws InterruptedException {
		pizzahutHomePage.selectFirstOptionLocation();
		reportListener.logStep("User selected first auto populate drop down option", true);
	}

	@When("User navigate to details page")
	public void user_navigate_to_deails_page() throws InterruptedException {
		pizzahutHomePage.navigateDetailsPage();
		Thread.sleep(3000);
		reportListener.logStep("User navigated to details page", true);

	}

	@Then("User validate vegetarian radio button flag is off")
	public void user_validate_vegetarian_radio_button_flag_is_off() {

		boolean isSelected = menuPage.isVegRadioButtonSelected();
		if (!isSelected) {
			reportListener.logStep("User validated that the vegetarian radio button flag is off", true);
		} else {
			reportListener.logStep("User validated that the vegetarian radio button flag is off", false);
		}
		
	}

	@Then("User clicks on Pizzas menu bar option")
	public void user_clicks_on_pizzas_menu_bar_option() throws InterruptedException {
		menuPage.clickPizzasMenu();
		reportListener.logStep("User clicked on Pizzas Menu bar option", true);
		Thread.sleep(3000);
	}

	@When("User select add button of any pizza from Recommended")
	public void user_select_add_button_of_any_pizza_from_recommended() throws InterruptedException {
		menuPage.addItemToBasket();
		Thread.sleep(3000);
	}

	@Then("User see that the pizza is getting added under Your Basket")
	public void user_see_that_the_pizza_is_getting_added_under_your_basket() throws InterruptedException {

		boolean isDisplayed = basketPage.isItemAddedInBasket();
		Thread.sleep(3000);
		if (isDisplayed) {
			reportListener.logStep("User sees that the pizza is getting added into the basket", true);
		} else {
			reportListener.logStep("User sees that the pizza is getting added into the basket", false);

		}

	}

	@Then("User validate pizza price plus Tax is checkout price")
	public void user_validate_pizza_price_plus_tax_is_checkout_price() {

		boolean isPriceCorrect = basketPage.validatePizzaPriceWithTax();
		if (isPriceCorrect) {
			reportListener.logStep("Pizza price plus restaurant charges plus tax is equal to Checkout Price", true);
		} else {
			reportListener.logStep("Pizza price plus restaurant charges plus tax is equal to Checkout Price", false);
		}

	}

	@Then("User validate checkout button contains Item count {int}")
	public void user_validate_checkout_button_contains_item_count(int expectedItemCount) {

		boolean isItemCountCorrect = checkoutPage.validateItemCount(expectedItemCount);

		if (isItemCountCorrect) {
			reportListener.logStep("Check out button contains the expected item count", true);
		} else {
			reportListener.logStep("Check out button contains the expected item count", false);
		}
	}

	@Then("User validate checkout button contains total price count {double}")
	public void user_validate_checkout_button_contains_total_price_count(double expectedTotalPrice) {
		boolean isTotalPriceInCheckout = checkoutPage.validateTotalPrice(expectedTotalPrice);
		if (isTotalPriceInCheckout) {
			reportListener.logStep("Checkout Button contains the correct total price: ₹" + expectedTotalPrice, true);
		} else {
			reportListener.logStep("Checkout Button contains the correct total price: ₹" + expectedTotalPrice, false);
		}
	}

	@Then("User clicks on Drinks option")
	public void user_clicks_on_drinks_option() throws InterruptedException {
		menuPage.clickDrinksMenu();
		Thread.sleep(3000);
		reportListener.logStep("User Clicked on Drinks option", true);

	}

	@Then("User select Pepsi option to add into the Basket")
	public void user_select_pepsi_option_to_add_into_the_basket() throws InterruptedException {
		menuPage.addPepsiToBasket();
		Thread.sleep(3000);
		reportListener.logStep("User selects Pepsi option to add into the Basket", true);

	}

	@Then("User see {int} items are showing under checkout button")
	public void user_see_items_are_showing_under_checkout_button(int expectedItemCount) {

		boolean isItemCountCorrect = checkoutPage.validateItemCount(expectedItemCount);
		if (isItemCountCorrect) {
			reportListener.logStep("User sees items are showing under checkout button: " + expectedItemCount, true);
		} else {
			reportListener.logStep("User sees items are showing under checkout button: " + expectedItemCount, false);
		}
	}

	@Then("User see total price is now more than before")
	public void user_see_total_price_is_now_more_than_before() {

		boolean isTotalPriceMoreThanBefore = checkoutPage.isTotalPriceMoreThanBefore();

		if (isTotalPriceMoreThanBefore) {
			reportListener.logStep("Total Price is now more than before", true);
		} else {
			reportListener.logStep("Total Price is now more than before", false);
		}

	}

	@Then("User remove the Pizza item from Basket")
	public void user_remove_the_pizza_item_from_basket() throws InterruptedException {
		basketPage.removeItemFromBasket();
//		Thread.sleep(3000);
		reportListener.logStep("User removes the Pizza item from Basket", true);

	}

	@Then("see Price tag got removed from the checkout button")
	public void see_price_tag_got_removed_from_the_checkout_button() {

		boolean isPriceTagRemoved = checkoutPage.priceTagRemovedFromCheckoutButton();
		if (isPriceTagRemoved) {
			reportListener.logStep("Price tag got removed from the checkout button", true);
		} else {
			reportListener.logStep("Price tag got removed from the checkout button", false);
		}
	}

	@Then("User see {int} item showing in checkout button")
	public void user_see_item_showing_in_checkout_button(Integer expectedItemCount) {

		boolean isItemCountCorrect = checkoutPage.validateItemCount(expectedItemCount);

		if (isItemCountCorrect) {
			reportListener.logStep("User sees items are showing under checkout button: " + expectedItemCount, true);
		} else {
			reportListener.logStep("User sees items are showing under checkout button: " + expectedItemCount, false);
		}

	}

	@Then("User Clicks on Checkout button")
	public void user_clicks_on_checkout_button() {
		checkoutPage.clickCheckoutButton();
		reportListener.logStep("User Clicks on Checkout Button", true);
	}

	@Then("User see minimum order required pop up is getting displayed")
	public void user_see_minimum_order_required_pop_up_is_getting_displayed() {
		boolean isMinimumOrderPopupDisplayed = checkoutPage.isMinimumOrderPopup();

		if (isMinimumOrderPopupDisplayed) {
			reportListener.logStep("Minimum order required pop-up is displayed.", true);
		} else {
			reportListener.logStep("Minimum order required pop-up is displayed.", false);
		}

	}

}
