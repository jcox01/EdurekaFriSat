package day3;

import java.util.concurrent.TimeUnit;		

import org.openqa.selenium.JavascriptExecutor;		
import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.firefox.FirefoxDriver;		
import org.testng.annotations.Test;		
    		
public class JavaSE_Test {				

    @Test		
    public void Login() 					
    {		
		System.setProperty("webdriver.gecko.driver",
				"C:/Users/John/eclipse-workspace/Libs/geckodriver-v0.20.0-win64/geckodriver.exe");
		   	
        WebDriver driver= new FirefoxDriver();			

        //Creating the JavascriptExecutor interface object by Type casting		
        JavascriptExecutor js = (JavascriptExecutor)driver;		
        		
        //Launching the Site.		
        driver.get("http://demo.guru99.com/V4/");			
     
          //Maximize window		
          driver.manage().window().maximize();		
        		
          //Set the Script Timeout to 20 seconds		
          driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);			
             
          //Declare and set the start time		
          long start_time = System.currentTimeMillis();			
                   
          //Call executeAsyncScript() method to wait for 5 seconds		
          js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");			
          		
         //Get the difference (currentTime - startTime)  of times.		
         System.out.println("Passed time: " + (System.currentTimeMillis() - start_time));					
                    		
    }		
}			