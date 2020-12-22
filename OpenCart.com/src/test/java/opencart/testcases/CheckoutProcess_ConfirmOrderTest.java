package opencart.testcases;

import opencart.Base.TestBase;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.qameta.allure.Allure;
import opencart.pages.CheckoutPage;
import opencart.pages.Homepage;
import opencart.pages.LoginPage;
import opencart.pages.Menu_Products;
import opencart.pages.RegisterPage;
import openchart.testdata.TestData;

public class CheckoutProcess_ConfirmOrderTest  extends TestBase {
	
	Homepage homepage_obj;
    LoginPage LoginPage_obj;
    RegisterPage RegisterPage_obj;
    Menu_Products Menu_Products_obj;
    CheckoutPage CheckoutPage_obj;
    
    
    @BeforeMethod
    public void valid_Login() throws InterruptedException{
    	homepage_obj = new Homepage(driver,property);
		homepage_obj.open_Login();
		LoginPage_obj = new LoginPage(driver,property);		
		LoginPage_obj.login(property.getProperty("valid-email1"), property.getProperty("valid-password"));	
	}
    
    @DataProvider(name = "confirmOrder")
   	public Object[][] checkcurrency() throws InvalidFormatException, IOException,org.apache.poi.openxml4j.exceptions.InvalidFormatException { 
   		Object[][] data= TestData.fetchData(property.getProperty("TestDataSheetPath"), "checkoutAndconfirmOrder");
   	return data;
   	}
    
    @Test (priority = 1, dataProvider="confirmOrder")
    public void Checkout_ConfirmOrder(String menu2, String sub_menu2 ,String Product , String fname ,String lname 
    		,String adress , String city , String postcode)
    				throws InterruptedException 
    { 
    	Boolean expected= true;
    	
    	// Go to MP3 Players Menu
        Menu_Products_obj = new Menu_Products(driver);
        	  String actual_2 =Menu_Products_obj.open_menu2(menu2,sub_menu2);
          	     Assert.assertTrue(actual_2.contains(menu2));
          	   Allure.step("Product selected from menu As Expected");
          	   
       // add product to shopping cart
               String product_price= homepage_obj.add_shopping_cart(Product,false, " "); 
                 String productprice 	= product_price.replace("$", "");
                	double productPrice_=  Double.parseDouble(productprice);
        		
            	     
             Boolean returned_msg_product2=homepage_obj.validate_msg_addtocart(Product);
        			assertEquals(returned_msg_product2, expected);
        		Allure.step("Product added to shopping Cart As Expected");   
        		
       		Boolean product2_shoppingCart=homepage_obj.check_product_addedinshoppingcart_table(Product,productPrice_);	
       		       assertEquals(product2_shoppingCart, expected);
       		 Allure.step("Product added successfully to shopping Table");
       		 
       	CheckoutPage_obj = new CheckoutPage(driver, property);
       	CheckoutPage_obj.Billing_details(fname, lname , adress , city , postcode);
       	
       
        CheckoutPage_obj.delivery_details(fname, lname, adress, city, postcode);
    	CheckoutPage_obj.delivery_method();
    	
        Double Actual_productPrice	= CheckoutPage_obj.check_tottalPrice();
	         assertEquals(Actual_productPrice, productPrice_);
	         Allure.step("Product Price the same as in shopping Cart");  
	         
	    Boolean flat_shipping_rate=CheckoutPage_obj.check_Flat_shipping_Rate(productPrice_);
	         assertEquals(flat_shipping_rate, expected);
    		 Allure.step("Total price includes the Flat shipping rate As Expected");
    		 
      	     
        Boolean returned_confirmation_msg=CheckoutPage_obj.validate_confirmation_msg();
        	assertEquals(returned_confirmation_msg, expected);
        	Allure.step("Order confirmed successfully"); 	 
    	
    }
    
    @AfterMethod
    public void log_out() throws InterruptedException {
    	RegisterPage_obj = new RegisterPage(driver, property);
    	RegisterPage_obj.log_Out();
    }

}
