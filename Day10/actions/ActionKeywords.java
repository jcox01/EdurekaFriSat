package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ActionKeywords {

	static WebDriver driver;
	
	public static void openBrowser() {
		
		System.setProperty("Webdriver.chrome.driver", "C:\\Users\\John\\eclipse-workspace\\Libs\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		
	}
	
	public static void navigate() {
		
		driver.navigate().to("http://www.google.com");
		
		
	}
	
	public static void click_Gmail() {
		
		driver.findElement(By.linkText("Gmail")).click();
		
		
	}
	
}
