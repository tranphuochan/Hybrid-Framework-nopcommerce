package com.nopcommerce.users;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class User_01_Repeat_Yourself {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitwait;
	String emailAdd;
	
	@BeforeClass
	public void BeforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			if (osName.contains("MAC")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");	
			driver = new ChromeDriver();
			}
		}
		
		explicitwait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		emailAdd = "HAN" + getRandomNumber() + "@hotmail.com";
		driver.get("https://demo.nopcommerce.com");
	}
		
	@Test
	public void Register_01_Register() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.cssSelector("input#gender-male")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("HAN");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("TRAN");
		new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).selectByVisibleText("2");
		new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).selectByVisibleText("July");
		new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).selectByVisibleText("1989");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAdd);
		System.out.println(emailAdd);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#register-button")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());
		
		driver.findElement(By.cssSelector("a.ico-logout")).click();
		explicitwait.until(ExpectedConditions.urlToBe("https://demo.nopcommerce.com/"));
		Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/");

	}
	@Test
	public void Register_02_Login() {
		driver.findElement(By.cssSelector("a.ico-login")).click();
		driver.findElement(By.cssSelector("input.email")).sendKeys(emailAdd);
		driver.findElement(By.cssSelector("input.password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button.login-button")).click();
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='M']")).isSelected());
		Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), "HAN");
		Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), "TRAN");
		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).getFirstSelectedOption().getText(),"2");
		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(), "July");
		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).getFirstSelectedOption().getText(), "1989");
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), emailAdd);
		

	}
	@Test
	public void Register_04_Password_Not_Match() {
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public int getRandomNumber() {
		return new Random().nextInt(99999);
	}
		
	
	
	
	
}

