package objectModel.Selenium;

import org.openqa.selenium.WebDriver;

public class seleniumhome {
	
	WebDriver browserobj;
	String url ="https://www.seleniumhq.org/";
	
	public seleniumhome(WebDriver browserobj) {
		// TODO Auto-generated constructor stub
		
		this.browserobj = browserobj;
	}

	
	public void navigate()
	{
		browserobj.navigate().to(url);
	}
	
	
	
}
