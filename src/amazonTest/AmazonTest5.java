/* 
 * 5. As a User, I can proceed to checkout, after I’ve added an item to my cart from search 
 *    Test steps:
	  a) Navigate to https://www.amazon.ca
	  b) Input “memory card” in the search bar
	  c) Tap on the “Sandisk Ultra 32GB Class 10 SDHC UHS-I Memory Card Up to 80MB, Grey/Black (SDSDUNC-032G-GN6IN)” memory card
	  d) Tap the add to cart button
	  e) Tap the proceed to checkout button
	  f) Sign-in w/ credentials
	  g) User should be taken to create shipping address screen.
 */

package amazonTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

public class AmazonTest5 {
	
	String searchBar = "twotabsearchtextbox";
	String yourAccountSelector = "nav-link-yourAccount";
	String signOutSelector = "nav-item-signout-sa";
	public WebDriver driver;
	
	@BeforeTest
	public void logOutUser() {
		
		System.setProperty("webdriver.chrome.driver", "/Users/sophiali/eclipse-workspace/SeleniumProject2/src/amazonTest/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.amazon.ca");
		
		if (driver.findElement(By.id(yourAccountSelector)).getText().contains("Hello,")) {
			System.out.println("User is not logged out of Amazon");

			Actions action  = new Actions(driver);
			action.moveToElement(driver.findElement(By.id(yourAccountSelector))).perform();
			
			driver.findElement(By.id(signOutSelector)).click();
			driver.navigate().to("https://www.amazon.ca");
		}
		
		else {
			System.out.println("User is logged out of Amazon");
		}
		
	}
	
	@Test
	public void verifyProceedCheckout() {
		
		String addToCartBtn = "add-to-cart-button";
		String proceedToCheckoutBtn = "hlb-ptc-btn-native";
		String product = "memory card";
		String serialNumber = "SDSDUNC-032G-GN6IN";
		String email = "misterpineapple1@gmail.com";
		String password = "SIcodingchallenge";
		String emailField = "ap_email";
		String passwordField = "ap_password";
		String selectAddressText = "Select a Shipping Address";
		
		driver.findElement(By.id(searchBar)).click();
		driver.findElement(By.id(searchBar)).clear();
		driver.findElement(By.id(searchBar)).sendKeys(product);
		driver.findElement(By.id(searchBar)).sendKeys(Keys.RETURN);
		driver.findElement(By.partialLinkText(serialNumber)).click(); //finds the product link using serial number
		driver.findElement(By.id(addToCartBtn)).click();
		driver.findElement(By.id(proceedToCheckoutBtn)).click();
		
		driver.findElement(By.id(emailField)).click();
		driver.findElement(By.id(emailField)).clear();
		driver.findElement(By.id(emailField)).sendKeys(email);
		
		driver.findElement(By.id("continue")).click();
		
		driver.findElement(By.id(passwordField)).click();
		driver.findElement(By.id(passwordField)).clear();
		driver.findElement(By.id(passwordField)).sendKeys(password);
		
		driver.findElement(By.id("signInSubmit")).click();
		
		Assert.assertTrue(driver.getPageSource().contains(selectAddressText));
	}
	
	@AfterTest
	public void endSession() {
		driver.quit();	
	}

}
