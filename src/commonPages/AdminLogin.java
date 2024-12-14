package commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AdminLogin 
{
	WebDriver driver;

	//creating const for accesing webdrive rmethods
	public AdminLogin(WebDriver driver) // here we need to pass two arg as usernma and pass for login
	{

		this.driver= driver;
	}


	//define repsoitory as Page object
	@FindBy(xpath="//input[@id='txtuId']")
	WebElement Username;  //store webelemnt so that acces all methods

	@FindBy(xpath="//input[@id='txtPword']")  //attrubiyenmae="value"
	WebElement Password;

	@FindBy(xpath="//input[@id='login']")
	WebElement loginbutton;
	
//method for login
	public boolean verifylogin(String username,String password)
	{
		this.Username.sendKeys(username); //if will use not use cosntirnctor then will not get .senekys

		this.Password.sendKeys(password);
		this.loginbutton.click();

		String expected ="adminflow";
		String actual=driver.getCurrentUrl();

		if(actual.toLowerCase().contains(expected.toLowerCase()))
		{

			Reporter.log("Login Succesffuly" +expected+""+actual,true);	

			return true;
		}

		else
		{
			Reporter.log("Login Fail" +expected+" " +actual,true);	

			return false;

		}

	}

}



