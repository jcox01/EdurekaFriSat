package day3;

import static org.testng.Assert.assertEquals;

import java.awt.Stroke;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.google.common.base.Function;

public class CaseStudy1 {

	public static void main(String[] args) throws Throwable {
	
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/John/eclipse-workspace/Libs/chromedriver_win32/chromedriver.exe");
		
		// user profiles in Chrome was used because the standard profile kept reseting and popups continued to 
		// occur, stopping proper execution.  Profiles fixed the issue.
		
		ChromeOptions options = new ChromeOptions();
		String userProfile = "C:/Users/John/AppData/Local/Google/Chrome/User Data/Profile 1";
		options.addArguments("user-data-dir="+userProfile);
	// set maximized
		options.addArguments("--start-maximized");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();	
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		WebDriver driver = new ChromeDriver(capabilities);

		driver.manage().deleteAllCookies();

	// launch chrome browser
		driver.get("https://www.google.com");
		
	// set implicit wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	// set pageLoadTimeout
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		
		driver.navigate().to("https://www.edureka.co/");
		
	//Creating the JavascriptExecutor interface object 		
        JavascriptExecutor js = (JavascriptExecutor)driver;		

        //Declare and set the start time		
        long start_time = System.currentTimeMillis();			
                 
    //Call executeAsyncScript() method to wait for 0.5 seconds		
        js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 500);");			
        		
        //Get the difference (currentTime - startTime)  of times.		
        System.out.println("Passed time: " + (System.currentTimeMillis() - start_time));					
                  		
	// search for Selenium press enter
		driver.findElement(By.id("homeSearchBar")).sendKeys("Selenium");
		driver.findElement(By.id("homeSearchBar")).sendKeys(Keys.ENTER);

		WebDriverWait wait = new WebDriverWait(driver, 10);
	
	//Explicit wait for web page to complete fully
		WebElement course = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
				("//*[@id='course-box-535']/div/span/div[3]/div[1]/h3/a")));
	// click on Selenium for details
		course.click();
		
		System.out.println(course.toString());

	// compare title expected to actual
		String actPageTitle = driver.getTitle();
		String expPageTitle = "Selenium 3.0 WebDriver Online Training | Selenium Certification Course | Edureka";
		System.out.println("The title is: " + actPageTitle);
		assertEquals(actPageTitle, expPageTitle);
	
	// navigate back to previous page
		driver.navigate().back();
		
		checkPageIsReady(driver);
		
//	// fluent wait here
//		
//		FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
//				.withTimeout(30, TimeUnit.SECONDS)
//				.pollingEvery(500, TimeUnit.MILLISECONDS)
//				.ignoring(NoSuchElementException.class);
//		
//		WebElement foo = fWait.until(new Function<WebDriver, WebElement>() {
//
//			public WebElement apply(WebDriver driver) {
//				return driver.findElement(By.xpath("//a[@href='/all-courses']"));
//			}
//		});
//		System.out.println(foo.toString());
	
	// Select All courses
		driver.findElement(By.xpath("//a[@href='/all-courses']")).click();
	
	// fluent wait here
		
		FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		
		WebElement foo = fWait.until(new Function<WebDriver, WebElement>() {

			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//a[@href='/all-courses']"));
			}
		});
		System.out.println(foo.toString());

		
		checkPageIsReady(driver);
	}
	

		public static void checkPageIsReady(WebDriver driver) {
		  
		  JavascriptExecutor js = (JavascriptExecutor)driver;
		  
/*		  //Initially bellow given if condition will check ready state of page.
		  if (js.executeScript("return document.readyState").toString().equals("complete")){ 
				System.out.println("Page Is loaded.");
				return; 
		  } 
		  
		  //This loop will rotate for 25 times to check If page Is ready after every 1 second.
		  for (int i=0; i<25; i++){ 
			  try {
				  Thread.sleep(1000);
			  }catch (InterruptedException e) {} 
		//To check page ready state.
		  if (js.executeScript("return document.readyState").toString().equals("complete")){ 
				  break; 
			  }   
		  }
		}
*/

		int i = 0;
		do {
			if (js.executeScript("return document.readyState").toString().equals("complete")){ 
				   System.out.println("Page Is loaded.");
				   return; 
			}
			try {
				Thread.sleep(250);
			}catch (InterruptedException e) {} 
			i++;
			System.out.println(i);
		} while (i < 25);
	
	}
	
}



