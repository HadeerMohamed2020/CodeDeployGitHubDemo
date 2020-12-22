
package TestPackage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import objectModel.Google.googlehome;
import objectModel.Selenium.Documentation;
import objectModel.Selenium.seleniumhome;
import objectModel.Google.searchResults;



public class TestClass1 {

	//variables
	WebDriver browserobj;
	// page object
googlehome homepagegoogleobject;
searchResults serachresultobject;
seleniumhome homeseleniumobject;
Documentation documentaionobject;

@BeforeClass 

	 public void setup()
	{
		 System.setProperty("webdriver.chrome.driver","C:\\Users\\habdelbasset\\Downloads\\chromedriver_win32\\chromedriver.exe");
		  browserobj = new ChromeDriver();
		  
	}
	
@Test
	  public void assertgooglepagetitle() {
		  homepagegoogleobject = new googlehome(browserobj);
		  homepagegoogleobject.navigate();
		  homepagegoogleobject.assertpagetitledisplaye();
	  }

@Test(dependsOnMethods = { "assertgooglepagetitle" })	

public void assertfirstresult() {
	homepagegoogleobject = new googlehome(browserobj);
	homepagegoogleobject.searchforquery("Selenium");
	
	serachresultobject= new searchResults(browserobj);
	serachresultobject.Assertfirstelementtext("Selenium - Web Browser Automation");
}
	  
@Test(dependsOnMethods = { "assertfirstresult" }) 
public void Assertdocumentandimagedisplayed() {
	homeseleniumobject = new seleniumhome(browserobj);
	homeseleniumobject.navigate();
	
	documentaionobject = new Documentation (browserobj);
	documentaionobject.opendocument();
	documentaionobject.assertbackgrounddisplay();  
}

@Test (dependsOnMethods = { "Assertdocumentandimagedisplayed" }) 
public void AssertGoogleImage () {
	
	homepagegoogleobject = new googlehome(browserobj);
	homepagegoogleobject.navigate();
	homepagegoogleobject.googleimageassert();
}

@AfterClass 
	  
	  public void quitebrowser() 
	  {
		  browserobj.quit();
		  
	  }

}

