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

import commons.BasePage;

public class Level_02_BasePage_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitwait;
	String emailAdd;
	BasePage basePage;
	
	
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
		basePage = new BasePage();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		emailAdd = "HAN" + getRandomNumber() + "@hotmail.com";
		
	}
		
	@Test
	public void Register_01_Register() {
		basePage.openUrl(driver, "https://demo.nopcommerce.com");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//input[@id='gender-male']");
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "HAN");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "TRAN");
		basePage.selectItemInDropdown(driver, "//select[@name='DateOfBirthDay']", "2");
		basePage.selectItemInDropdown(driver, "//select[@name='DateOfBirthMonth']", "July");
		basePage.selectItemInDropdown(driver, "//select[@name='DateOfBirthYear']", "1989");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAdd);
		System.out.println(emailAdd);
		basePage.scrollToBottomPage(driver);
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		basePage.clickToElement(driver, "//button[@id='register-button']");
		Assert.assertTrue(basePage.isElementDisplay(driver, "//div[text()='Your registration completed']"));
		basePage.clickToElement(driver, "//a[@class='ico-logout']");
		
		Assert.assertTrue(basePage.waitForPageUrlToBe(driver, "https://demo.nopcommerce.com/"));
		Assert.assertEquals(basePage.getCurrentUrl(driver), "https://demo.nopcommerce.com/");
		

	}
	@Test
	public void Register_02_Login() {
		basePage.clickToElement(driver, "//a[@class='ico-login']");
		basePage.sendKeyToElement(driver, "//input[@class='email']", emailAdd);
		basePage.sendKeyToElement(driver, "//input[@class='password']", "123456");
		basePage.clickToElement(driver, "//button[@class='button-1 login-button']");
		basePage.clickToElement(driver, "//a[@class='ico-account']");
		Assert.assertTrue(basePage.isElementSelected(driver, "//input[@value='M']"));
		Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@id='FirstName']", "value"), "HAN");
		Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@id='LastName']", "value"), "TRAN");
		Assert.assertEquals(basePage.getSelectedOptionInDropdown(driver, "//select[@name='DateOfBirthDay']"), "2");
		Assert.assertEquals(basePage.getSelectedOptionInDropdown(driver, "//select[@name='DateOfBirthMonth']"), "July");
		Assert.assertEquals(basePage.getSelectedOptionInDropdown(driver, "//select[@name='DateOfBirthYear']"), "1989");
		Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@id='Email']", "value"), emailAdd);
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public int getRandomNumber() {
		return new Random().nextInt(99999);
	}
		
	
	
	
	
}

