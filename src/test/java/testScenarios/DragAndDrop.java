package testScenarios;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;

public class DragAndDrop extends BaseTest {

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

	@Test
	public void dragAndDrop() {
		driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Drag and Drop\"));");
		driver.findElementByAndroidUIAutomator("text(\"Drag and Drop\")").click();
		WebElement sourceEle = driver
				.findElement(By.xpath("//*[@class='android.widget.RelativeLayout']/child::android.view.View[1]"));
		WebElement destEle = driver
				.findElement(By.xpath("//*[@class='android.widget.RelativeLayout']/child::android.view.View[2]"));
		TouchAction ta = new TouchAction(driver);
		ta.longPress(longPressOptions().withElement(element(sourceEle))).moveTo(element(destEle)).release().perform();

	}
	@AfterTest
	public void tearDown() throws MalformedURLException {
		driver.quit();
		stopAppiumServer();
	}

}
