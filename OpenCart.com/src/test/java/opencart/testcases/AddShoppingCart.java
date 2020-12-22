package opencart.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.qameta.allure.Allure;
import opencart.Base.TestBase;
import opencart.pages.Homepage;
import opencart.pages.LoginPage;
import opencart.pages.Menu_Products;
import opencart.pages.RegisterPage;
import openchart.testdata.TestData;

public class AddShoppingCart  extends TestBase {

	Homepage homepage_obj;
    LoginPage LoginPage_obj;
    RegisterPage RegisterPage_obj;
    Menu_Products Menu_Products_obj;
    
    @BeforeMethod
    public void valid_Login() throws InterruptedException{
    	homepage_obj = new Homepage(driver,property);
		homepage_obj.open_Login();
		LoginPage_obj = new LoginPage(driver,property);		
		LoginPage_obj.login(property.getProperty("valid-email"), property.getProperty("valid-password"));	
	}
    
    
    @DataProvider(name = "testShoppingCart")
   	public Object[][] checkcurrency() throws InvalidFormatException, IOException,org.apache.poi.openxml4j.exceptions.InvalidFormatException { 
   		Object[][] data= TestData.fetchData(property.getProperty("TestDataSheetPath"), "addShoppingCart");
   	return data;
   	}
    
    @Test (priority = 1, dataProvider="testShoppingCart")
    public void Validate_Shopping_Cart(String menu1 , String menu2 , String sub_menu2,String product1, String product2) throws InterruptedException 
    {
    	Boolean expected= true;
                 ////////////add Tablet steps///////////////

  
    	// Go to Tablets
    	  Menu_Products_obj = new Menu_Products(driver);
    	       String actual =Menu_Products_obj.open_menu1(menu1);
      	           Assert.assertTrue(actual.contains(menu1)); 		           
      	         Allure.step("Product selected from menu As Expected ");
      	     
      	// add Tablet to shopping cart
         	String product_price1 = homepage_obj.add_shopping_cart(product1,false," "); 
        	String productprice_1 	= product_price1.replace("$", "");
         	double productPrice1=  Double.parseDouble(productprice_1);
     		
     		Boolean returned_msg_product1=homepage_obj.validate_msg_addtocart(product1);
     				assertEquals(returned_msg_product1, expected);		
     			Allure.step("Product added to shopping Cart As Expected");
     				
     		Boolean product1_shoppingCart=homepage_obj.check_product_addedinshoppingcart_table(product1 , productPrice1);	
     		        assertEquals(product1_shoppingCart, expected);  		        
     		   	Allure.step("Product added successfully to shopping Table");
     		   	
     				//////////// add laptop steps///////////////
     		        
    	// Go to Laptops & Notebooks
          Menu_Products_obj = new Menu_Products(driver);
          	  String actual_2 =Menu_Products_obj.open_menu2(menu2,sub_menu2);
            	     Assert.assertTrue(actual_2.contains(menu2));
            	   Allure.step("Product selected from menu As Expected ");
            	   
       // add laptop to shopping cart
             String product_price2= homepage_obj.add_shopping_cart(product2,true , "2011-4-8"); 
               String productprice_2 	= product_price2.replace("$", "");
              	double productPrice2=  Double.parseDouble(productprice_2);
      		
          	     
           Boolean returned_msg_product2=homepage_obj.validate_msg_addtocart(product2);
      			assertEquals(returned_msg_product2, expected);
      		Allure.step("Product added to shopping Cart As Expected");   
      		
     		Boolean product2_shoppingCart=homepage_obj.check_product_addedinshoppingcart_table(product2,productPrice2);	
     		       assertEquals(product2_shoppingCart, expected);
     		 Allure.step("Product added successfully to shopping Table");
                   
     		Boolean Actual_tottal_price = homepage_obj.validate_tottal_price(productPrice1,productPrice2);
     		       assertEquals(Actual_tottal_price, expected);
     		 Allure.step("Tottal Price of products return with right Value");      
    }
    
    	
    @AfterMethod
    public void log_out() throws InterruptedException {
    	RegisterPage_obj = new RegisterPage(driver, property);
    	RegisterPage_obj.log_Out();
    }
    
}
