/*
 * 3. As a User, I should have the ability to search for an item on Amazon 
 *    Test steps:
      a) Navigate to https://www.amazon.ca
      b) Input “memory card” in the search bar
      c) Notice results appear for memory
 */

package amazonTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.Assert;

public class AmazonTest3 {
	
	String searchBar = "twotabsearchtextbox";
	public WebDriver driver;
	
	@Test
	public void verifyValidSearch() {
		
		String searchResultCount = "s-result-count";
		String resultsText = "results for";
		String sortText = "Sort by";
		String product = "memory card";
		
		System.setProperty("webdriver.chrome.driver", "/Users/sophiali/eclipse-workspace/SeleniumProject2/src/amazonTest/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.amazon.ca");
		
		driver.findElement(By.id(searchBar)).click();
		driver.findElement(By.id(searchBar)).clear();
		driver.findElement(By.id(searchBar)).sendKeys(product);
		driver.findElement(By.id(searchBar)).sendKeys(Keys.RETURN);
		
		Assert.assertTrue(driver.getPageSource().contains(resultsText));
		Assert.assertTrue(driver.getPageSource().contains(sortText));
		System.out.println(driver.findElement(By.id(searchResultCount)).getText());
	}
	
	@AfterTest
	public void endSession() {
	
		driver.quit();	
		
	}

}