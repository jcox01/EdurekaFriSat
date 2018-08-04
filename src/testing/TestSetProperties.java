package testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSetProperties {

	public static void main(String[] args) {

		System.setProperty("WebDriver.chrome.driver", "C:\\Users\\John\\eclipse-workspace\\Selenium\\exe\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("URL");
		driver.getWindowHandle();
		
		driver.navigate().to("URL");
	}
	
	

}
