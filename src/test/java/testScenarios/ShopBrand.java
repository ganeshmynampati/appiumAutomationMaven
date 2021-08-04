package testScenarios;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ShopBrand extends BaseTest {

	static AndroidDriver<AndroidElement> driver = null;
	int count = 0;
	String amount = null;
	String countryName = "Bangladesh";
	List<String> prodToSelect = new ArrayList<String>();
	String prod1 = "Air Jordan 9 Retro";
	String prod2 = "Nike SFB Jungle";

	@BeforeTest
	public void setUp() throws MalformedURLException {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			startAppiumServer();
			driver = getCapabilities("Android", "10.0", "General-Store.apk", null, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void login() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@resource-id='android:id/text1']")).click();
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + countryName + "\"))");
		List<AndroidElement> countryList = driver.findElements(By.className("android.widget.TextView"));
		for (AndroidElement country : countryList)
			if (country.getText().equals(countryName)) {
				country.click();
				break;
			}
		driver.findElement(By.xpath("//*[@resource-id='com.androidsample.generalstore:id/nameField']"))
				.sendKeys("testUser");
		driver.findElement(By.xpath("//*[@resource-id='com.androidsample.generalstore:id/radioFemale']")).click();
		driver.findElement(By.xpath("//*[@resource-id='com.androidsample.generalstore:id/btnLetsShop']")).click();
	}

	@Test(enabled = false)
	public void verifyToastMessage() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@resource-id='com.androidsample.generalstore:id/btnLetsShop']")).click();
		String toastPrompt = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		Assert.assertEquals(toastPrompt, "Please enter your name");

	}

	@Test
	public void addMultipleProducts() {
		prodToSelect.add(prod1);
		prodToSelect.add(prod2);
		for (int i = 0; i < prodToSelect.size(); i++) {
			driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(text(\""
							+ prodToSelect.get(i) + "\"))"));
			int nums = driver
					.findElements(By.xpath("//*[@resource-id='com.androidsample.generalstore:id/productAddCart']"))
					.size();
			for (int j = 0; j < nums; j++) {
				if (driver.findElements(By.xpath("//*[@resource-id='com.androidsample.generalstore:id/productName']"))
						.get(j).getText().equalsIgnoreCase(prodToSelect.get(i))) {
					driver.findElements(
							By.xpath("//*[@resource-id='com.androidsample.generalstore:id/productAddCart']")).get(j)
							.click();
					break;
				}
			}

		}

	}

	@AfterTest
	public void tearDown() throws MalformedURLException {
		driver.quit();
		stopAppiumServer();
	}

}
