package commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;


public class RoleUpdate 
{
	WebDriver driver;
	public RoleUpdate(WebDriver driver)   //defing cons
	{
		this.driver= driver;

	}


	//updating only rolename and role desciptn (2 parmaaters pas)  doubt cant update ? Role Type C?E?
	@FindBy(xpath="//img[@src='images/b-edit.png']")
	WebElement ClickEditbutton;      //clcik edit button


	@FindBy(xpath="//input[@id='txtrNameU']")   //updating rolename
	WebElement EnterRolename;

	@FindBy(xpath="//input[@id='txtrdescU']")   // updating role desc
	WebElement EnterRoleDesciption;

	@FindBy(xpath="//input[@id='btnupdate']")   //click update button
	WebElement ClikcUpdatebutton;


	public boolean verify_RoleUpdate(String Rolename,String RoleDescription) throws Throwable 
	{
		this.ClickEditbutton.click();

		this.EnterRolename.sendKeys(Rolename);
		this.EnterRoleDesciption.sendKeys(RoleDescription);
		this.ClikcUpdatebutton.click();

//compaing alert text box
		String actualAlert=driver.switchTo().alert().getText();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();


//		String actualAlert = "Role updated ";
		
		String expectedAlert = "Role updated Sucessfully";
		if(actualAlert.toLowerCase().contains(expectedAlert.toLowerCase()))

		{
			Reporter.log(actualAlert,true);
			return true;

		}

		else
		{

			Reporter.log("Role updated FAil",true);
			return false;
		}



	}



}
