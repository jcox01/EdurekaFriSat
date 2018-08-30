package Grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class testGrid {

	public static void main(String[] args) throws MalformedURLException {
		
		//Initialize DesiredCapabilities
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		
		//set platform Name
		cap.setPlatform(Platform.WINDOWS);
		
		URL nodeURL = new URL("http://192.168.1.3:5566/wd/hub");
		
		WebDriver driver = new RemoteWebDriver(nodeURL, cap);
		
		driver.get("http://www.google.com");
		
		System.out.println("Page Title is: " + driver.getTitle());
		
		
		
		
	}

}
