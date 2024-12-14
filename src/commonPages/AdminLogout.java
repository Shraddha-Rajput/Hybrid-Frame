package commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AdminLogout
{ 
	WebDriver driver ;
	public AdminLogout(WebDriver driver)  //defining constructor
	{
		this.driver = driver;

	}

	@FindBy(xpath="(//img)[4]")
	WebElement ClickLogOutBtn;

	@FindBy(xpath="//input[@id='login']")
	WebElement ClickLoginBtn;

	//verify Logout suucesfully or not using conition if Login button  button display it mena loout successfully


	public boolean Verify_LogOut() throws Throwable
	{

		this.ClickLogOutBtn.click();  //clock logot button 
		Thread.sleep(2000);
		
		if(this.ClickLoginBtn.isDisplayed())   //Login button display it mean logout successfully
		{

			Reporter.log(" Admin Logout Successfuly",true);
			return true;
		}

		else
		{

			Reporter.log("Admin LOgin Fail ,true");
			return false;

		}



	}

}
