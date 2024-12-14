package commonPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Branchclick

{

	@FindBy(xpath="//a[@href='admin_banker_master.aspx']//img")
	WebElement Clickbranch;   //here we storing so that w ecan acces all webdrive rmethods 
	public void branches()
	{
		
		Clickbranch.click(); // now no need to give driver.find elemnt as directly use
	}
	
}
