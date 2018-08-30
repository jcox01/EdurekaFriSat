package day4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class Indigo {

	public static void main(String[] args) {
		
		Indigo go = new Indigo();
		
		go.executeGoIndigo("chrome");
		
		go.executeGoIndigo("firefox");

	}

	public void executeGoIndigo(String browserType) {
		
		WebDriver driver = null;
		
		String fromAP = "BLR";
		String toAP = "LKO";
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

	//choose one way
		WebElement oneWay = driver.findElement(By.xpath(
				"//div[@id='bookingflightTab']//li[@class='current']/a"));
		oneWay.click();
		
	// detect and close notice
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[@id='globalModal']//button[@class='close']")));
		
		WebElement noticeRT = driver.findElement(By.xpath(
				"//div[@id='globalModal']//button[@class='close']"));

		noticeRT.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[@id='oneWay']//input[@class='origins-value city-name-value']")));

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
					e.printStackTrace();
		}
	//click origin box
		WebElement chooseOrigin = driver.findElement(By.xpath(
				"//*[@id='oneWay']//input[@class='origins-value city-name-value']"));
		chooseOrigin.click();
		
	//choose origin city
		String cityOrigion = String.format(
				"//div[@id='oneWay']//a[contains(text(),'%s')]", fromAP);
		
		driver.findElement(By.xpath(cityOrigion)).click();
		
	//choose destination city
		String cityDestination = String.format(
				"(//div[@id='oneWay']//a[contains(text(),'%s')])[2]", toAP);
		
		driver.findElement(By.xpath(cityDestination)).click();
		
	//choose passenger counts
		WebElement adultSel = driver.findElement(By.xpath(
				"//select[@name='indiGoOneWaySearch.PassengerCounts[0].Count']"));		
		Select adultNum = new Select(adultSel); 
		adultNum.selectByVisibleText(adultCount);
		
		WebElement childSel = driver.findElement(By.xpath(
				"//select[@name='indiGoOneWaySearch.PassengerCounts[1].Count']"));
		Select childNum = new Select(childSel);
		childNum.selectByVisibleText(childCount);
		
		WebElement infantSel = driver.findElement(By.xpath(
				"//select[@name='indiGoOneWaySearch.InfantCount']"));
		Select infantNum = new Select(infantSel);
		infantNum.selectByVisibleText(infantCount);
		
	// departure date can span months on the calendar	
		String departureDate = tomorrowsDate();
		
		selectDepartDate(driver, departureDate);

	//submit selection
		WebElement submit = driver.findElement(By.xpath(
				"//form[@class='flight-booking-way one-way-form']//button[@type='submit']"));	
		
		submit.click();
		
	//wait for page to load
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//div[@id='bw-color-id']//span[@class='oneWay-farecap_price']//span[contains(text(),',')]")));
	
		driver.close();
	}
	
	public static String tomorrowsDate() {
		Date date = new Date();
		long date1 = (date.getTime()) + (1000 * 60 * 60 * 24 * 14);
		return dayFormat(date1);
	}

	public static String dayFormat(long date1) {
		DateFormat dateFormat = new SimpleDateFormat("dd");	
		String date2 = dateFormat.format(date1);
		if((Integer.parseInt(date2)) < 10) {	
			
			System.out.println("single character date2 is : " + Integer.parseInt(date2));
			System.out.println("multiple character date2 is : " + date2);
			return String.valueOf(Integer.parseInt(date2));
		}
		else {
			return date2;
		}
	}

	public static String todaysDate() {
		Date date = new Date();
		long date1 = (date.getTime());
		return dayFormat(date1);
	}
	
	public static void selectDepartDate(WebDriver driver, String dateDepart) {
		
		driver.findElement(By.xpath(
				"//input[@name='indiGoOneWaySearch.DepartureDate']")).click();
		
		String dateToday = todaysDate();
		
		if ((Integer.parseInt(dateDepart)) < (Integer.parseInt(dateToday))) {
			
			String monthDay = String.format(
					"(//table[@class='ui-datepicker-calendar'])[2]//tr/td/a[text()='%s']", dateDepart);
			driver.findElement(By.xpath(monthDay)).click();
		}
		else {
			String monthDay = String.format(
					"(//table[@class='ui-datepicker-calendar'])[1]//tr/td/a[text()='%s']", dateDepart);
			driver.findElement(By.xpath(monthDay)).click();
		}
	
	}
}