package opencart.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.qameta.allure.Allure;

import java.util.Properties;



public class Homepage {
	
	WebDriver driver;
	WebDriverWait wait; 
	Properties property;
	
	//webelements
By my_Account = By.xpath("//ul[@class='list-inline']//a[@class='dropdown-toggle']");
By login = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[contains(text(),'Login')]");
By register = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[contains(text(),'Register')]");
By x =By.xpath("//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']//span");
By handside_menu=By.xpath("//a[contains(text(),'Tablets (1)')]");
By sort= By.xpath("//select[@id='input-sort']");
By sort_AZ=By.xpath("option[contains(text(),'Name (A - Z)')]");
By sort_ZA=By.xpath("option[contains(text(),'Name (Z - A)')]");

// search By Name 
By search_input= By.xpath("//div[@id='search']//input");
By search_button= By.xpath("//div[@id='search']//span[@class='input-group-btn']//button");
By search_header_ele= By.xpath("//div[@id='content']//h1");
By search_input2=By.xpath("//input[@id='input-search']");
By select_categories= By.xpath("//select[@name='category_id']");
By searchBtn=By.xpath("//input[@id='button-search']");
By no_product=By.xpath("//p[contains(text(),'There is no product that matches the search criter')]");
By subcategory_checkbox=By.xpath("//input[@name='sub_category']");

// add to cart

By DeliveryDate_input=By.xpath("//input[@id='input-option225']");
By btn_deliveryDate_addcart= By.xpath("//button[@id='button-cart']");
By alert_msg=By.xpath("//div[@class='alert alert-success']");

By shopping_cart_element= By.xpath("//a[@title='Shopping Cart']");

By shopping_cart_list=By.xpath("//table[@class='table table-bordered']//tbody");



//Constructor
	public Homepage(WebDriver driver , Properties property ) {
		this.driver = driver;
		this.property = property;
		 wait = new WebDriverWait(driver, 60);
	}

		
	
	public String add_shopping_cart(String product , boolean deliveryDate , String product_delivery_Date) {
		
    String productPrice= " ";
 
		 List <WebElement> listOf_Products = driver.findElements(By.xpath("//div[contains(@class, 'product-grid col')]"));
      
         
         for(int i=1; i <= listOf_Products.size(); i++)
         {    	
        	
        	 WebElement product_title = driver.findElement(By.xpath("//div[contains(@class, 'product-grid col')]["+i+"]//h4//a"));
             	 String Actual_product_Tittle = product_title.getText();
   	      	 
  	 
        	    if(Actual_product_Tittle.contains(product))
        	     {
        	    	if (deliveryDate == false) {
        	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'product-grid col')]["+i+"]//button[contains(@onclick, 'cart.add')]")));  
        	    	WebElement addshopping_btn_ = driver.findElement(By.xpath("//div[contains(@class, 'product-grid col')]["+i+"]//button[contains(@onclick, 'cart.add')]"));			
        	    	addshopping_btn_.click();
  
        	    	WebElement product_price = driver.findElement(By.xpath("//div[contains(@class, 'product-grid col')]["+i+"]//p[@class='price']"));
             	 String Actual_product_price = product_price.getText();
              	 String[] Actual_product_price_= Actual_product_price.split("\n",0);
              	 
              	  productPrice =Actual_product_price_[0];
              	 
        			return productPrice;
        			
        			
        			}
        	    	
        	    	else if (deliveryDate == true) {
        	    		
        	    		WebElement product_price = driver.findElement(By.xpath("//div[contains(@class, 'product-grid col')]["+i+"]//p[@class='price']"));
                       	String  Actual_product_price = product_price.getText();
                        	 String[] Actual_product_price_= Actual_product_price.split("\n",0);
                        	 
                        	  productPrice =Actual_product_price_[0];
        	    		
        	    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'product-grid col')]["+i+"]//button[contains(@onclick, 'cart.add')]")));  
            	    	WebElement addshopping_btn_ = driver.findElement(By.xpath("//div[contains(@class, 'product-grid col')]["+i+"]//button[contains(@onclick, 'cart.add')]"));			
            	    	addshopping_btn_.click();
            	    	
            	    	
            	    	wait.until(ExpectedConditions.visibilityOfElementLocated(DeliveryDate_input));  
            	    	WebElement deliveryDate_input = driver.findElement(DeliveryDate_input);
            	    	deliveryDate_input.sendKeys(product_delivery_Date);
            	    	
            	    	wait.until(ExpectedConditions.visibilityOfElementLocated(btn_deliveryDate_addcart)); 
            	    	WebElement add_tocard = driver.findElement(btn_deliveryDate_addcart);			
            	    	add_tocard.click();
            	    
          	 
              			return productPrice;
               					
        	    	}
        	    	break;
                 }
        	    
         }
        
	    	     
            
		return productPrice;
		
	}
		
	public Boolean validate_msg_addtocart(String product) {
		
		boolean x = false;
		
	 wait.until(ExpectedConditions.visibilityOfElementLocated(alert_msg)); 
		WebElement addshopping_btn_ = driver.findElement(alert_msg);
		String actual_returnedMSG = addshopping_btn_.getText();
		String Tottal_MSG= property.getProperty("add_to_cart_msg") +" " + product;
		  if(actual_returnedMSG.contains(Tottal_MSG))
 	     {
			  x=true;
 	     }
		
		return x;
	}
	
	public boolean check_product_addedinshoppingcart_table(String product, double price) throws InterruptedException {

		boolean product_exist = false;
					
		Thread.sleep(1000);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'btn-block btn-lg')]"))); 
			WebElement shoppingCart_btn_1 = driver.findElement(By.xpath("//button[contains(@class,'btn-block btn-lg')]"));
			shoppingCart_btn_1.click();
			
			
			WebElement shoppingCart_btn_2 = driver.findElement(By.xpath("//p[@class='text-right']/a[1]"));
			shoppingCart_btn_2.click();
			
			 List <WebElement> shopping_cart_table = driver.findElements(shopping_cart_list);
	 
			 for(int i=1; i <= shopping_cart_table.size(); i++)
	         {
		
				  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='table table-bordered']//tr["+i+"]//td[2]//a"))); 
					 WebElement productName_elem = driver.findElement(By.xpath("//table[@class='table table-bordered']//tr["+i+"]//td[2]//a"));
					String productname_= productName_elem.getText();
					
					 
						
					 if(productname_.contains(product))
					 {  
						 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form//tbody/tr["+i+"]/td[4]//input[@type='text']"))); 
						WebElement quantity= driver.findElement(By.xpath("//form//tbody/tr["+i+"]/td[4]//input[@type='text']"));
						quantity.clear();
						quantity.sendKeys("1");
						
						WebElement refresh=driver.findElement(By.xpath("//form//tbody/tr["+i+"]/td[4]//button[@type='submit']"));
						refresh.click();
						
					
						 WebElement productprice_elem = driver.findElement(By.xpath("//form//tbody/tr["+i+"]/td[5]"));
							String productprice_ = productprice_elem.getText();
						String productprice_1 	= productprice_.replace("$", "");
						 double productprice_f = Double.parseDouble(productprice_1);
					 
						if (productprice_f == price ) {
							
						 product_exist = true ;
						 break;
						 
						}
					 }
				   
	         }
		
		return product_exist;
		
	}
	
	public boolean validate_tottal_price(double price1, double price2)
	{
		boolean tottal_price_validated = false;	
		double tottal_price =price1 + price2  ;
		
	
		 wait.until(ExpectedConditions.visibilityOfElementLocated(shopping_cart_element)); 
			WebElement shoppingCart_btn_ = driver.findElement(shopping_cart_element);
			shoppingCart_btn_.click();
			
			 WebElement productprice_elem = driver.findElement(By.xpath("//div[@class='col-sm-4 col-sm-offset-8']//table[@class='table table-bordered']//tr[4]//td[2]"));
				String productprice_actual = productprice_elem.getText();		
				String Tottalprice_1 	= productprice_actual.replace("$", "");
				 double tottal_price_actual = Double.parseDouble(Tottalprice_1);
		
				
			 if (tottal_price_actual == tottal_price) 
			 {
				 
				 tottal_price_validated = true;
				
			 }
			      
		return tottal_price_validated;
	}
	
	
	
	public boolean search_by_name (String product) {
		
		boolean x = true;
		 ArrayList<String> obtainedList = new ArrayList<String>(); 
		 
		wait.until(ExpectedConditions.visibilityOfElementLocated(search_input));  
		 WebElement email_element = driver.findElement(search_input);
		 email_element.sendKeys(product);
		 
		 WebElement search_btn_element = driver.findElement(search_button);			
		 search_btn_element.click();
		 
		 WebElement search_header = driver.findElement(search_header_ele);
		 String actualString = search_header.getText();
			Assert.assertTrue(actualString.contains(product));
		 
         
		 List <WebElement> listOf_SearchProducts = driver.findElements(By.xpath("//div[@class='row']//div[@class='product-thumb']//a//img"));
         
         for(int i=0; i < listOf_SearchProducts.size(); i++)
         {    	
        	 obtainedList.add(listOf_SearchProducts.get(i).getAttribute("title").toUpperCase());          
         }
         
         for (int i=0; i < obtainedList.size(); i++) {
        if(!obtainedList.get(i).contains(product.toUpperCase())) {
        	 x=false;	
        }
        
         }
		return x;
              
         }
		
		 public boolean search_sub_category(String product) 
		 {
			 WebElement searchBtn_ele;
			 boolean x = true;
			 wait.until(ExpectedConditions.visibilityOfElementLocated(search_button)); 
			 WebElement search_btn_element = driver.findElement(search_button);			
			 search_btn_element.click();
			 		 
			 WebElement email_element = driver.findElement(search_input2);
			 email_element.sendKeys(product);
			 
			 Select category= new Select(driver.findElement(select_categories));
			 category.selectByIndex(7);
			 
			  searchBtn_ele=driver.findElement(searchBtn);
			 searchBtn_ele.click();
			 
		WebElement no_product_ele=driver.findElement(no_product);
		 String actualstring= no_product_ele.getText();
		 Assert.assertTrue(actualstring.contains("no product"));
		 
		 // search in subcategory checkbox 
		 WebElement checkbox=driver.findElement(subcategory_checkbox);
		 checkbox.click();
		 
		  searchBtn_ele=driver.findElement(searchBtn);
		 searchBtn_ele.click();
		 
		 ArrayList<String> obtainedList = new ArrayList<String>(); 
List <WebElement> listOf_SearchProducts = driver.findElements(By.xpath("//div[@class='row']//div[@class='product-thumb']//a//img"));
         
         for(int i=0; i < listOf_SearchProducts.size(); i++)
         {    	
        	 obtainedList.add(listOf_SearchProducts.get(i).getAttribute("title").toUpperCase());          
         }
         
         for (int i=0; i < obtainedList.size(); i++) {
        if(!obtainedList.get(i).contains(product.toUpperCase())) {
        	 x=false;	
        }
        
         }
		  return x;
			 
		 }
	public void sort_Byname_ASC() 
	{  
		wait.until(ExpectedConditions.visibilityOfElementLocated(sort)); 
		Select sorting= new Select(driver.findElement(sort));
			sorting.selectByIndex(1);

		ArrayList<String> obtainedList = new ArrayList<String>(); 
		List<WebElement> listproduct= driver.findElements(By.xpath("//div[@id='content']//div[@class='row']//H4//a"));
		for(int i=0;i< listproduct.size(); i++){
		   obtainedList.add(listproduct.get(i).getText().trim().toUpperCase());
		}
		ArrayList<String> sortedList = new ArrayList<>();   
		for(String s:obtainedList){
		sortedList.add(s);
		}
		Collections.sort(sortedList);
		Assert.assertTrue(sortedList.equals(obtainedList));
		
		 Allure.step("Product is Sorted ASC As Expected"); 	
			
	}
		
	
	
	public void sort_Byname_DESC() 
	{ 
		wait.until(ExpectedConditions.visibilityOfElementLocated(sort)); 
		Select sorting= new Select(driver.findElement(sort));
		sorting.selectByIndex(2);
		
		ArrayList<String> obtainedList = new ArrayList<String>(); 
		List<WebElement> listproduct= driver.findElements(By.xpath("//div[@id='content']//div[@class='row']//H4//a"));
		for(int i=0;i< listproduct.size(); i++){
		   obtainedList.add(listproduct.get(i).getText().trim().toUpperCase());
		}
		ArrayList<String> sortedList = new ArrayList<>();   
		for(String s:obtainedList){
		sortedList.add(s);
		}
		Collections.sort(sortedList);
		 Collections.reverse(sortedList);
		Assert.assertTrue(sortedList.equals(obtainedList));
		
		 Allure.step("Product is Sorted DESC As Expected"); 	
	}
	
	
	public void open_Login() throws InterruptedException  {
		
	 Thread.sleep(2000);
  
		wait.until(ExpectedConditions.visibilityOfElementLocated(my_Account));  
      
	 driver.findElement(my_Account).click();
	 driver.findElement(login).click();
		
	}
	
	public void open_Register() throws InterruptedException  {
		
	 Thread.sleep(2000);
  
		wait.until(ExpectedConditions.visibilityOfElementLocated(my_Account));  
      
	 driver.findElement(my_Account).click();
	 driver.findElement(register).click();
		
	}

	
	public Boolean check_currency(String currency1){
		String actualString = null ;
		
		actualString = driver.findElement(x).getText();
		if(actualString.contains(currency1))
			return true;
		else
			return false;
		
	}
	
	
	
  public void change_curr(String currency){
   
	  String currency_actualString= null;
	  
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pull-left']"))); 
	  WebElement currency_menu=driver.findElement(By.xpath("//div[@class='pull-left']"));
	  currency_menu.click();
	  
	  List <WebElement> listOfProducts = driver.findElements(By.xpath("//div[@class='btn-group open']//ul//li"));
		 for(int i=1; i <= listOfProducts.size(); i++)
         { 
			 
			 WebElement currency_webelement = driver.findElement(By.xpath("//div[@class='btn-group open']//ul//li["+i+"]"));
			 currency_actualString = currency_webelement.getText();
			 if(currency_actualString.contains(currency))
			 { 
				 currency_webelement.click();
				 break;
			 }
         }

	}
  
  public String check_bread_crumb(String bread_crumb){
	  
	  String actualString=null;
		List <WebElement> listOfProducts = driver.findElements(By.xpath("//ul[@class='breadcrumb']//li"));
        
        for(int i=1; i <= listOfProducts.size(); i++)
        {
               WebElement productString = driver.findElement(By.xpath("//ul[@class='breadcrumb']//li["+i+"]"));
               if (i == listOfProducts.size() )
               {
              actualString = productString.getText();
               if(actualString.contains(bread_crumb)) {
            	   return actualString;        
               }
               }
        }
		return actualString;
	  	
	}

  public String check_active_product() {
	 String active_Actual_product = null;
	 
	  List <WebElement> listOfProducts = driver.findElements(By.xpath("//div[@class='list-group']/a"));
      
      for(int i=1; i <= listOfProducts.size(); i++)
      {
    	  WebElement productString = driver.findElement(By.xpath("//div[@class='list-group']/a["+i+"]"));
    	 // productString.getClass();
    	 String class_Name= productString.getAttribute("class");
    	 
    	 if (class_Name.contains("active")) {
    		  WebElement productString_ = driver.findElement(By.xpath("//div[@class='list-group']/a["+i+"]"));
    		 active_Actual_product = productString_.getText();
    		  
    		 return active_Actual_product;  		 
    	 }
    	
      }
	return active_Actual_product;
		
        }


	
}


