package testScenarios;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LongPress extends BaseTest {

	@BeforeTest
	public void setUp() throws MalformedURLException {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			startAppiumServer();
			driver = getCapabilities("Android", "10.0", "ApiDemos-debug.apk", null, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void longPress() throws MalformedURLException {
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
		driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']").click();
		driver.findElementByXPath("//android.widget.TextView[@text='1. Custom Adapter']").click();
		WebElement peopleOption = driver.findElementByXPath("//android.widget.TextView[@text='People Names']");
		TouchAction ta = new TouchAction(driver);
		ta.longPress(longPressOptions().withElement(element(peopleOption)).withDuration(ofSeconds(3))).release()
				.perform();
		String longPressText = driver
				.findElement(By.xpath("//*[@class='android.widget.FrameLayout']/child::android.widget.TextView"))
				.getText();
		System.out.println("The text obtained from long press is " + longPressText);

	}
	@AfterTest
	public void tearDown() throws MalformedURLException {
		driver.quit();
		stopAppiumServer();
	}

}
