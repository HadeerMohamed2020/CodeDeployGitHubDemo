package opencart.testcases;


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


public class CheckBreadCrumb extends TestBase{
	
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
	    
	 
	 @DataProvider(name = "leftsidmenu")
		public Object[][] checkcurrency() throws InvalidFormatException,
		IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException { 
			Object[][] data= TestData.fetchData(property.getProperty("TestDataSheetPath"), "leftsidmenu");
		return data;
		}
	    
	    
	// Check on bread crumb & Left side menu  test case
  @Test (priority = 1, dataProvider="leftsidmenu")
  public void left_Side_menu(String product) {
	 
	  
	  Menu_Products_obj = new Menu_Products(driver);
	  String actual =Menu_Products_obj.open_menu1(product);
  	     Assert.assertTrue(actual.contains(product));
  	   Allure.step("Product selected from Menu As Expected");
  	
	 String actual_ = homepage_obj.check_bread_crumb(product);
	 Assert.assertTrue(actual_.contains(product));
	 Allure.step("Product is displayed in bread_crumb As Expected");
	 
 	// validate Tablets is active in left side menu 
  	 String active_Actual_product= homepage_obj.check_active_product();  
  	 Assert.assertTrue(active_Actual_product.contains(product));
  	Allure.step("Product is active in left side menu As Expected");
  	
  }
	 
	  @AfterMethod
	    public void log_out() throws InterruptedException {
	    	RegisterPage_obj = new RegisterPage(driver, property);
	    	RegisterPage_obj.log_Out();
	    }
}
