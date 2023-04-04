package com.qa.opencart.tests;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetUp()
	{
		//accPage= loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		accPage= new AccountsPage(driver);
	}
	
	@Test(enabled = false)
	public void accPageTitleTest()
	{
		String title = accPage.getAccPageTitle();
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test(enabled = false)
	public void accPageHeaderTest()
	{
		String accPageHeader =accPage.getAccPageHeader();
		System.out.println("accPageHeader: "+accPageHeader);
		Assert.assertEquals(accPageHeader, Constants.ACCOUNTS_PAGE_HEADER);
	}
	
	@Test(enabled = false)
	public void accPageSectionsHeaderTest()
	{
		List<String> actualAccSecList= accPage.getAccountsSectionsList();
		System.out.println("Actual Accounts Section Header: "+ actualAccSecList);
		System.out.println("Expected Accounts Section Header: "+ Constants.ACCOUNTS_PAGE_SECTIONS_HEADER_LIST);
		Collections.sort(actualAccSecList);
		Collections.sort(Constants.ACCOUNTS_PAGE_SECTIONS_HEADER_LIST);
		Assert.assertEquals(actualAccSecList, Constants.ACCOUNTS_PAGE_SECTIONS_HEADER_LIST);
	}
	
	@Test(enabled = false)
	public void isUserLoggedOutTest()
	{
		accPage.clickOnLogout();
		Assert.assertEquals(loginPage.getLogoutMessage(), Constants.USER_LOGOUT_MESSAGE);
	}
	
	@DataProvider
	public Object[][] getProductName()
	{
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Samsung"},
			{"Apple"}
		};
	}
	
	@Test(dataProvider="getProductName")
	public void searchTest(String productName)
	{
		commonsPage = new CommonsPage(driver);
		searchResultsPage= commonsPage.doSearch(productName);
		String resultsPageHeader=searchResultsPage.getResultsPageHeader();
		Assert.assertTrue(resultsPageHeader.contains(productName));
	}
	
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"iMac","iMac"},
			{"Samsung","Samsung SyncMaster 941BW"},
		};
	}
	
	//4 level page chaining
	@Test(dataProvider="getProductData")
	public void productInfoTest(String productName, String mainProductName)
	{
		commonsPage = new CommonsPage(driver);
		searchResultsPage= commonsPage.doSearch(productName);
		productInfoPage =searchResultsPage.selectProductName(mainProductName);
		String mainProductNameValue= productInfoPage.getMainProductName();
		System.out.println("mainProductNameValue: "+mainProductNameValue);
		Assert.assertEquals(mainProductNameValue,mainProductName);
	}
}
