package com.qa.opencart.tests;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoTest extends BaseTest{

	@BeforeClass
	public void productInfoSetup()
	{
		commonsPage =new CommonsPage(driver);
		productInfoPage = new ProductInfoPage(driver);
	}
	
	@DataProvider
	public Object[][] getProductData()
	{
		/*
		 * return new Object[][] { {"Macbook","MacBook Pro",4},
		 * {"Macbook","MacBook Air",4}, {"Samsung","Samsung SyncMaster 941BW",1}, };
		 */
		
		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
	}
	
	@Test(dataProvider = "getProductData")
	public void productImagesCountTest(String searchKey,String productName, int imagesCount )
	{ 
		searchResultsPage= commonsPage.doSearch(searchKey);
		productInfoPage =searchResultsPage.selectProductName(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), imagesCount);
	}
	
	@Test
	public void productDescriptionTest()
	{
		searchResultsPage= commonsPage.doSearch("MacBook");
		productInfoPage =searchResultsPage.selectProductName("MacBook Air");	
		String productDescription = productInfoPage.getProductDescription();
		System.out.println("Product Description :"+productDescription);
		
		softAssert.assertTrue(productDescription!=null);
		softAssert.assertFalse(productDescription.isEmpty());
		softAssert.assertTrue(productDescription.contains("MacBook Air"));
		softAssert.assertAll(); // It is mandatory. It will tell which above assertion got failed in the report.
	}
	
	@Test
	public void productDataTest()
	{
		searchResultsPage= commonsPage.doSearch("MacBook");
		productInfoPage =searchResultsPage.selectProductName("MacBook Air");	
		Map<String,String> actualProductInfoMap= productInfoPage.getProductInfo();
		actualProductInfoMap.forEach((k,v)->System.out.println(k +":"+ v));
	
		softAssert.assertEquals(actualProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(actualProductInfoMap.get("ProductName"), "MacBook Air");
		softAssert.assertEquals(actualProductInfoMap.get("Reward Points"), "700");
		softAssert.assertAll();
		
		// hashMap
//		Brand:Apple
//		Availability:In Stock
//		ProductName :MacBook Air
//		price:£1,202.00
//		extaxprice:Ex Tax: £1,000.00
//		Product Code:Product 17
//		Reward Points:700
		
		// LinkedHashMap  : To maintain insertion order use this
		// TreeMap : To maintain sorted order, use this.
	}
}
