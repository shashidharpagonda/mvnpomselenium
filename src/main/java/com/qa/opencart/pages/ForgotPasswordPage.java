package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
	WebDriver driver;
	
	By forgotPasswordText = By.xpath("//div/h1");
	
	public ForgotPasswordPage(WebDriver driver)
	{
		this.driver= driver;
	}

	public String getForgotPwdPageTitle()
	{
		String title=driver.getTitle();
		System.out.println("Forgot Password Page Title:"+ title);
		return title;
	}
	
	
}
