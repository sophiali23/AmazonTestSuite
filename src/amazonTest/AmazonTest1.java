/*
 * 1. As a User, I should be presented with Sign In button CTA, when I navigate to Amazon 
 *    Test steps:
	  a) Ensure user is logged out of amazon
	  b) Navigate to https://www.amazon.ca
	  c) Notice in top right corner, “Hello. Sign In. Your Account” should be displayed 
*/

package amazonTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

public class AmazonTest1 {
	
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
	public void verifySignInButton() {
		
		Assert.assertTrue(driver.findElement(By.id(yourAccountSelector)).getText().contains("Hello. Sign in"));
		Assert.assertTrue(driver.findElement(By.id(yourAccountSelector)).getText().contains("Your Account"));
	
	}
	
	@AfterTest
	public void endSession() {
		
		driver.quit();	
	
	}
}