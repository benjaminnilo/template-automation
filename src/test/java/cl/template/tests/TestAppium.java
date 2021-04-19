package cl.template.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestAppium {
	
	@Test
	public void openAppOnAndroid() throws MalformedURLException {
		
		String  url = "http://127.0.0.1:4723/wd/hub";
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_3a_API_30_X86");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
		caps.setCapability(MobileCapabilityType.APP, "/Users/bnilo/eclipse-workspace/Appium_QAcart_To_Do_App/android/app/build/outputs/apk/debug/app-debug.apk");

		AppiumDriver driver = new AppiumDriver(new URL(url), caps);
	}
	
	/*
	@Test
	public void openAppOnIOS() throws MalformedURLException {
		
		String  url = "http://127.0.0.1:45454/wd/hub";
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.IOS);
		caps.setCapability(MobileCapabilityType.APP, "/Users/bnilo/Library/Developer/Xcode/DerivedData/todoqacart-dmpknaofsnyyalesyjegiubjhnut/Build/Products/Debug-iphonesimulator/todoqacart.app");
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
		caps.setCapability("browserstack.local", "true");


		AppiumDriver driver = new AppiumDriver(new URL(url), caps);
	}*/

}
