/*
 * 4. As a User, I should be presented with a no products found search screen, when attempting to search for an item that doesn’t exist, on Amazon
      Test steps:
 	  a) Navigate to https://www.amazon.ca
	  b) Input “[alpja]” in the search bar
	  c) Notice “Your search "[alpja]" did not match any products.” text and no results are displayed.
 */

package amazonTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.Assert;

public class AmazonTest4 {
	
	String searchBar = "twotabsearchtextbox";
	public WebDriver driver;
	
	@Test
	public void verifyInvalidSearch() {
		
		String noResultsText = "did not match any products.";
		String suggestionText = "Try something like";
		
		System.setProperty("webdriver.chrome.driver", "/Users/sophiali/eclipse-workspace/SeleniumProject2/src/amazonTest/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.amazon.ca");
		
		driver.findElement(By.id(searchBar)).click();
		driver.findElement(By.id(searchBar)).clear();
		driver.findElement(By.id(searchBar)).sendKeys("[alpja]");
		driver.findElement(By.id(searchBar)).sendKeys(Keys.RETURN);
		
		Assert.assertTrue(driver.getPageSource().contains(noResultsText));
		Assert.assertTrue(driver.getPageSource().contains(suggestionText));
	}
	
	@AfterTest
	public void endSession() {
	
		driver.quit();	
		
	}

}