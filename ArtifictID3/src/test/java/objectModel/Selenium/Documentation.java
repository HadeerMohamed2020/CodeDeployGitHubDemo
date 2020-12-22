package objectModel.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Documentation {

	 WebDriver browserobj;
	 
	  By document = By.xpath("//ul[@id='sitemap']/li/a[@href='/docs/']");
	  By Backgroundpicture = By.xpath("//div[@id='header']//a[@href='/']");
	  
	public Documentation(WebDriver browserobj) {
		// TODO Auto-generated constructor stub
		
		this.browserobj=browserobj;
	}
	
	public void opendocument() {
		browserobj.findElement(document).click(); 
		
	}
	
	public void  assertbackgrounddisplay() {
		
		String getCssPicture =browserobj.findElement(Backgroundpicture).getCssValue("background");
		 Boolean isbackgrundimagedisplaye =getCssPicture.contains("images/header-logo.png");
		Assert.assertTrue(isbackgrundimagedisplaye);
	}

}
