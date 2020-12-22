package opencart.testcases;
import java.io.IOException;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.qameta.allure.Allure;
import opencart.Base.TestBase;
import opencart.pages.Homepage;
import opencart.pages.LoginPage;
import openchart.testdata.TestData;


public class LoginTest extends TestBase {
	
	Homepage homepage_obj;
    LoginPage LoginPage_obj;
 

	
	@Test
	public void inValid_Login() throws InterruptedException  {
		homepage_obj = new Homepage(driver,property);
		homepage_obj.open_Login();
		LoginPage_obj = new LoginPage(driver,property);
		LoginPage_obj.login("hadeer", "098");
		LoginPage_obj.verify_error_display();
		
	}
	
	@DataProvider(name = "validLogin")
	public Object[][] validLogin() throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException { 
		Object[][] data= TestData.fetchData(property.getProperty("TestDataSheetPath"), "validLogin");
	return data;
	}
	

	@Test (priority = 1, dataProvider="validLogin")
	public void valid_Login(String email, String password ) throws InterruptedException {
		homepage_obj = new Homepage(driver,property);
		homepage_obj.open_Login();
		LoginPage_obj = new LoginPage(driver,property);		
		LoginPage_obj.login(email, password);
		LoginPage_obj.my_account_isDisplayed();
		Allure.step("success login");
		
	}
	


}
