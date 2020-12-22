package opencart.pages;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class LoginPage {
	
	
	WebDriver driver;
	WebDriverWait wait; 
	Properties property;
	
	// Webelements
By email = By.id("input-email");
By password = By.id("input-password");
By login_Btn = By.xpath("//input[@class='btn btn-primary']");
By error_Login = By.xpath("//div[@class='alert alert-danger']");
By my_account = By.xpath("//h2[contains(text(),'My Account')]");
	

	// Constructor
	public LoginPage(WebDriver driver,Properties property) {
		this.driver = driver;
		this.property=property;
		 wait = new WebDriverWait(driver, 60);
	}
	
			
		public void login(String Email , String pw) {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(email));  
			 WebElement email_element = driver.findElement(email);
			 email_element.sendKeys(Email);
				
				WebElement password_element = driver.findElement(password);	
				password_element.sendKeys(pw);
				
			WebElement Login_btn_element = driver.findElement(login_Btn);			
			Login_btn_element.click();
			
		}
		
		public void verify_error_display()  {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(error_Login));  
	    	String actualString = driver.findElement(error_Login).getText();
			
			Assert.assertTrue(actualString.contains(property.getProperty("login_error")));

		
		}
		
		public void my_account_isDisplayed() {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(my_account));  
            String actualString = driver.findElement(my_account).getText();
			
			Assert.assertTrue(actualString.contains("My Account"));
		}


	

}
