package pageObjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Base.DriverInitializer;

public class Web_Stories extends DriverInitializer {

	WebDriver driver;

	public Web_Stories(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@target='_blank']/section/div")
	public List<WebElement> Webstories;

	@FindBy(xpath = "//*[@class='i-amphtml-glass-pane']")
	public WebElement AddButton;

	@FindBy(xpath = "//button[@aria-label='Next page']")
	public WebElement NextButton;

	@FindBy(xpath = "//button[@aria-label='Next page']")
	public WebElement ReplayButton;

	@FindBy(xpath = "(//*[@class='letterbox']/p)")
	public List<WebElement> WebstoriesDescription;

	public void Webstory1() throws InterruptedException {

		String title = p.getProperty("WebStoryTitle");
		Assert.assertTrue(driver.getTitle().contains(title));
		String Parentwindow = driver.getWindowHandle();
		Assert.assertTrue(driver.getWindowHandles().size() == 1);

		Webstories.get(3).click();
		Thread.sleep(4000);

		for (String window : driver.getWindowHandles()) {
			if (!Parentwindow.contentEquals(window)) {
				driver.switchTo().window(window);
				break;
			}
		}

		     printTxt_From_EachPage_in_Webstories();
		     driver.close();
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("\n");
		driver.switchTo().window(Parentwindow);
		Thread.sleep(3000);
		Webstories.get(4).click();
		for (String window : driver.getWindowHandles()) {
			if ( !Parentwindow.contentEquals(window) ) {
				driver.switchTo().window(window);
				break;
			}
		}
		Thread.sleep(3000);
		printTxt_From_EachPage_in_Webstories();
		driver.close();
	}

	public void printTxt_From_EachPage_in_Webstories() throws InterruptedException {
		Actions action = new Actions(driver);
		boolean ADD_intercepted_once = false ;
		int maximum_iterations = 10 ;
		int x = 1 ;
		do {
			
			Thread.sleep(3000);
			WebElement Header = driver.findElement(By.tagName("*"));
			WebElement Paragraph = driver.findElement(By.tagName("*"));			

			 System.out.println("\nStory Page : "+ x + " :-");
			 Thread.sleep(3000);			
				
				try {
					Header = driver.findElement(By.xpath("(//*[@class='letterbox']/h1)[" + x + "]"));
				} catch (Exception e) {}
				
				if(Header.getText() == null || Header.getText() == "" )
				{
				try {
						Header = driver.findElement(By.xpath("(//*[@class='letterbox']/h2)[" + x + "]"));
					} catch (Exception e1) {}
				}
				
				try {
					Paragraph = driver.findElement(By.xpath("(//*[@class='letterbox']/p)[" + x + "]"));
				} catch (Exception e) {}
				 
				
				if ( Header == driver.findElement(By.tagName("*"))   || Paragraph.getText() == "" )
				  System.out.println("\nHeader : " + " BLANK ");
				else 
				  System.out.println("\nHeader : " + Header.getText());
				
				if ( Paragraph == driver.findElement(By.tagName("*")) || Paragraph.getText() == "")
				  System.out.println("Paragraph : " + "BLANK");
				else 
					System.out.println("Paragraph : " + Paragraph.getText());
				
				
				try {
						action.moveToElement(NextButton).click().build().perform();
						boolean ADD_is_Displayed = false ;
						
						try {
							if(AddButton.isDisplayed())
								ADD_is_Displayed = true	;
						} catch (Exception e1) {}
						
							if (ADD_is_Displayed && !ADD_intercepted_once )
							{
								ADD_intercepted_once = true ;
								
								System.out.println("\n************************  ADD intercepted ******************");
								x = x+1 ;
								maximum_iterations++ ;
								try 
								{action.moveToElement(NextButton).click()
									                        .build().perform();
								} catch (Exception e) {}
								
								Thread.sleep(3000);
								continue ;
							} 
							else {Thread.sleep(3000);}
							
				   }
				catch (InterruptedException e) { System.out.println("You are in Last Page of the WebStories"); }
			
				x= x+1;
		}while(x < maximum_iterations);
		
		
	} // Method closed
	
}
