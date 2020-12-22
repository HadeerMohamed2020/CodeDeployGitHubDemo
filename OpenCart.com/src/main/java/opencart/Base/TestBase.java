package opencart.Base;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties property;
	FileInputStream fs;
	
	@BeforeSuite
	public void startdriver() throws IOException {
		 System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		 driver= new ChromeDriver();
		 
		 fs = new FileInputStream (System.getProperty("user.dir")+"//config.properties");
		 property =new Properties();
		 property.load(fs);
		 
		 driver.navigate().to(property.getProperty("URL"));
		driver.manage().window().maximize();
	}
	
	
	 @AfterSuite
	public void quitedriver() {
		driver.quit();
	}

}
