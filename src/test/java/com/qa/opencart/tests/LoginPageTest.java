package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC-100: Design login page for open cart application")
@Story("US-101: design login page feature ")

public class LoginPageTest extends BaseTest {

	@Description(" Verify login page title test")
	@Severity(SeverityLevel.NORMAL)   
	@Test (priority = 1)
	public void loginPageTitleTest()
	{
		String actualTitle =loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	// This test is to check how Failed tests show in Allure reports
	@Description(" Verify login page header test")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority = 6)
	public void loginPageHeaderTest()
	{
		String actualTitle ="Open Cart";
		Assert.assertEquals(actualTitle, "Open Cart11");  // To Fail  give Open Cart11
	}
	
	@Description(" Verify login page URL test")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority = 2)
	public void loginPageURLTest()
	{
		String actualUrl= loginPage.getLoginPageURL();
		Assert.assertTrue(actualUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION	));
	}
	
	@Description(" Verify forgot password link test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkExistsTest()
	{
		Assert.assertTrue(loginPage.isForgotpasswordLinkExists());
	}
	
	@Description(" Verify register link test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void registerLinkExistsTest()
	{
		Assert.assertTrue(loginPage.isRegisterLinkExists());
	}
	
	@Description(" Verify login test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void loginTest()
	{
		AccountsPage accPage =loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		String accPageTitle=accPage.getAccPageTitle();
		Assert.assertEquals(accPageTitle, Constants.ACCOUNTS_PAGE_TITLE);
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
}
