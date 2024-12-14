package commonPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RoleClick 
{ 
	
	@FindBy(xpath="//img[@src='images/Roles_but.jpg']")
	WebElement ClickRoles;
	
	public void Roles() // not returning anu value just clcikin
	{
	
	 ClickRoles.click();
	
	}

}
