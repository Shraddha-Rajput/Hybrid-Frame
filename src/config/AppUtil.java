package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class AppUtil
{
	public static Properties prt;
	public static WebDriver driver;

	@BeforeSuite  //eceuste after each and  every test caeses
	public static void setUp() throws Throwable 
	{

		prt= new Properties();
		prt.load(new FileInputStream("./PropertyFile/Environment.properties")); //give here proeprties file loacation

		//we access skey value form proeprty file using get proeprty.so from proeprty acess

		if(prt.getProperty("Browser").equalsIgnoreCase("chrome")) //browser key and chrome is value
		{
			
			driver= new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(prt.getProperty("Url")); //from where so proeprty
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


		}


		else if(prt.getProperty("Browser").equalsIgnoreCase("Firebox"))
		{


			driver= new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(prt.getProperty("Url")); //from where so proeprty
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		}


		else  
		{


			Reporter.log("Browser value is not maching",true);  //if both firbox and chrome not matching print this
		}

	}

	
	@AfterSuite
	public static void tearDown()
	{

		driver.close();
	}


}







