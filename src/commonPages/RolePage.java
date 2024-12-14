package commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class RolePage
{

	WebDriver driver;

	public RolePage(WebDriver driver)  //constucotr fro involi

	{

		this.driver= driver;

	}

	//ROle name.role despc ,role tye as E/C from dropwdown 
	@FindBy(xpath="//input[@id='btnRoles']")
	WebElement CLickNewRole;

	@FindBy(xpath="//input[@id='txtRname']")     //rolename
	WebElement EnterRolename;

	@FindBy(xpath="//input[@id='txtRDesc']")   //reoledesctipons
	WebElement EnterRoleDescription;


	@FindBy(xpath="//select[@id='lstRtypeN']")      //select from  dropdpnw
	WebElement SelectRoleType;

	@FindBy(xpath="//input[@id='btninsert']")      //submit button
	WebElement ClickSubmit;

	//wnat to perfrom any action need to wriet metods

	public boolean verify_role(String roleName,String roleDesc, String roleType ) throws Throwable  // 3 arg will give bases on Excel parmaaters
	{

		this.CLickNewRole.click();  //first will click on role
		this.EnterRolename.sendKeys(roleName);  
		this.EnterRoleDescription.sendKeys(roleDesc);

		new Select(this.SelectRoleType).selectByVisibleText(roleType);   //import ui one not generic
		this.ClickSubmit.click();
		Thread.sleep(3000);

		//handle Alert
		String actualAlert = driver.switchTo().alert().getText();
		Thread.sleep(5000);
		driver.switchTo().alert().accept();

		//compare both actual and ecpetced alerts
		String expectedAlert="New Role with id" ;
		if(actualAlert.toLowerCase().contains(expectedAlert.toLowerCase()))  //cpverting both to tlowercase

		{

			Reporter.log(actualAlert,true); //printing value
			return true;

		}


		else
		{
			Reporter.log("Role creation fail",true);
			return false;

		}

	}


}
