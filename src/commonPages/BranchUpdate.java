package commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class BranchUpdate 
{
	//edit 
	WebDriver driver;
	public BranchUpdate( WebDriver driver)  //Argument  pass as driver beacuese we are invoking webdrive methods within the class

	{

		this.driver= driver;
	}

//updating 4fields  brnachname,adress1,area,zocode,
	@FindBy(xpath="//tbody/tr[4]/td[7]/a[1]/img[1]")
	WebElement ClickEditbutton;

	@FindBy(xpath="//input[@id='txtbnameU']")
	WebElement EnterBranchName ;

	@FindBy(id="txtadd1u")
	WebElement EnterAddress1;

	@FindBy(xpath="//input[@id='txtareaU']")
	WebElement EnterArea ;

	@FindBy(xpath="//input[@id='txtzipu']")
	WebElement EnterZipcode ;

	@FindBy(xpath="//input[@id='btnupdate']")
	WebElement ClickUpdatebutton ;

	public boolean verify_BranchUpdate(String BranchName, String Address1 , String Area,String Zipcode) throws Throwable
	{

		this.ClickEditbutton.click();

		this.EnterBranchName.clear();  //clear previpus textfield data before wriritng ne wone
		this.EnterBranchName.sendKeys(BranchName);
		
		this.EnterAddress1.clear();
		this.EnterBranchName.sendKeys(Address1);
		
		this.EnterArea.clear();
		this.EnterArea.sendKeys(Area);
		
		this.EnterZipcode.clear();
		this.EnterZipcode.sendKeys(Zipcode);
		this.ClickUpdatebutton.click();
		
		//After lcocking updtae button will get one pop up will abdel it using slert
		String ExpectedAlert= driver.switchTo().alert().getText();
		Thread.sleep(5000);
		driver.switchTo().alert().accept();  //accepting by clicking ok button of alert box
		
		//now storing some part of alert mesage into variable for copamrssions
		String ActualAlert ="Branch updated" ; //here type same message as displayed
		
		
		if(ExpectedAlert.toLowerCase().contains(ActualAlert.toLowerCase()))  //converting all message run time in lower case
		{
			Reporter.log(ExpectedAlert,true);  // display expected alert text so not puttin in ""
			return true;
		}
		
		else
		{
	      Reporter.log("Branch updation fail",true);
	      return false;
		}
		



	}



}
