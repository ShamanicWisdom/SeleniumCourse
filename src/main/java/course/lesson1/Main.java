/*
 * Selenium Course - lesson one. 
 * Lesson scope:
 * 		- Selenium project configuration,
 * 		- Basic information how Selenium works + some curios,
 * 		- First automated scripts,
*/
package course.lesson1;

import java.time.Duration;							//Used by Selenium's Implicity Wait (starting from Selenium 4.0+).
import java.util.List;

import org.openqa.selenium.By; 						//By object is containing variety of selectors for proper finding a desired WebElement.
import org.openqa.selenium.Dimension;				//Dimension object is used for passing dimensions of web browser.
import org.openqa.selenium.Keys;					//Keys object is used for passing certain keyboard keys (see: typing youtube + Keys.ENTER).
import org.openqa.selenium.Point;					//Point object is used for browser's precise alignment (by passing coordinates of top-left edge of browser)
import org.openqa.selenium.WebDriver;				//WebDriver object - Most important object of Selenium framework. Responsible for overall work of automation (starting and handling the whole browser).
import org.openqa.selenium.WebElement;				//WebElement object - important part of Selenium framework. Every element of the page can be assigned and considered as a WebElement (button, label, field, table etc)...
import org.openqa.selenium.chrome.ChromeDriver;		//ChromeDriver is one of specified versions of WebDriver object. Designed for Google Chrome browser.
import org.openqa.selenium.chrome.ChromeOptions;	//ChromeOptions object - support object for specifying and configuring a WebDriver (adding/deleting cookies, turning headless mode on/off, enabling default protocols and behaviors - example: how to handle PDF files - open or direct download when spotted).

public class Main {
	public static void main(String[] args) {
		System.out.println("Selenium Course - Lesson 1 - basic, installing.");
		
		String currentChromeVerion = "chromedriver104.exe";
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/" + currentChromeVerion); //Creating a new environmental variable - driver's requirement - necessary step.
		
		ChromeOptions options = new ChromeOptions(); 
		options.setHeadless(true); //adding an option to run the driver on headless mode - (without GUI)
				
		WebDriver driver1 = new ChromeDriver(options);
		WebDriver driver2 = new ChromeDriver(options);
				
		driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); //Implicity Wait - waiting up for 3 seconds for a site to load before continuing. 
		driver2.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver1.manage().window().setSize(new Dimension(900, 900)); //setting up the size and position for each of opened browser instance.
		driver1.manage().window().setPosition(new Point(0, 0));
		
		driver2.manage().window().setSize(new Dimension(900, 900));
		driver2.manage().window().setPosition(new Point(1000, 0));
		
		//driver1.manage().window().maximize(); //maximizing the browser's window.
		
		driver1.get("https://www.google.com"); //Proceeding to Google site
		
		driver2.navigate().to("https://www.komixxy.pl"); //Proceeding to meme site.
		
		WebElement randomizeButton = driver2.findElement(By.xpath("//*[@id=\"m2\"]/a")); //finding a 'Losuj' (randomize) button.
				
		randomizeButton.click();
		
		System.out.println("Komixxy - tytul: " + driver2.getTitle());
				
		WebElement rejectButton = driver1.findElement(By.cssSelector("#W0wltc")); //Finding the reject button of the Google form
		
		rejectButton.click();
				
		WebElement searchField = driver1.findElement(By.className("gLFyf"));
		
		searchField.click();
		
		searchField.clear(); //Clearing the field from unexpected input (good practice + doublecheck)
		
		searchField.sendKeys("youtube" + Keys.ENTER); //Typing a desired phrase and then pressing an Enter button.
				
		List<WebElement> youtubeElement = driver1.findElements(By.partialLinkText("YouTube")); //How many 'YouTube' phrases is present on the first site of Google search engine?
		
		System.out.println("Youtube elements: " + youtubeElement.size()); 
		
		WebElement youtubeLink = driver1.findElement(By.partialLinkText("youtube.com")); //finding a 'youtube.com' link and clicking.
		
		youtubeLink.click();
		
		System.out.println("Current URL: [" + driver1.getCurrentUrl() + "] Title: [" + driver1.getTitle() + "]"); //Get the current URL from the place where you are at this moment.
		
		if(driver1.getTitle().equalsIgnoreCase("Zanim przejdziesz do YouTube")) //Ew... another cookies form. Going back!
			driver1.navigate().back(); 
		
		driver2.navigate().refresh(); //Casually refreshed a meme site.
		
		driver1.quit(); 	//Closing both browser instance and driver process.
		//driver1.close(); 	//Slight different behavior - just closing a browser, driver process will be still up and running!
		driver2.quit();		
	}
}
