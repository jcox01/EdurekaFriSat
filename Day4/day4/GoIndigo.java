package day4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class GoIndigo {

	WebDriver driver;
	
	public void executeGoIndigo(String browserType) {
		
		String cityFrom = "BLR";
		String cityTo = "LKO";
		String adultCount = "3";
		String childCount = "2";	
		String infantCount = "0";

		if (browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\John\\eclipse-workspace\\Libs\\chromedriver_win32\\chromedriver.exe");
			// /n -- new line, /t-- tab
			driver = new ChromeDriver();

		} else if (browserType.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", 
					"C:/Users/John/eclipse-workspace/Libs/geckodriver-v0.21.0-win64/geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserType.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					"C:/Users/John/eclipse-workspace/Libs/MicrosoftWebDriver.exe");
	// /n -- new line, /t-- tab
			driver = new EdgeDriver();

		} else {
			System.err.println("Invalid Browser");
		}
		
		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("https://www.goindigo.in/?linkNav=home_header");
		
		WebDriverWait wait = new WebDriverWait(driver, 10);

		WebElement originElement = driver.findElement(By.xpath
				("//*[@id='oneWay']//input[@class='origins-value city-name-value']"));

	//	WebElement testDelay;
		originElement.click();
		
		String departCity = String.format
				("//*[@id='oneWay']//a[contains(text(),'%s')]", cityFrom);
		
		driver.findElement(By.xpath(departCity)).click();

		String arriveCity = String.format
				("(//div[@id='oneWay']//a[contains(text(),'%s')])[2]", cityTo);
		driver.findElement(By.xpath(arriveCity)).click();

	// Select Passenger count
		WebElement adultDropDown = driver.findElement(By.xpath
				("//select[@name='indiGoOneWaySearch.PassengerCounts[0].Count']"));
		Select adultNum = new Select(adultDropDown);
		adultNum.selectByVisibleText(adultCount);
	
		WebElement childDropDown = driver.findElement(By.xpath
				("//select[@name='indiGoOneWaySearch.PassengerCounts[1].Count']"));
		Select childNum = new Select(childDropDown);
		childNum.selectByVisibleText(childCount);
		
		WebElement infantDropDown = driver.findElement(By.xpath
				("//select[@name='indiGoOneWaySearch.InfantCount']"));
		Select infantNum = new Select(infantDropDown);
		infantNum.selectByVisibleText(infantCount);
				
				
	// Select todays date (due to time difference between me and the site, I am using tomorrows date
		String today = todaysDate();
		searchAndClick(driver, today);
		
		driver.findElement(By.xpath
				("//div[@id='oneWay']//button[@type='submit']")).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath
				("//*[@id='bw-color-id']//span[@class='oneWay-farecap_price']//span[contains(text(), ',')]")));
		
		driver.quit();
		
	}

	
	public static void searchAndClick(WebDriver driver, String departDate) {
		
		driver.findElement(By.xpath(
				"//*[@id='oneWay']//label[@class='home_label']")).click();
		
		List<WebElement> dates = driver.findElements(By.xpath(
				"(//table[@class='ui-datepicker-calendar'])[1]//tr/td"));
		
		int totalNodes = dates.size();
		for(int i=0; i<=totalNodes; i++) {
			
			String foundDate = dates.get(i).getText();
			if (foundDate.equals(departDate)){
				dates.get(i).click();
				break;
			}
			
			
		}
		
	}

	
	public static void printDate(){
		 
		 // Create object of SimpleDateFormat class and decide the format
		 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:ms");
		 
		 //get current date time with Date()
		 Date date = new Date();
		 
		 // Now format the date
		//	 String date1= dateFormat.format(date);
		 long date1= (date.getTime());
		 
		 // Print the Date
		 System.out.println("Date/time in miliseconds " +date1);
	 
	}
	
	public static String todaysDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		long date1 = (date.getTime()) + (1000 * 60 * 60 * 24);
		String dateToday = dateFormat.format(date1);
		return dateToday;
				
	}

	
	
	
	
}
