package pageObjects;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class CricketTab {
	WebDriver driver;
	WebDriverWait wait ;
	public CricketTab(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy( xpath= "(//h2[contains(@class,'MuiTypography')]/parent::a)" )
	public List<WebElement> Home_Page_ALL_URLs;
	
	@FindBy( xpath ="((//div[@role='tablist'])[2]/button)[3]")
	public WebElement ThirdPage;
	
	@FindBy( xpath ="//span[text()='Back']")
	public WebElement BackButton;
	
	
	public void validate_BrokenLink() throws Throwable
	{

	SoftAssert a = new SoftAssert();
	for (WebElement link : Home_Page_ALL_URLs)
	{
		
		String url = link.getAttribute("href");
		String Parentwindow=driver.getWindowHandle();
		Actions a1 = new Actions(driver);
		a1.keyDown(Keys.CONTROL).moveToElement(link).click().keyUp(Keys.CONTROL).build().perform();
		Thread.sleep(1000);
		for(String window: driver.getWindowHandles())
		{
			if(!Parentwindow.contentEquals(window))
			{
				driver.switchTo().window(window);
				Thread.sleep(1000);
				break;
			}
		}
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setRequestMethod("HEAD");
		conn.connect();
		int respCode = conn.getResponseCode();
		System.out.println(respCode);
		a.assertTrue(respCode < 400, "The link with Text" +  url + " is broken with code" + respCode);
		driver.close();
		driver.switchTo().window(Parentwindow);
	}
	a.assertAll();
	}

}