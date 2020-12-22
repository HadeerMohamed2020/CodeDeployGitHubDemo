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
import opencart.pages.Menu_Products;
import opencart.pages.Homepage;
import opencart.pages.LoginPage;
import opencart.pages.RegisterPage;
import openchart.testdata.TestData;


public class ChangeCurrencyTest extends TestBase{
	
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
    
    
    @DataProvider(name = "checkcurrency")
	public Object[][] checkcurrency() throws InvalidFormatException, IOException,
	org.apache.poi.openxml4j.exceptions.InvalidFormatException { 
		Object[][] data= TestData.fetchData(property.getProperty("TestDataSheetPath"), "checkcurrency");
	return data;
	}
    
    @Test (priority = 1, dataProvider="checkcurrency")
    public void change_currency(String currency , String menu, String sub_menu)  {
    	Boolean expected= true;
    			
    	Menu_Products_obj = new Menu_Products(driver);
    	String actual=Menu_Products_obj.open_menu2(menu,sub_menu);
    	Assert.assertTrue(actual.contains(menu));
    	Allure.step("Product selected from Menu as expected ");
    	
    	// Check default currency
    	Boolean actual_dollar_currecy= homepage_obj.check_currency("$"); 
		assertEquals(actual_dollar_currecy, expected);
		Allure.step("Default currency is $");
 	
	
     	homepage_obj.change_curr(currency);	

		// Check currency after change currency
		 Boolean actual_euro_currecy= homepage_obj.check_currency(currency);
		 assertEquals(actual_euro_currecy, expected);
		 Allure.step("Currency changed As Expected");
		 
    }
    
    	
    @AfterMethod
    public void log_out() throws InterruptedException {
    	RegisterPage_obj = new RegisterPage(driver, property);
    	RegisterPage_obj.log_Out();
    }

}
