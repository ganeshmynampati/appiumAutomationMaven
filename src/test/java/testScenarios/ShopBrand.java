package testScenarios;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ShopBrand extends BaseTest {

	static AndroidDriver<AndroidElement> driver = null;

	@BeforeMethod
	public void setUp() throws MalformedURLException {
		driver = getCapabilities("Android", "10.0", "General-Store.apk", null, true);
		System.out.println("Successful");
	}

	@Test(enabled = false)
	public void login() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@resource-id='android:id/text1']")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"))");
		List<AndroidElement> countryList = driver.findElements(By.className("android.widget.TextView"));
		for (AndroidElement country : countryList)
			if (country.getText().equals("India")) {
				country.click();
				break;
			}
		driver.findElement(By.xpath("//*[@resource-id='com.androidsample.generalstore:id/nameField']"))
				.sendKeys("testUser");
		driver.findElement(By.xpath("//*[@resource-id='com.androidsample.generalstore:id/radioFemale']")).click();
		driver.findElement(By.xpath("//*[@resource-id='com.androidsample.generalstore:id/btnLetsShop']")).click();
	}

	@Test
	public void verifyToastMessage() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@resource-id='com.androidsample.generalstore:id/btnLetsShop']")).click();
		String toastPrompt = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		Assert.assertEquals(toastPrompt, "Please enter your name");

	}

}
