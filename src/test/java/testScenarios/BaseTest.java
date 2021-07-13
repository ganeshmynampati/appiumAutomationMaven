package testScenarios;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseTest {

	static AndroidDriver<AndroidElement> driver = null;
	static File appDir;
	static File app;
	static DesiredCapabilities cap;

	public static AndroidDriver<AndroidElement> getCapabilities(String platformName, String version, String appName,
			String deviceName, boolean isAndroid) throws MalformedURLException {
		appDir = new File("src/test/java");
		app = new File(appDir, appName);
		cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		cap.setCapability(MobileCapabilityType.VERSION, version);
		cap.setCapability("autoGrantPermissions", true);
		cap.setCapability("noReset", "false");
		if (isAndroid) {
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		} else {
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		}
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		return driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
	}

}
