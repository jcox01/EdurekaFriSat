package day3;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class logonFaceBook {

	public static void main(String[] args) throws Throwable{
		
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/John/eclipse-workspace/Libs/chromedriver_win32/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
//		String userProfile = "C:/Users/John/Chrome Profile/Profile 1";
		String userProfile = "C:/Users/John/AppData/Local/Google/Chrome/User Data/Profile 1";
		options.addArguments("user-data-dir="+userProfile);
		options.addArguments("--start-maximized");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();	
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		WebDriver driver = new ChromeDriver(capabilities);
		
		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.get("https://www.facebook.com");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("2017879715");
		
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Pt109FFaceBBook");
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//input[@value='Log In' and @type='submit']")).click();
		driver.findElement(By.xpath("//div[@id='userNavigationLabel']")).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Log Out')]")).click();
		
		driver.close();
		
	}

}
