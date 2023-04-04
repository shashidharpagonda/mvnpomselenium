package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class ForgotPasswordPageTest extends BaseTest{
	
	@BeforeClass
	public void doforgotPwd()
	{
		forgotPwdPage = loginPage.doForgotPwd();
	}
	
	@Test
	public void forgotPageTitleTest()
	{
		String actualTitle= forgotPwdPage.getForgotPwdPageTitle();
		Assert.assertEquals(actualTitle, Constants.FORGOT_PAGE_TITLE);
	}

}
