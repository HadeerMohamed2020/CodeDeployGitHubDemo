package opencart.pages;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Menu_Products {

	WebDriver driver;
	WebDriverWait wait; 

	// Constructor
		public Menu_Products(WebDriver driver) {
			this.driver = driver;
			wait = new WebDriverWait(driver, 60);
		}
       
		public String open_menu1(String product) {
			
           String actualString = null;
   	
			List <WebElement> listOfProducts = driver.findElements(By.xpath("//nav[@id='menu']//ul"));
            
            for(int i=1; i <= listOfProducts.size(); i++)
            {
                   WebElement productString = driver.findElement(By.xpath("//nav[@id='menu']//ul[@class='nav navbar-nav']/li["+i+"]/a"));
                    actualString = productString.getText();
                   if(actualString.equals(product)) {
                         productString.click();
                   break;
                   }
            }
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='content']//h2")));  
            WebElement x= driver.findElement(By.xpath("//div[@id='content']//h2"));
            actualString = x.getText();
            return actualString;
			
		}
		
		public String open_menu2(String product , String subproduct) 
	       {    
	    	   String actualString = null;
	    	   String product_actualString = null;
	    	   String subproduct_actualString = null;
	    	   	
				List <WebElement> listOfProducts = driver.findElements(By.xpath("//nav[@id='menu']//ul"));
				 for(int i=1; i <= listOfProducts.size(); i++)
		            {
					 WebElement productString = driver.findElement(By.xpath("//nav[@id='menu']//ul[@class='nav navbar-nav']/li["+i+"]/a"));
					 product_actualString = productString.getText();
					 if(product_actualString.equals(product))
					 { 
	                     productString.click();
	                     
	                 WebElement last_element= driver.findElement(By.xpath("//nav[@id='menu']//ul[@class='nav navbar-nav']/li["+i+"]/div/a"));
	                    String product_actualstring2=last_element.getText();
	             
	             if (product_actualstring2.equals(subproduct)) {
	            	 last_element.click();
	             }
	             else {
	                    
	                     List <WebElement> listOfProducts2 = driver.findElements(By.xpath("//nav[@id='menu']//ul[@class='nav navbar-nav']/li["+i+"]//ul/li"));
	                     for(int k=1; k <= listOfProducts2.size(); k++) {
	                    	
	                    	 WebElement sub_productString = driver.findElement(By.xpath("//nav[@id='menu']//ul[@class='nav navbar-nav']/li["+i+"]//div//ul/li["+k+"]"));
	                    	 subproduct_actualString = sub_productString.getText();
	                    	 if (subproduct_actualString.equals(subproduct)) {
	                    	 sub_productString.click();
	                    	// x = true ;
	                    	 
	                    	break;
	                    	 }
	                    	 
	                    	 
	                     }
	                 }
		
	                }
			}
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='content']//h2")));  
		            WebElement product_name= driver.findElement(By.xpath("//div[@id='content']//h2"));
		            actualString = product_name.getText();
		            return actualString;
	       }

}
