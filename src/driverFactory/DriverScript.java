package driverFactory;

import org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc.Role;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;
import commonPages.AdminLogin;
import commonPages.AdminLogout;
import commonPages.BranchUpdate;
import commonPages.Branchclick;
import commonPages.MainBranch;
import commonPages.RoleClick;
import commonPages.RolePage;
import commonPages.RoleUpdate;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil
{

	//pre and psot conditon required for running thos so exennd apputil
	//gloavla variable
	String inputpath="C:\\EClipse_Shraddha\\Eclipse Project\\Hybrid_Framework\\FileInput\\DataEngine.xlsx";  //stroign excel in inputpath varivale

	String outputpath= "C:\\EClipse_Shraddha\\Eclipse Project\\Hybrid_Framework\\FileOutput\\HybridResults.xlsx";	

	//give any file name in FIleputput folder as selnium automatciaaly create given file naame  after exceution under Fileoutput folder

	//Storing sheetfilename in global variable

	String TcCasesSheet ="TestCases";
	String TcStepsSheet = "TestSteps";

	//write tc in testng under @test

	@Test
	public void StartTest() throws Throwable
	{
		boolean res = false; //all the method in Page are boolean type so before eceution false after excet t/f
		String Tcres="";
		//Acces Excel methods
		ExcelFileUtil xl = new ExcelFileUtil(inputpath); 

		//Counting no of rowsn in Both Sheet 
		int TCcount= xl.rowcount(TcCasesSheet);  //row countin Testcase I sheet
		int TScount = xl.rowcount(TcStepsSheet);  ////row countin TestSteps II sheeet
		Reporter.log(TcCasesSheet+ " " +TcStepsSheet,true);

		//read all steps so loop required

		for(int i=1;i<=TCcount;i++)  //ate from 1 
		{

			//readin module status  col
			String ModuleStatus=xl.getCellData(TcCasesSheet, i, 2) ; //module variable conating Y/N
			//if Y theen yes
			if(ModuleStatus.equalsIgnoreCase("Y"))  //Module contain Y flag  will be pass afetr executin Ts steps
			{

				//read TCid col of 1 sheet
				String TCid = xl.getCellData(TcCasesSheet, i, 0) ;// read ID of 1 col of TC sheet
				for(int j=1;j<=TScount ;j++)   //J loop for 2 sheet
				{
					//read TSteps id of 2 sheet
					String TSid = xl.getCellData(TcStepsSheet, j, 0) ;

					// if both id of sheet matching then start reading step by step
					if(TCid.equalsIgnoreCase(TSid))
					{

						//storing keywork(3 col) of TCstep sheet in this below keword variable 
						String Keyword= xl.getCellData(TcStepsSheet, j, 3);  //readin kerowek form 3 col
						if(Keyword.equalsIgnoreCase("adminLogin"))  // we need to write here samae keyword jo ki excel mention ame
							//call Adminlogin page
						{
							AdminLogin login= PageFactory.initElements(driver, AdminLogin.class); //go with last object page.Here pago is class
							//callign all locators  and method of  Admin page and stroing in  this  abovelogin variable
							String parma1_user= xl.getCellData(TcStepsSheet, j,5 ) ; //5col (paramte 1) in excel
							String param2_pwd = xl.getCellData(TcStepsSheet, j, 6); //in 6 col mae par2 haa in excel

							// hereboth  username and paramter holding data we need to sent it login method

							res= login.verifylogin(parma1_user, param2_pwd) ;

						}

						else if(Keyword.equalsIgnoreCase("branchCreation"))
						{
							//same here call both class -branchclik and main branch .mehtods nad store in variabel so ta
							Branchclick branch= PageFactory.initElements(driver, Branchclick.class);

							MainBranch branchcreation =PageFactory.initElements(driver, MainBranch.class); //here fill all mansdorty feildl

							//total 9 Paramteres

							String paraBranchname =xl.getCellData(TcStepsSheet, j, 5);
							String paraAddress1 = xl.getCellData(TcStepsSheet, j, 6);
							String paraAddress2 = xl.getCellData(TcStepsSheet, j, 7);
							String paraAddress3 = xl.getCellData(TcStepsSheet, j, 8);
							String paraArea = xl.getCellData(TcStepsSheet, j, 9);
							String paraZipcode = xl.getCellData(TcStepsSheet, j, 10);
							String paraCountry = xl.getCellData(TcStepsSheet, j, 11);
							String paraState = xl.getCellData(TcStepsSheet, j, 12);
							String paraCity = xl.getCellData(TcStepsSheet, j, 13);


							branch.branches();  //method in Branchclick class
							res= branchcreation.Verify_NewBranch(paraBranchname, paraAddress1, paraAddress2, paraAddress3, paraArea, paraZipcode, paraCountry, paraState, paraCity);


						}

						else if(Keyword.equalsIgnoreCase("branchUpdation"))

						{
							Branchclick branch= PageFactory.initElements(driver, Branchclick.class);
							BranchUpdate updatebranch= PageFactory.initElements(driver, BranchUpdate.class);  //here we edit 4 Para

							String parabranchname=xl.getCellData(TcStepsSheet, j,5);
							String paraAddress1=xl.getCellData(TcStepsSheet, j, 6) ;
							String paraArea =xl.getCellData(TcStepsSheet, j, 9) ;
							String paraZipcode=xl.getCellData(TcStepsSheet, j, 10) ;

							branch.branches(); //click

							res=updatebranch.verify_BranchUpdate(parabranchname, paraAddress1, paraArea, paraZipcode);

						}

						else if(Keyword.equalsIgnoreCase("adminLogout"))
						{

							AdminLogout logout=PageFactory.initElements(driver ,AdminLogout.class);
							logout.Verify_LogOut();
							Thread.sleep(2000);

						}

						//here write ofr role creation
						// herer again login for role creations
						else if(Keyword.equalsIgnoreCase("roleCreation"))
						{

							RoleClick role= PageFactory.initElements(driver,RoleClick.class);
							RolePage rolecreation= PageFactory.initElements(driver, RolePage.class);

							String Rolename= xl.getCellData(TcStepsSheet, j, 5);
							String RoleDescription= xl.getCellData(TcStepsSheet, j, 6);
							String RoleType= xl.getCellData(TcStepsSheet, j, 7);

							role.Roles();  //clcik
							res = rolecreation.verify_role(Rolename, RoleDescription, RoleType);


						}
						

						//Here again login for role updation and logout

						else if(Keyword.equalsIgnoreCase("roleUpdation"))

						{
							RoleClick role= PageFactory.initElements(driver, RoleClick.class);
							RoleUpdate updaterole= PageFactory.initElements(driver, RoleUpdate.class);

							//passing 2 param only  for updation
							String Rolename=xl.getCellData(TcStepsSheet, j, 5);
							String Roledescription = xl.getCellData(TcStepsSheet, j, 6);

							role.Roles();
							updaterole.verify_RoleUpdate(Rolename, Roledescription);


						}




						///////  
						String Tsres="" ;//null		
						if(res)
						{

							//if res is true write Pass in test step sheet
							Tsres="Pass"; //in steps sheet II

							xl.setCelldata(TcStepsSheet, j, 4,Tsres,outputpath); //in 4 col write Pass of Test step sheet

						}

						else
						{
							//if res is fai write fail into test step sheet

							Tsres="Fail";  //fail
							xl.setCelldata(TcStepsSheet, j, 4, Tsres, outputpath);  //In tSres holding fial
						}

						//if not tCres equal to null then write pass/fail into TS sheet     
						if(!Tsres.equalsIgnoreCase("Fail"))  //Not operator work opp  true mean fail
						{
							//if TSres is pass then write fail in TC sheet

							Tcres= Tsres ; //assignment operaator as assign value pf Ts into Tc
							//here if TS fail then wriet same fail in tc sheet
						}

					}


				}  //After wiring all tstep then go to Tc sheet//close J loop of Tes step sheet as doyuble click here ebfore brac
				//  writing Pass.fail whcih was assign above in TCres in  Tcsheet
				xl.setCelldata(TcCasesSheet, i, 3, Tcres, outputpath); //where will wriye in Excel
			}

			else 
			{
				//write as Blocked in Status col which contain N flag n sheet
				xl.setCelldata(TcCasesSheet, i, 3, "Blocked", outputpath);
			}


		}

	}  //close entire loop

}

