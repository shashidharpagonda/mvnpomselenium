package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class CommonsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	//private By search = By.tagName("search");
	private By search = By.xpath("//div[@id='search']/input[@name='search']");
	private By searchIcon = By.cssSelector("div#search button");
	
	public CommonsPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public SearchResultsPage doSearch(String productName)
	{
		WebElement searchElement=eleUtil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT);
		searchElement.clear();
		searchElement.sendKeys(productName);
		eleUtil.doClick(searchIcon);
		
		return new SearchResultsPage(this.driver);
	}
}
