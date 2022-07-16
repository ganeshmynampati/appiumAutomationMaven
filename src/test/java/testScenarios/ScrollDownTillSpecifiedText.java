package testScenarios;

import java.net.MalformedURLException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ScrollDownTillSpecifiedText extends BaseTest {

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
	public void scrollDownTillSpecificText() throws MalformedURLException {
		driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));");
	}

	@AfterTest
	public void tearDown() throws MalformedURLException {
		driver.quit();
		stopAppiumServer();
	}

}