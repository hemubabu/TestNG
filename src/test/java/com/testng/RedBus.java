package com.testng;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedBus {
	public static WebDriver driver;
	@SuppressWarnings("deprecation")
	@Test(priority=-7,groups="smoke",enabled=true) 
	public void launch_the_url() {
		ChromeOptions options = new ChromeOptions(); 
		WebDriverManager.chromedriver().setup();
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);
		//driver.manage().window().maximize();
		driver.get("https://www.redbus.in/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	}
	@Parameters({"fromname"})
	@Test(priority=-6,groups="smoke",dependsOnMethods="launch_the_url")
	public void from_place(@Optional ("Coimbatore")String f) throws Throwable {
		Thread.sleep(3000);
		WebElement from = driver.findElement(By.xpath("//input[@id='src']"));
		from.sendKeys(f,Keys.ENTER);
	}
	@DataProvider(name="data")
	public Object [][] data_provide(){
		return new Object[][] {{"Madurai"}};
		
		
	}
	
	@Parameters({"toname"})
	@Test(priority=-5,dataProvider="data")
	public void to_place(String t){
	WebElement to = driver.findElement(By.id("dest"));
	to.sendKeys(t,Keys.ENTER);
		
	}
	@Test(priority=-4)
	public void choose_date() throws Throwable {
		WebElement date = driver.findElement(By.xpath("(//input[@class='db'])[3]"));
		date.click();
		driver.findElement(By.xpath("//td[@class='current day']")).click();
		Thread.sleep(3000);
		
		
	}
	@Test(priority=-3)
	public void search() {
		driver.findElement(By.xpath("//button[text()='Search Buses']")).click();
	}
	@Test(priority=-2,groups="sanity")
	public void take_screenshot() throws Throwable {
		TakesScreenshot SC = (TakesScreenshot)driver;
		File source = SC.getScreenshotAs(OutputType.FILE);
		File target = new File("C:\\Users\\HEMU\\eclipse-workspace2\\TestNG\\Screenshot\\Redbus.png");
		FileUtils.copyFile(source, target);
	}
	@Test(priority=-1,groups="sanity")
	public void quit_the_browser() throws InterruptedException {
		Thread.sleep(3000);
		
		driver.quit();
	}
//	@BeforeSuite
//	public void before_suite() {
//		System.out.println("@BeforeSuite");
//	}
//	@BeforeTest
//	public void before_test() {
//		System.out.println("@BeforeTest");
//	}
//	@AfterTest
//	public void after_test() {
//		System.out.println("@AfterTest");
//	}
//	@AfterSuite
//	public void after_suite() {
//		System.out.println("@AfterSuite");
//	}

}
