package opencart.pages;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

	
	WebDriver driver;
	WebDriverWait wait; 
	Properties property;
	
	//webelements
	
	By firstName_payment = By.id("input-payment-firstname");
	By lastName_payment = By.id("input-payment-lastname");
	By address_payment = By.id("input-payment-address-1");
	By city_payment = By.id("input-payment-city");
	By postcode_payment = By.id("input-payment-postcode");	
	By selected_Region_payment= By.xpath("//select[@id='input-payment-zone']//option[3]"); 
	
	By firstName_shipping = By.id("input-shipping-firstname");
	By lastName_shipping = By.id("input-shipping-lastname");
	By address_shipping = By.id("input-shipping-address-1");
	By city_shipping = By.id("input-shipping-city");
	By postcode_shipping = By.id("input-shipping-postcode");	
	By selected_Region_shipping= By.xpath("//select[@id='input-shipping-zone']//option[3]"); 
	
	//Constructor
		public CheckoutPage(WebDriver driver , Properties property ) {
			this.driver = driver;
			this.property = property;
			 wait = new WebDriverWait(driver, 60);
		}
		
 public void Billing_details(String fname, String lName , String Adress , String City , String Postcode
		 ) throws InterruptedException {
	 JavascriptExecutor js = (JavascriptExecutor) driver;

	 
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'btn btn-primary')]")));  
	 WebElement checkout_btn = driver.findElement(By.xpath("//a[contains(@class,'btn btn-primary')]")); 
	   Thread.sleep(2000); 
	 //This will scroll the web page till end.	
	      js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
	            checkout_btn.click();
	 
	 Thread.sleep(1000); 
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='panel panel-default'][2]//div[@class='radio']//input[@value='new']")));  
	 WebElement new_address = driver.findElement(By.xpath("//div[@class='panel panel-default'][2]//div[@class='radio']//input[@value='new']"));
	 new_address.click();
	 
	 
	WebElement firstName_element= driver.findElement(firstName_payment);
	firstName_element.sendKeys(fname);
	
	WebElement lastName_element= driver.findElement(lastName_payment);
	lastName_element.sendKeys(lName);
	
	WebElement  adress_element = driver.findElement(address_payment);
	adress_element.sendKeys(Adress);
	
	WebElement  city_element = driver.findElement(city_payment);
	city_element.sendKeys(City);
	
	WebElement  postcode_element = driver.findElement(postcode_payment);
	postcode_element.sendKeys(Postcode);
	
	WebElement  region_element = driver.findElement(selected_Region_payment);
	region_element.click();
	
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='button-payment-address']"))); 
	WebElement continue_btn1=driver.findElement(By.xpath("//input[@id='button-payment-address']"));
	Thread.sleep(2000);
	   js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
	   continue_btn1.click();
 
        }		
 
 public void delivery_details(String fname, String lName , String Adress , String City
		 , String Postcode) throws InterruptedException {
	 
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	 
	
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='panel panel-default'][3]//div[@class='radio']//input[@value='new']")));  
	 WebElement new_address = driver.findElement(By.xpath("//div[@class='panel panel-default'][3]//div[@class='radio']//input[@value='new']"));
	 new_address.click();
	 
	 
	 WebElement firstName_element= driver.findElement(firstName_shipping);
		firstName_element.sendKeys(fname);
		
		WebElement lastName_element= driver.findElement(lastName_shipping);
		lastName_element.sendKeys(lName);
		
		WebElement  adress_element = driver.findElement(address_shipping);
		adress_element.sendKeys(Adress);
		
		WebElement  city_element = driver.findElement(city_shipping);
		city_element.sendKeys(City);
		
		WebElement  postcode_element = driver.findElement(postcode_shipping);
		postcode_element.sendKeys(Postcode);
		
		WebElement  region_element = driver.findElement(selected_Region_shipping);
		region_element.click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='button-shipping-address']"))); 
		WebElement continue_btn2=driver.findElement(By.xpath("//input[@id='button-shipping-address']"));
		Thread.sleep(2000);
		   js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
		   continue_btn2.click();
	 
       }
 
 public void delivery_method()
        {
	 
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	 
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='panel panel-default'][4]//textarea[@name='comment']")));  
	 WebElement comment_1 = driver.findElement(By.xpath("//div[@class='panel panel-default'][4]//textarea[@name='comment']"));
	 comment_1.sendKeys("tesssssssst");
	 
	 WebElement  continue_ele = driver.findElement(By.xpath("//div[@class='panel panel-default'][4]//input[@id='button-shipping-method']"));
	  js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
	 continue_ele.click();
		
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='agree']")));  
	 WebElement terms_condition = driver.findElement(By.xpath("//input[@name='agree']"));
	 terms_condition.click();
	 
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='button-payment-method']")));  
	 WebElement  continue_ele2 = driver.findElement(By.xpath("//input[@id='button-payment-method']"));
	  js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
	  continue_ele2.click();
		
			
           }
 
 public double check_tottalPrice()
 
           {
	
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr//td[5]"))); 
	 WebElement tottal_price_element = driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr//td[5]"));
    	String  Actual_product_price = tottal_price_element.getText();
    	 String productprice 	= Actual_product_price.replace("$", "");
    double tottal_price=  Double.parseDouble(productprice);
	 
	 return tottal_price;
	
           }
 
 public boolean check_Flat_shipping_Rate(double tottal_price) {
	 
	 boolean x = false;
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	 
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr//td[5]")));
	 WebElement tottal_price_element = driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']//tfoot//tr[5]//td[2]"));
 	       String  Actual_product_price = tottal_price_element.getText();
 	       String productprice 	= Actual_product_price.replace("$", "");
                double Product_tottal_price =  Double.parseDouble(productprice);
	 
	 if (Product_tottal_price > tottal_price) {
		 
		 x=true;
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Confirm Order']")));
		 WebElement confirm_order_btn = driver.findElement(By.xpath("//input[@value='Confirm Order']"));
		  js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
		 confirm_order_btn.click();
	 }
	 
	 return x;
 }
 
 public boolean validate_confirmation_msg() {
	 Boolean x= false ;
	 
	 WebElement msg_element = driver.findElement(By.xpath("//div[@class='row']/div[@id='content']/h1"));
	 String actual_returnedMSG = msg_element.getText();
	 
	 String Tottal_MSG= property.getProperty("order_confirmation_msg");
	  if(actual_returnedMSG.contains(Tottal_MSG))
     {
		  x=true;
     }
	 
	 return x;
		 
	 }
	 
 }
 

