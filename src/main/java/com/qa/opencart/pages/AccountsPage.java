package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	//private CommonsPage commPage;

	private By header = By.xpath("//div/h2[text()='My Account']");
	private By accountSectionsHeader = By.xpath("//div/h2");  // CSS "div#content h2"
	private By logout = By.linkText("Logout");
	private By search = By.tagName("search");
	
	public AccountsPage (WebDriver driver)
	{
		this.driver=driver;
		eleUtil= new ElementUtil(this.driver);
		//commPage = new CommonsPage(this.driver);
	}
	
	public String getAccPageTitle()
	{
		return eleUtil.waitForTitleContains(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	public String getAccPageURL()
	{
		String accountUrl= eleUtil.waitForUrlContains(Constants.ACCOUNTS_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
		System.out.println("Account URL:  "+accountUrl);
		return accountUrl;
	}
	
	public String getAccPageHeader()
	{
		return eleUtil.waitForElementVisible(header, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).getText();
	}
	
	public List<String> getAccountsSectionsList()
	{
		List<WebElement> accSecList = 
				eleUtil.waitForElementsVisible(accountSectionsHeader, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT);
		List<String> accSecValList = new ArrayList<String>();
		
		for(WebElement sec : accSecList)
		{
			String text =sec.getText();
			accSecValList.add(text);
		}
		
		return accSecValList;
	}
	
	public boolean isLogoutLinkExist()
	{
		return eleUtil.waitForElementVisible(logout, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}
	
	public LoginPage clickOnLogout()
	{
		if(isLogoutLinkExist())
		{
			eleUtil.doClick(logout);
			return new LoginPage(driver);
		}
		return null;
	}
	
	public boolean isSearchExist()
	{
		return eleUtil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}
}
