package commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class MainBranch
{
	WebDriver driver;

	public MainBranch( WebDriver driver) ///deifnnin constuctor for accessing webdriver emthpds giving webdriver argu 
	//argument  pass as driver beacuese invoking webdrive methods within the class
	{
		this.driver= driver;

	}

	// below All are reprository.We defining locators and storing them in webelemnt

	@FindBy(xpath="//input[@id='BtnNewBR']")   
	WebElement  ClickNewBranch; //storing  welelement so that aceess webelemnt methods

	@FindBy(xpath="//input[@id='txtbName']")
	WebElement  EnterBranchname;

	@FindBy(xpath="//input[@id='txtAdd1']")
	WebElement  EnterAddress1;

	@FindBy(xpath="//input[@id='Txtadd2']")
	WebElement  EnterAddress2;

	@FindBy(xpath="//input[@id='txtadd3']")
	WebElement  EnterAddress3;

	@FindBy(xpath="//input[@id='txtArea']")
	WebElement EnterArea ;

	@FindBy(xpath="//input[@id='txtZip']")
	WebElement EnterZipcode ;

	@FindBy(xpath="//select[@id='lst_counrtyU']")
	WebElement SelectCountry;

	@FindBy(xpath="//select[@id='lst_stateI']")
	WebElement SelectState ;

	@FindBy(xpath="//select[@id='lst_cityI']")
	WebElement SelectCity ;

	@FindBy (xpath="//input[@id='btn_insert']")
	WebElement ClickSubmit;

	//to perform any action n above repso need to deinf mehthods

	public boolean Verify_NewBranch(String Branchname ,String Address1, String Address2 ,String Address3,String Area,String Zipcode, String Country,String State ,String City) throws Throwable  //define all  that 9 arg  bases on Excel 
	{
		this.ClickNewBranch.click(); //will click on new branch
		this.EnterBranchname.sendKeys(Branchname);
		this.EnterAddress1.sendKeys(Address1);
		this.EnterAddress2.sendKeys(Address2);
		this.EnterAddress3.sendKeys(Address3);
		this.EnterArea.sendKeys(Area);
		this.EnterZipcode.sendKeys(Zipcode);
		
		//Select ddripdown using select class(import it) and use its methods like visible tetx
		new Select(this.SelectCountry).selectByVisibleText(Country);
		new Select(this.SelectState).selectByVisibleText(State);
		new Select(this.SelectCity).selectByVisibleText(City);
		
		
		this.ClickSubmit.click();//clcik submit button
		
		
		// handle alerts after clicking submit button  capture alert message
		String ExpectedAlert=driver.switchTo().alert().getText();
		Thread.sleep(2000);
		driver.switchTo().alert().accept(); //click  ok button of laert poup
		
		String ActualAlert = "New Branch with id" ; //getting this alert message when not entering allfields
		
		
		//checkingboth expeted and actaul aleert message
		if(ExpectedAlert.toLowerCase().contains(ActualAlert.toLowerCase()) )
		{
			Reporter.log(ExpectedAlert,true);
			return true;
			
		}
		
		else
		{
			Reporter.log("Branch creation fail",true);
			return false;
		}
		


	}


}
