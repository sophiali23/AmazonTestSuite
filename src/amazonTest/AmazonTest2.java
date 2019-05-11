/*
 * 2. As a User, I should have the ability to sign in to Amazon 
 *    Test steps:
      a) Ensure user is logged out of amazon
      b) Navigate to https://www.amazon.ca
      c) Notice in top right corner, “Hello. Sign In. Your Account” should be displayed
      d) Tap “Hello. Sign In. Your Account” button
      e) Tap Sign In Button
      f) Sign In w/ credentials
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

public class AmazonTest2 {
	
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
	public void verifyUserSignIn() {
			
		String email = "misterpineapple1@gmail.com";
		String password = "SIcodingchallenge";
		String emailField = "ap_email";
		String passwordField = "ap_password";
		
		Assert.assertTrue(driver.findElement(By.id(yourAccountSelector)).getText().contains("Hello. Sign in"));
		Assert.assertTrue(driver.findElement(By.id(yourAccountSelector)).getText().contains("Your Account"));
		
		driver.findElement(By.id(yourAccountSelector)).click();
		driver.findElement(By.id(emailField)).click();
		driver.findElement(By.id(emailField)).clear();
		driver.findElement(By.id(emailField)).sendKeys(email);
		
		driver.findElement(By.id("continue")).click();
		
		driver.findElement(By.id(passwordField)).click();
		driver.findElement(By.id(passwordField)).clear();
		driver.findElement(By.id(passwordField)).sendKeys(password);
		
		driver.findElement(By.id("signInSubmit")).click();
		
		Assert.assertTrue(driver.findElement(By.id(yourAccountSelector)).getText().contains("Hello, Sophia"));
		System.out.println("User signed in successfully");
	}
	
	@AfterTest
	public void endSession() {
	
		driver.quit();	
		
	}

}