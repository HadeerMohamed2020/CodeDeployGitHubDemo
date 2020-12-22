package opencart.pages;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class RegisterPage {
	
	WebDriver driver;
	WebDriverWait wait; 
	Properties property;
	
	// Webelements
	By first_Name = By.id("input-firstname");
	By last_Name =	By.id("input-lastname");
	By email = By.id("input-email");
	By telephone = By.id("input-telephone");
	By address1= By.id("input-address-1");
	By city = By.id("input-city");
	By postCode = By.id("input-postcode");
	By region = By.id("input-zone");
	By selected_Region= By.xpath("//select[@id='input-zone']//option[3]"); 
	By passWord = By.id("input-password");
	By confirm_Password = By.id("input-confirm");
    By agreement= By.xpath("//input[@name='agree']");
    By continue_Btn = By.xpath("//input[@class='btn btn-primary']");
    By accountCreated_msg = By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]");
    
    By my_Account = By.xpath("//ul[@class='list-inline']//a[@class='dropdown-toggle']");
    By log_Out= By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[contains(text(),'Logout')]");
	By log_out_msg= By.xpath("//h1[contains(text(),'Account Logout')]");
	
    // Verify elments _Register _with Errors 
    By last_Name_Error =By.xpath("//input[@id='input-lastname']/following-sibling::div");
    By email_Error = By.xpath("//input[@id='input-email']/following-sibling::div");
    By telephone_Error = By.xpath("//input[@id='input-telephone']/following-sibling::div");
    By adress_Error = By.xpath("//input[@id='input-address-1']/following-sibling::div");
    By city_Error = By.xpath("//input[@id='input-city']/following-sibling::div");
    By postcode_Error = By.xpath("//input[@id='input-postcode']/following-sibling::div");
    By password_Error = By.xpath("//input[@id='input-password']/following-sibling::div");
	
 // Constructor
 	public RegisterPage(WebDriver driver , Properties property ) {
 		this.driver = driver;
 		this.property = property;
 		 wait = new WebDriverWait(driver, 60);
 	}
	
	
	public void register(String FirstName , String LastName , String Email , String Telephone , String Adress1 , String City , String Postcode , String Password , String Confirm_Password) {
		 WebElement first_Name_element = driver.findElement(first_Name);
		 first_Name_element.sendKeys(FirstName);
		 
		 WebElement last_Name_element = driver.findElement(last_Name);
		 last_Name_element.sendKeys(LastName);
		 
		 WebElement email_element = driver.findElement(email);
		 email_element.sendKeys(Email);
		
		 WebElement telephone_element = driver.findElement(telephone);
		 telephone_element.sendKeys(Telephone);
		 
		 WebElement adress1_element = driver.findElement(address1);
		 adress1_element.sendKeys(Adress1);
		 
		 WebElement city_element = driver.findElement(city);
		 city_element.sendKeys(City);
		 
		 WebElement postcode_element = driver.findElement(postCode);
		 postcode_element.sendKeys(Postcode);
		 
		 WebElement region_element = driver.findElement(region);
		 region_element.isSelected();
		 
		 WebElement selected_region_element = driver.findElement(selected_Region);
		 selected_region_element.click();
		 
		 WebElement pass_element = driver.findElement(passWord);
		 pass_element.sendKeys(Password);
		 
		 WebElement confirm_pass_element = driver.findElement(confirm_Password);
		 confirm_pass_element.sendKeys(Confirm_Password);
		  
		 WebElement agreement_element = driver.findElement(agreement);
		 agreement_element.click();
		 
		 WebElement continue_Btn_element = driver.findElement(continue_Btn);
		 continue_Btn_element.click();
		 	
	}
	
	public void verify_AccountCreated_msg() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreated_msg));  
		Thread.sleep(2000);
    	String actualString = driver.findElement(accountCreated_msg).getText();
		
		Assert.assertTrue(actualString.contains(property.getProperty("accountCreated_message")));

	}
	
	public void verify_Register_with_Errors() throws IOException { 
        String last_Name_Error_Actual = driver.findElement(last_Name_Error).getText();	
		   Assert.assertTrue(last_Name_Error_Actual.contains(property.getProperty("last_name_error")));
		
	    String email_Error_actual = driver.findElement(email_Error).getText();	
			Assert.assertTrue(email_Error_actual.contains(property.getProperty("email_error")));
			
		String telephone_Error_actual = driver.findElement(telephone_Error).getText();	
			Assert.assertTrue(telephone_Error_actual.contains(property.getProperty("telephone_error")));
			
		String adress_Error_actual = driver.findElement(adress_Error).getText();	
			Assert.assertTrue(adress_Error_actual.contains(property.getProperty("address_error")));
					
		String city_Error_actual = driver.findElement(city_Error).getText();	
			Assert.assertTrue(city_Error_actual.contains(property.getProperty("city_error")));
					
		String postcode_Error_actual = driver.findElement(postcode_Error).getText();	
			Assert.assertTrue(postcode_Error_actual.contains(property.getProperty("postcode_error"))); 
						
	    String password_Error_actual = driver.findElement(password_Error).getText();	
			Assert.assertTrue(password_Error_actual.contains(property.getProperty("password_error"))); 
		   
	}
	
	
	public void log_Out() throws InterruptedException {
		driver.findElement(my_Account).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(log_Out));  
		Thread.sleep(2000);
		driver.findElement(log_Out).click();
	}
	

	public void verify_LogOut() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(log_out_msg));  
		Thread.sleep(2000);
    	String actualString = driver.findElement(log_out_msg).getText();
		
		Assert.assertTrue(actualString.contains("Account Logout"));
			
	}

}
