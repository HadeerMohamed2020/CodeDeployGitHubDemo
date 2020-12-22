package opencart.testcases;
import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import opencart.Base.TestBase;
import opencart.pages.Homepage;
import opencart.pages.RegisterPage;
import openchart.testdata.TestData;

public class RegisterTest extends TestBase {
	
	Homepage homepage_obj;
	RegisterPage RegisterPage_obj;
	
	
	
	@Test
	public void register_With_Erros() throws InterruptedException, IOException {
		homepage_obj = new Homepage(driver,property);
		homepage_obj.open_Register();
		RegisterPage_obj = new RegisterPage(driver,property);
		RegisterPage_obj.register("test", "", "", "", "", "", "", "123", "123");
		RegisterPage_obj.verify_Register_with_Errors();
	}
	
	
	@DataProvider(name = "validregister")
	public Object[][] valid_register() throws InvalidFormatException, 
	IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{ 
		Object[][] data= TestData.fetchData(property.getProperty("TestDataSheetPath"), "validregister");
	return data;
	}
	
	@Test(priority = 1, dataProvider="validregister")
	public void register_Without_Erros(String first_Name , String last_Name ,
			String email , String telephone , String address1 , String city ,
			String Postcode , String Password , String Confirm_Password) throws InterruptedException 
	{
		homepage_obj = new Homepage(driver,property);
		homepage_obj.open_Register();
		RegisterPage_obj = new RegisterPage(driver, property);
		RegisterPage_obj.register(first_Name, last_Name, email, telephone,
				address1, city, Postcode, Password, Confirm_Password);
	    RegisterPage_obj.verify_AccountCreated_msg();
		RegisterPage_obj.log_Out();
		RegisterPage_obj.verify_LogOut();
		
	}
	
	

}
