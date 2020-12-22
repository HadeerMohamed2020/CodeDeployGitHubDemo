package opencart.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import opencart.Base.TestBase;
import opencart.pages.Homepage;
import opencart.pages.LoginPage;
import opencart.pages.Menu_Products;
import opencart.pages.RegisterPage;
import openchart.testdata.TestData;

public class SearchSubcategoryTest extends TestBase{

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
    
    @DataProvider(name = "searchsubCat")
   	public Object[][] checkcurrency() throws InvalidFormatException, 
   	IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException { 
   		Object[][] data= TestData.fetchData(property.getProperty("TestDataSheetPath"), "searchsubCat");
   	return data;
   	}
    
  //Search Subcategory test case
    @Test (priority = 1, dataProvider="searchsubCat")
    
     public void search_Subcategory(String product) {
   	 
    	 boolean expected =true;  
    	 
   	 boolean x =homepage_obj.search_sub_category(product);
   	assertEquals(x, expected);
                  }
   
    
   @AfterMethod
   public void log_out() throws InterruptedException {
   	RegisterPage_obj = new RegisterPage(driver, property);
   	RegisterPage_obj.log_Out();
   }
}
