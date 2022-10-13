package com.abpayments.com.mobile;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.util.concurrent.Uninterruptibles;

import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MobileAppTest {
	
	private static final By CONTINUE_EMAIL_OPTION = By.id("com.tv.v18.viola:id/dialog_fr_btn_traditional");
	
	private static final By EMAIl_ID = By.id("com.tv.v18.viola:id/email");
	
	private static final By PASSWORD_ID = By.id("com.tv.v18.viola:id/password");
	
	private static final By CONTINUE_BTN = By.id("com.tv.v18.viola:id/btn_login");
	
	private static final By REMAIN_LATER_ID = By.id("com.tv.v18.viola:id/btn_home_page");
	
	private static final By CLOSE_AD = By.id("com.tv.v18.viola:id/floater_ad_close_img");
	
	private static final By PROFILE = By.id("com.tv.v18.viola:id/act_iv_profile_image");
	
	private static final By SIGN_OUT = By.id("com.tv.v18.viola:id/frag_tv_sign_out");
	
	private static final By LOG_OUT = By.id("com.tv.v18.viola:id/txt_sign_out");
	
	private static ThreadLocal<WebDriver> threadlocalDriver = new ThreadLocal<>();

	
	public static WebDriver getDriver() {
		return threadlocalDriver.get();
	}
	
	public static void setDriver(WebDriver driver) {
		threadlocalDriver.set(driver);
	}
	
	@BeforeTest
	public static void launchApp() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability("appPackage", "com.tv.v18.viola");
		cap.setCapability("appActivity", "com.tv.v18.viola.view.activity.SVSplashScreenActivity");
		cap.setCapability("udid", "RZ8M40C5S6X");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		
		setDriver(new AndroidDriver<>(url, cap));
	}
	
	@Test
	public static void invokeApp() {
		((InteractsWithApps) getDriver()).launchApp();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().findElement(CONTINUE_EMAIL_OPTION).click();
		getDriver().findElement(EMAIl_ID).sendKeys("test992@v.com");
		((AndroidDriver) getDriver()).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		getDriver().findElement(CONTINUE_BTN).click();
		getDriver().findElement(PASSWORD_ID).sendKeys("password");
		((AndroidDriver) getDriver()).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		getDriver().findElement(CONTINUE_BTN).click();
		getDriver().findElement(REMAIN_LATER_ID).click();
		Uninterruptibles.sleepUninterruptibly(2000, TimeUnit.MILLISECONDS);
		getDriver().findElement(CLOSE_AD).click();
		scrollToElement(getTrayTitle());
		Uninterruptibles.sleepUninterruptibly(2000, TimeUnit.MILLISECONDS);
		scroll("up");
		Uninterruptibles.sleepUninterruptibly(2000, TimeUnit.MILLISECONDS);
		scrollRight();
//		swipe("RIGHT", 3, 2000);
		getDriver().findElement(PROFILE).click();
		getDriver().findElement(SIGN_OUT).click();
		getDriver().findElement(LOG_OUT).click();
		assertTrue(getDriver().findElement(CONTINUE_EMAIL_OPTION).isDisplayed());
		
		
	}
	
	
	@AfterTest
	public void quitDriver() {
		getDriver().quit();
	}
	
	
	public static String getTrayTitle() {
		String url = "https://psapi.voot.com/jio/voot/v1/voot-mobile/view/my-voot?responseType=common&features=include%3AbuttonsTray&variant=0";
		 Response response = RestAssured.given().baseUri(url).when().get().then().assertThat().statusCode(200).extract().response();
		 
		return response.body().jsonPath().get("trays[9].title").toString();
	}
	
	public static void scrollToElement(String elementText) {
		((FindsByAndroidUIAutomator) getDriver()).findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
							+ elementText + "\").instance(0))");
	}
	
	
	
	public static void swipe(String direction, int count, int time) {
		Dimension size = getDriver().manage().window().getSize();
		try {
			switch (direction) {
			case "left":
			case "LEFT":
				for (int i = 0; i < count; i++) {
					int startx = (int) (size.width * 0.8);
					int endx = (int) (size.width * 0.20);
					int starty = size.height / 2;
					touchActions(startx, starty, endx, starty, time);
				}
				break;
			case "right":
			case "RIGHT":
				for (int j = 0; j < count; j++) {
					int endx = (int) (size.width * 0.8);
					int startx = (int) (size.width * 0.20);
					int starty = size.height / 2;
					touchActions(startx, starty, endx, starty, time);
				}
				break;
			case "up":
			case "UP":
				for (int j = 0; j < count; j++) {
					int starty = (int) (size.height * 0.80);
					int endy = (int) (size.height * 0.20);
					int startx = size.width / 2;
					touchActions(startx, starty, startx, endy, time);
				}
				break;
			case "down":
			case "DOWN":
				for (int j = 0; j < count; j++) {
					int starty = (int) (size.height * 0.80);
					int endy = (int) (size.height * 0.20);
					int startx = size.width / 2;
					touchActions(startx, endy, startx, starty, time);
				}
				break;
			default:
				break;
			}
		} catch (Exception e) {
		
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void touchActions(int a1, int b1, int a2, int b2, int time) {
		TouchAction touchAction = new TouchAction((PerformsTouchActions) getDriver());
		touchAction.press(PointOption.point(a1, b1)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(time)))
				.moveTo(PointOption.point(a2, b2)).release();
		touchAction.perform();
	}
	
	
	public static void scroll(String upOrDown) {

		TouchAction action = new TouchAction((PerformsTouchActions) getDriver());
		PointOption startPoint = new PointOption().withCoordinates(500, 1000);
		PointOption endPoint = new PointOption().withCoordinates(300, 300);
		switch (upOrDown.toLowerCase()) {
		case "up":
			startPoint = new PointOption().withCoordinates(500, 1000);
			endPoint = new PointOption().withCoordinates(300, 300);
			break;
		case "down":
			startPoint = new PointOption().withCoordinates(300, 400);
			endPoint = new PointOption().withCoordinates(500, 1000);
			break;
		}
		action.press(startPoint).waitAction(new WaitOptions().withDuration(Duration.ofMillis(500))).moveTo(endPoint)
				.release().perform();
	}
	
	public static void scrollRight() {
		List<AndroidElement> e=getDriver().findElements(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.tv.v18.viola:id/vh_iv_popular_card_item\")"));
		AndroidElement firdelement=e.get(0);
		AndroidElement secondElement=e.get(1);
		AndroidElement thirdElement=e.get(2);
									
		int midOfY =thirdElement.getLocation().y +(thirdElement.getSize().height/2);
		int fromXLocation=thirdElement.getLocation().x;
		int toXLocation=firdelement.getLocation().x;
								
		TouchAction  action =new TouchAction((PerformsTouchActions) getDriver());
		action.press(PointOption.point(fromXLocation, midOfY))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
		.moveTo(PointOption.point(toXLocation, midOfY))
		.release()
		.perform();
	}

}
