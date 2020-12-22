package objectModel.Google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class searchResults {
	WebDriver browserobj;
	
	
	 By firstResultdisplay = By.xpath("(//h2[text()='Web results']/following-sibling::div//h3)[1]");
	 
	public searchResults(WebDriver browserobj) {
		// TODO Auto-generated constructor stub
		this.browserobj = browserobj;
	}

	public void Assertfirstelementtext(String Text) {
		
			 String Actualresult = browserobj.findElement(firstResultdisplay).getText();
			 Assert.assertEquals(Actualresult, Text);	
	
	}
	
	 
}
