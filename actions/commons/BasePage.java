package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	public void openUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();

	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();

	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refeshPage(WebDriver driver) {
		driver.navigate().refresh();

	}

	public void AcceptToAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelToAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();

	}

	public String getTextToAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void sendkeyToAlert(WebDriver driver, String valueToSendkey) {
		driver.switchTo().alert().sendKeys(valueToSendkey);
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String expectedPageTitle) {

		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String currentPageTitle = driver.getTitle();
			System.out.println(currentPageTitle);

			if (currentPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowWithoutParent(WebDriver driver, String parentID) {

		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	/* Web Element */

	public WebElement getWebElement(WebDriver driver, String Locator) {
		return driver.findElement(By.xpath(Locator));

	}

	public List<WebElement> getListWebElements(WebDriver driver, String Locator) {
		return driver.findElements(By.xpath(Locator));
	}

	public By getByXpath(String Locator) {
		return By.xpath(Locator);
	}

	public void clickToElement(WebDriver driver, String Locator) {
		getWebElement(driver, Locator).click();
	}

	public void sendkeyToElement(WebDriver driver, String Locator, String valueToSendkey) {
		getWebElement(driver, Locator).clear();
		getWebElement(driver, Locator).sendKeys(valueToSendkey);

	}

	public String getElementText(WebDriver driver, String Locator) {
		return getWebElement(driver, Locator).getText();
	}

	public void selectItemInDropdown(WebDriver driver, String Locator, String itemValue) {
		// Select select = new Select(getWebElement(driver, Locator));
		// select.selectByVisibleText(itemValue);

		new Select(getWebElement(driver, Locator)).selectByVisibleText(itemValue);

	}

	public String getSelectedOptionInDropdown(WebDriver driver, String Locator) {

		return new Select(getWebElement(driver, Locator)).getFirstSelectedOption().getText();

	}

	public boolean isDropdownMultiple(WebDriver driver, String Locator) {

		return new Select(getWebElement(driver, Locator)).isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator,
			String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);

		List<WebElement> allItems = new WebDriverWait(driver, 30)
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

	}

	public String getElementAttribute(WebDriver driver, String parentLocator, String attributeName) {

		return getWebElement(driver, parentLocator).getAttribute(attributeName);

	}

	public void getElementCssValue(WebDriver driver, String parentLocator, String propertyName) {
		getWebElement(driver, parentLocator).getCssValue(propertyName);

	}

	public String getHexaColorByRgbaColor(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public void getElementsNumber(WebDriver driver, String parentLocator) {
		getListWebElements(driver, parentLocator).size();

	}

	public void clickToCheckboxRadio(WebDriver driver, String parentLocator) {
		if (!getWebElement(driver, parentLocator).isSelected()) {
			getWebElement(driver, parentLocator).click();
		}
	}

	public void uncheckToCheckboxRadio(WebDriver driver, String parentLocator) {
		if (getWebElement(driver, parentLocator).isSelected()) {
			getWebElement(driver, parentLocator).click();
		}
	}

	public boolean isElementDisplay(WebDriver driver, String parentLocator) {
		return getWebElement(driver, parentLocator).isDisplayed();

	}

	public boolean isElementSelected(WebDriver driver, String parentLocator) {
		return getWebElement(driver, parentLocator).isSelected();

	}

	public boolean isElementEnabled(WebDriver driver, String parentLocator) {
		return getWebElement(driver, parentLocator).isEnabled();

	}

	public void switchtoFrame(WebDriver driver, String parentLocator) {
		driver.switchTo().frame(getWebElement(driver, parentLocator));
	}

	public void switchToDefaulContent(WebDriver driver, String parentLocator) {
		driver.switchTo().defaultContent();
	}

	public void moveToElement(WebDriver driver, String parentLocator) {
		// Actions action = new Actions(driver);
		// action.moveToElement(getWebElement(driver, parentLocator)).perform();

		new Actions(driver).moveToElement(getWebElement(driver, parentLocator)).perform();

	}

	public void doubleClickToElement(WebDriver driver, String parentLocator) {
		new Actions(driver).doubleClick(getWebElement(driver, parentLocator)).perform();

	}

	public void rightClickToElement(WebDriver driver, String parentLocator) {
		new Actions(driver).contextClick(getWebElement(driver, parentLocator)).perform();

	}

	public void dragAndDropElement(WebDriver driver, String SourceLocator, String TargetLocator) {
		new Actions(driver).dragAndDrop(getWebElement(driver, SourceLocator), getWebElement(driver, TargetLocator))
				.perform();

	}

	public void sendKeyToElement(WebDriver driver, String Locator, String key) {
		new Actions(driver).sendKeys(getWebElement(driver, Locator), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		return (String) ((JavascriptExecutor) driver).executeScript(javaScript);

	}

	public String getInnerText(WebDriver driver) {

		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {

		((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {

		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element,
				"style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element,
				"style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getWebElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {

		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')",
				getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {

		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {

		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {

		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public void waitForElementVisible(WebDriver driver, String locator) {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
		
	}
	public void waitForElementClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
		
	
	}
	public void waitForElementInvisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	
}
	public void waitForListElementInvisible(WebDriver driver, String locator) {
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, locator)));
		
	}
	public void waitForElementPresence(WebDriver driver, String locator) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(getByXpath(locator)));

	}
	public void waitForAllElementPresence(WebDriver driver, String locator) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
		
	}
	public void waitForAlertPresence(WebDriver driver) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.alertIsPresent());
	}
}	