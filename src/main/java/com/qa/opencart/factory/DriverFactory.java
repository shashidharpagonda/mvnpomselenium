package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.customexception.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author SPAGONDA
 *
 */
public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver>();
	
	/**
	 * It initializes the driver on the basis of given browser name.
	 * @param properties prop
	 * @return webdriver
	 */
	public WebDriver init_driver(Properties prop) {
		
		String browserName= prop.getProperty("browser");
		
		System.out.println("Browser Name: "+browserName);
		optionsManager= new OptionsManager(prop);
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver();
			//To add chrome options, we have to pass below as argument
			//driver=new ChromeDriver(optionsManager.getChromeOptions());  ThreadLocal 
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else {
			System.out.println("Please pass the right browser");
		}
		
		//Here getDriver() is for ThreadLocal
		getDriver().manage().deleteAllCookies();  
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	/*
	 * ThreadLocal get method
	 */
	public synchronized WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
	
	/**
	 * This method is use to initialize the properties from the respective env config file
	 * @return properties class object with all the config properties
	 */
	public Properties init_prop()
	{
		FileInputStream ip=null;
		prop = new Properties();
		
		//mvn commandline arg
		//mvn clean install -Denv="qa"   #> Here env is argument. we can give any name instead of env.
		
		String envName=System.getProperty("env");
		System.out.println("Running tests on environment: "+ envName);
		
		if(envName == null)
		{	System.out.println("No env is given.... Hence running on QA env.");
			try {
				ip=new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
			switch(envName.toLowerCase())
			{
			case "dev": ip=new FileInputStream("./src/test/resources/config/dev.config.properties");
			break;
			case "qa": ip=new FileInputStream("./src/test/resources/config/qa.config.properties");
			break;
			case "stage": ip=new FileInputStream("./src/test/resources/config/stage.config.properties");
			break;			
			case "prod": ip=new FileInputStream("./src/test/resources/config/config.properties");
			break;
			
			default:
			System.out.println("Please pass the right env"+envName);
			throw new FrameworkException("No env found...");
				
			}
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			} catch (FrameworkException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public String getScreenshot() {
		File scrFile= ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path= "./screenshot/"+System.currentTimeMillis()+".png";
		File destination= new File(path);
		try {
			FileUtils.copyFile(scrFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
}
