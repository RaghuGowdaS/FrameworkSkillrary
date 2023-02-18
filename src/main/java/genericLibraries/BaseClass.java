package genericLibraries;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import pomPages.ContactUsPage;
import pomPages.CoreJavaPage;
import pomPages.CoreJavaVideoPage;
import pomPages.Homepage;
import pomPages.SeleniumTrainingPage;
import pomPages.SkillRaryDemoAppPage;
import pomPages.TestingPage;

public class BaseClass {
		
	protected PropertiesFileUtility property;
	protected ExcelUtility excel;
	protected WebDriverUtility web;
	protected WebDriver driver;
	protected Homepage home;
	protected SkillRaryDemoAppPage skillraryDemo;
	protected SeleniumTrainingPage selenium;
	protected TestingPage testing;
	protected CoreJavaPage coreJava;
	protected CoreJavaVideoPage javaVideo;
	protected ContactUsPage contact;
	protected long time;
	
	
	
	//@BeforeSuite
	//@BeforeTest
	@BeforeClass
	public void classConfiguration() {
		property = new PropertiesFileUtility();
		excel = new ExcelUtility();
		web = new WebDriverUtility();
		
		property.propertyFileInitialization(IConstantPath.PROPERTY_FILE_PATH);
		excel.excelInitialization(IConstantPath.EXCEL_FILE_PATH);
	}
	
	@BeforeMethod
	public void methodConfiguration() {
		time = Long.parseLong(property.fetchProperty("timeouts"));
		driver = web.openApplication(property.fetchProperty("browser"), property.fetchProperty("url"), time);
		
		home = new Homepage(driver);
		Assert.assertTrue(home.getLogo().isDisplayed());
		skillraryDemo = new SkillRaryDemoAppPage(driver);
		selenium = new SeleniumTrainingPage(driver);
		testing = new TestingPage(driver);
		coreJava = new CoreJavaPage(driver);
		javaVideo = new CoreJavaVideoPage(driver);
		contact = new ContactUsPage(driver);
	}
	
	@AfterMethod
	public void methodTearDown()
    {
	   web.quitBrowser();
    }
	@AfterClass
	public void classTearDown()
    {
	   excel.closeExcel();
    }
	//@AfterTest
	//@AfterSuite


	}
