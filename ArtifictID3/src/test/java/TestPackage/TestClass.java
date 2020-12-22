package TestPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestClass {
	WebDriver browserobj;
	
@BeforeClass 

 public void setup()
{
	 System.setProperty("webdriver.chrome.driver","C:\\Users\\habdelbasset\\Downloads\\chromedriver_win32\\chromedriver.exe");
	  browserobj = new ChromeDriver();
}

@Test

  public void openGoogleSearch() {
	  
	  browserobj.navigate().to("https://www.google.com/ncr");
	  String actualtitle= browserobj.getTitle();
	  Assert.assertEquals(actualtitle, "Google");
	  
}

@Test(dependsOnMethods = { "openGoogleSearch" })

public void assertfirstresult() {
	  WebElement Search =browserobj.findElement(By.xpath("//input[@name='q']"));
	  Search.sendKeys("Selenium");
	  Search.sendKeys(Keys.ENTER);
	  
	  WebElement Text=browserobj.findElement(By.xpath("(//h2[text()='Web results']/following-sibling::div//h3)[1]"));
	 String Actualresult = Text.getText();
	 Assert.assertEquals(Actualresult, "Selenium - Web Browser Automation");	
	 
	 
}

@Test(dependsOnMethods = { "assertfirstresult" }) 
public void Assertdocumentandimagedisplayed() {
	// open documentation from selenium URL
	
	 browserobj.navigate().to("https://www.seleniumhq.org/"); 
	 WebElement document =browserobj.findElement(By.xpath("//ul[@id='sitemap']/li/a[@href='/docs/']"));
	document.click();
	
	 
	// assert for image displayed in header
	WebElement picture =browserobj.findElement(By.xpath("//div[@id='header']//a[@href='/']"));
	String getCssPicture =picture.getCssValue("background");
	boolean isbaackgrndimagedisplayed = getCssPicture.contains("images/header-logo.png");
     Assert.assertTrue(isbaackgrndimagedisplayed);
	  
    
  }
  
  @AfterClass 
  
  public void quitebrowser() 
  {
	  browserobj.quit();
	  
  }
  
}
