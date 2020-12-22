package objectModel.Google;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class googlehome {

	WebDriver browserobj;
	String url ="https://www.google.com/ncr" ;
	String title="Google";
	
	 By searchbox =By.xpath("//input[@name='q']") ;
	 By Googleimage = By.xpath("//img[@id='hplogo']"); 
	 
	public googlehome(WebDriver browserobj) {
		// TODO Auto-generated constructor stub
		this.browserobj =browserobj;
	}

	public void navigate()
	{
		browserobj.navigate().to(url);
	}
	
	public void assertpagetitledisplaye() 
	{
		  Assert.assertEquals(browserobj.getTitle(), title);
	}
	
	public void searchforquery(String query) {
		
		browserobj.findElement(searchbox).sendKeys(query);
		browserobj.findElement(searchbox).sendKeys(Keys.ENTER);
	}
	
	public void googleimageassert() 
	{
	String x = browserobj.findElement(Googleimage).getAttribute("src");
	Boolean googleimagedisplayed = x.contains("2x/googlelogo_color_272x92dp.png");
		Assert.assertTrue(googleimagedisplayed);
	}
}
