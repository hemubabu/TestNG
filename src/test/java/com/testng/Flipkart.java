package com.testng;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart {
	public static WebDriver driver;
	static long starttime;
	@DataProvider(name="Dataname")
	public Object[][] dataProvide() {
		return new Object[][] {{"SAMSUNG"}};
		
	}
	@Parameters({"driver"})
	@BeforeClass
	public static void launch(String drive) {
	
		if(drive.equals("edge"))
		{
			EdgeOptions options = new EdgeOptions(); 
			WebDriverManager.edgedriver().setup();
			options.addArguments("start-maximized");
			driver = new EdgeDriver(options);
			// driver.manage().window().maximize();
			driver.get("https://www.flipkart.com/");
			//driver.findElement(By.xpath("//button[text()='✕']")).click();
		}
		else 
		{
			ChromeOptions options = new ChromeOptions(); 
			WebDriverManager.chromedriver().setup();
			options.addArguments("start-maximized");
			driver = new ChromeDriver(options);
			//driver.manage().window().maximize();
			driver.get("https://www.flipkart.com/");
			//driver.findElement(By.xpath("//button[text()='✕']")).click();
		}
		
		
	}
	@BeforeMethod()
	public void startTime() {
		System.out.println("START TIME:");
		starttime = System.currentTimeMillis();
		System.out.println(starttime);
			
	}
	@AfterMethod
	public void endTime() {
		long endtime = System.currentTimeMillis();
		System.out.println("END TIME:");
		System.out.println(endtime-starttime);
	}
	
	// To use Data Provider Concept

	@Test(priority=-3,dataProvider="Dataname",retryAnalyzer=Rerun.class)
	public void search(String S) throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='✕']")).click();
		System.out.println("search");
		

		WebElement search =driver.findElement(By.name("q"));
	;
		search.sendKeys(S,Keys.ENTER);
	}
	@Test(priority=-2,dataProvider="Dataname",retryAnalyzer=Rerun.class)
	public void samsungModels(String S) {
		System.out.println("samsungModels");
		List<WebElement> mobiles = driver.findElements(By.xpath("//a[contains(text(),'"+S+"')]"));
		int size = mobiles.size();
		for(int i=0;i<size;i++) {
			WebElement mob = mobiles.get(i);
			String text = mob.getText();
			System.out.println(text);
			
		}
		
		
	}
	static int count =0;
	@Test(priority=-1,invocationCount=3)
	public void screenShot() throws IOException, InterruptedException {
		System.out.println("screenShot");
		count++;
		Thread.sleep(3000);
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File("C:\\Users\\HEMU\\eclipse-workspace2\\TestNG\\Screenshot\\Samsung"+count+".png");
		FileUtils.copyFile(source, target);
		
	}
	@AfterClass
	public static void quit() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
		
	}


}
