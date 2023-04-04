package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.ForgotPasswordPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	
	DriverFactory df;
	public WebDriver driver;
	protected Properties prop;
	
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected ForgotPasswordPage forgotPwdPage;
	protected CommonsPage commonsPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage regPage;
	
	protected SoftAssert softAssert;

	@BeforeTest
	public void setUp()
	{
		df= new DriverFactory();	
		// Here driver is the reference. In DriverFactory, we are creating and Object which has again 1 more reference.
		// Hence 1 object has 2 references here.
		
		prop= df.init_prop();
		driver=df.init_driver(prop);  // We are getting ThreadLocal driver here.
		loginPage= new LoginPage(driver);
		softAssert= new SoftAssert();
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
}
