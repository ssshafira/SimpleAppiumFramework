package qa.mobile;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LoginTests {
	AppiumDriver driver;
  @Test
  public void invalidUsername() {
	  WebElement sideMenu = driver.findElement(AppiumBy.accessibilityId("open menu"));
	  sideMenu.click();
	  WebElement loginMenu = driver.findElement(AppiumBy.accessibilityId("menu item log in"));
	  loginMenu.click();
	  
	  WebElement usernameTxtFld = driver.findElement(AppiumBy.accessibilityId("Username input field"));
	  WebElement passwordTxtFld = driver.findElement(AppiumBy.accessibilityId("Password input field"));
	  WebElement loginBtn = driver.findElement(AppiumBy.accessibilityId("Login button"));
	  
	  usernameTxtFld.clear();
	  usernameTxtFld.sendKeys("invalidusername");
	  passwordTxtFld.clear();
	  passwordTxtFld.sendKeys("10203040");
	  loginBtn.click();
	  
	  WebElement errTxt = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"generic-error-message\"]/android.widget.TextView"));
	  
	  String actualErrTxt = errTxt.getAttribute("text");
	  System.out.println("actual err txt : " + actualErrTxt);
	  String expectedErrTxt = "Provided credentials do not match any user in this service.";
	  
	  Assert.assertEquals(actualErrTxt, expectedErrTxt);
  }
  
  @Test
  public void invalidPassword() {
	  WebElement sideMenu = driver.findElement(AppiumBy.accessibilityId("open menu"));
	  sideMenu.click();
	  WebElement loginMenu = driver.findElement(AppiumBy.accessibilityId("menu item log in"));
	  loginMenu.click();
	  
	  WebElement usernameTxtFld = driver.findElement(AppiumBy.accessibilityId("Username input field"));
	  WebElement passwordTxtFld = driver.findElement(AppiumBy.accessibilityId("Password input field"));
	  WebElement loginBtn = driver.findElement(AppiumBy.accessibilityId("Login button"));
	  
	  usernameTxtFld.clear();
	  usernameTxtFld.sendKeys("bob@example.com");
	  passwordTxtFld.clear();
	  passwordTxtFld.sendKeys("invalidpassword");
	  loginBtn.click();
	  
	  WebElement errTxt = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"generic-error-message\"]/android.widget.TextView"));
	  
	  String actualErrTxt = errTxt.getAttribute("text");
	  System.out.println("actual err txt : " + actualErrTxt);
	  String expectedErrTxt = "Provided credentials do not match any user in this service.";
	  
	  Assert.assertEquals(actualErrTxt, expectedErrTxt);
  }
  
  @Test
  public void successfullLogin() throws InterruptedException {
	  WebElement sideMenu = driver.findElement(AppiumBy.accessibilityId("open menu"));
	  sideMenu.click();
	  WebElement loginMenu = driver.findElement(AppiumBy.accessibilityId("menu item log in"));
	  loginMenu.click();
	  
	  WebElement usernameTxtFld = driver.findElement(AppiumBy.accessibilityId("Username input field"));
	  WebElement passwordTxtFld = driver.findElement(AppiumBy.accessibilityId("Password input field"));
	  WebElement loginBtn = driver.findElement(AppiumBy.accessibilityId("Login button"));
	  
	  usernameTxtFld.clear();
	  usernameTxtFld.sendKeys("bob@example.com");
	  passwordTxtFld.clear();
	  passwordTxtFld.sendKeys("10203040");
	  loginBtn.click();
	  
	  Thread.sleep(3000);
	  
	  WebElement producTitle = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"container header\"]/android.widget.TextView"));
	  
	  String actualProductTitle = producTitle.getAttribute("text");
	  System.out.println("actual product title : " + actualProductTitle);
	  String expectedProductTitle = "Products";
	  
	  Assert.assertEquals(actualProductTitle, expectedProductTitle);
  }
  
  @BeforeClass
  public void beforeClass() throws MalformedURLException {
	  DesiredCapabilities caps = new DesiredCapabilities();
	  caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	  caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
	  caps.setCapability(MobileCapabilityType.UDID, "RR8R105VJLX");

	  String appUrl = 
			  System.getProperty("user.dir") 
			  + File.separator + "src" 
			  + File.separator + "test" 
			  + File.separator + "resources"
			  + File.separator + "Android-MyDemoAppRN.1.3.0.build-244.apk";
		
	  caps.setCapability(MobileCapabilityType.APP, appUrl);
		
	  URL url = new URL("http://0.0.0.0:4723");
		
	  driver = new AndroidDriver(url, caps);
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
