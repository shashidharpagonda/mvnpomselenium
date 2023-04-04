package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. Private By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPassLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By accLogoutMessage =By.cssSelector("div#content h1");
	
	private By forgotPassword = By.xpath("//div[@class='list-group']/a[text()='Forgotten Password']");
	
	//2. public Page class Constructor
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);
	}
	
	//3. public page actions/methods
	@Step("getting login page title...")
	public String getLoginPageTitle()
	{
		String title= eleUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		System.out.println("Login page title is: "+title);
		return title;
	}

	@Step("getting login page url...")
	public String getLoginPageURL()
	{
		String url=eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
		System.out.println("Login page title is: "+url);
		return url;
	}
	
	@Step("checking forgot password link from Page class...")
	public boolean isForgotpasswordLinkExists()
	{
		return eleUtil.waitForElementVisible(forgotPassLink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}
	
	@Step("checking register password link from Page class...")
	public boolean isRegisterLinkExists()
	{
		return eleUtil.waitForElementVisible(registerLink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}
	
	@Step("login with username {0} and password {1} ")
	public AccountsPage doLogin(String username, String pwd)
	{
		System.out.println("UN: "+username+"| Pwd: "+pwd);
		eleUtil.waitForElementVisible(emailId, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
	}

	@Step("checking success message after logout...")
	public String getLogoutMessage()
	{
		String logoutMsg= eleUtil.waitForElementVisible(accLogoutMessage, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT)
				.getText();
		System.out.println("Logout successful Message:  "+logoutMsg);
		return logoutMsg;
	}
	
	@Step("navigating to registration page...")
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	// Delete this
	public ForgotPasswordPage doForgotPwd()
	{
		driver.findElement(forgotPassword).click();
		return new ForgotPasswordPage(driver);
	}
	
}
