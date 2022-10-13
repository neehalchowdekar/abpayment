package com.abpayments.com.abpayment;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.abpayments.api.applicationapi.DepositApi;
import com.abpayments.api.utils.ApiUtils;
import com.abpayments.ui.pages.LoginPage;
import com.google.gson.JsonArray;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;

public class ABTest {
	
	
	@Test
	public void abTest() {
		String orderId = new LoginPage().loginToWebSite("abc", "123")
					   .navigateToProduct()
					   .navigateToPaymentPage()
					   .clickOnPaymentOption("UPI")
					   .clickOnDespositButton()
					   .getOrderId();
		
		System.out.println(orderId);
			
	}
	
	
	@Test
	public void testUserId() throws JSONException {
		
		for(int i = 0; i < 1000; i++) {
			
			int randomUserId = ApiUtils.randomNumberGen(1, 10);
			int userId = ApiUtils.getPaymentPath(randomUserId);
			Response response = DepositApi.get(userId);
			JsonArray paymentOptions = response.body().path("Payment_options");
			assertEquals(paymentOptions, ApiUtils.getPaths(userId));
			
		}
			
//			String paymentOption = response.body().path("Payment_options"+ "[" + userId + "]");
		
	}
	
	
	//Mobile -> Appium
	
	private static final By HEADER = By.xpath("//android.view.ViewGroup[@resource-id = 'android:id/action_bar']/android.widget.TextView");
	private static final By LIST = By.id("android:id/text1");
	
	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
	
	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}
	
	public static void setDriver(WebDriver driver) {
		threadLocalDriver.set(driver);
	}
	
	@BeforeTest
	public void launchApp() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/android-app.apk");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		
		setDriver(new AndroidDriver<>(url, cap));
	}
	
	@Test
	public void mobileAppTest() {
		String header = getDriver().findElement(HEADER).getText();
		System.out.println(header);
		assertEquals(header, "API Demos");
		getDriver()
		.findElements(LIST)
		.stream()
		.map(e -> e.getText())
		.forEach(System.out::println);
		
	}
	
	@AfterTest
	public void closeApp() {
		getDriver().quit();
	}
	
	
	
// Website -> Selenium	
	
private static ThreadLocal<WebDriver> threadWebLocalDriver = new ThreadLocal<>();
	
	private static WebDriver getWebDriver() {
		return threadWebLocalDriver.get();
	}
	
	private static void setWebDriver(WebDriver driver) {
		threadWebLocalDriver.set(driver);
	}
	
	@BeforeMethod
	public void initDriver() {
		WebDriverManager.chromedriver().setup();
		setWebDriver(new ChromeDriver());
	}
	
	@Test
	public void siteTest() {
		getWebDriver().get("https://www.google.com/");
		boolean logo = getDriver().findElement(By.id("hplogo")).isDisplayed();
		assertTrue(logo);
	}
	
	@AfterMethod
	public void quitDriver() {
		getWebDriver().quit();
	}

}
