@Smoke
Feature: Validate Pizzahut pizza order flow

  Scenario Outline: Validate Pizzahut pizza order flow
    Given User launch Pizzahut application with "<URL>"
    When User waits for auto location black pop-up screen
    Then User closes the pop-up screen
    And User see pop up for delivery asking for enter location
    Then User type address as "<Location>"
    And User select first auto populate drop down option
    When User navigate to details page
    Then User validate vegetarian radio button flag is off
    And User clicks on Pizzas menu bar option
    When User select add button of any pizza from Recommended
    Then User see that the pizza is getting added under Your Basket
    And User validate pizza price plus Tax is checkout price
    Then User validate checkout button contains Item count <ItemCount>
    And User validate checkout button contains total price count <TotalPrice>
    Then User clicks on Drinks option
    And User select Pepsi option to add into the Basket
    Then User see 2 items are showing under checkout button
    And User see total price is now more than before
    Then User remove the Pizza item from Basket
    And see Price tag got removed from the checkout button
    And User see 1 item showing in checkout button
    Then User Clicks on Checkout button
    And User see minimum order required pop up is getting displayed
    
    
    
    

    Examples: 
      | URL                         | Location            |ItemCount|TotalPrice|
      #| https://www.pizzahut.co.in/ | Lulu Mall Bangalore |
      | https://www.pizzahut.co.in/ | maddilapalem |1|466.20|
