package genericUtilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
	public WebDriver driver;
	public WebDriverWait wait;
	String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

	@Parameters("browser")
	@BeforeClass
	public void LaunchBrowser(@Optional("chrome") String browser) throws InterruptedException {
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();

			driver.get(url);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get(url);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		} else {
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get(url);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		}

	}

	@BeforeMethod // this method is used to login
	public void login() {
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");

		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	@AfterMethod //it is used to logout
	public void logout() throws InterruptedException
	{
		wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		
		
//	WebElement p_drpdown = driver.findElement(By.xpath("oxd-userdropdown-tab"));
		
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']"))).click();
		//Thread.sleep(3000);
		WebElement logout = driver.findElement(By.xpath("//a[@class='oxd-userdropdown-link' and contains(text(),'Logout')]"));
		wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
		
	}
	
	@AfterClass
	public void TearDown() throws InterruptedException {
		Thread.sleep(4000);
		driver.quit();
	}


}
