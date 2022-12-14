package Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class DriverInitializer {
	
	public static WebDriver driver;
	public static Properties p;
	static FileInputStream fis;
	
	public static String current_working_directory ;

	public static WebDriver Initializer() throws IOException {

		
		p = new Properties();
	   current_working_directory = System.getProperty("user.dir");
		
		fis = new FileInputStream(current_working_directory+
				"\\src\\main\\java\\config.properties");
		p.load(fis);
		System.setProperty("webdriver.chrome.driver", current_working_directory + "\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		
	    return driver;
	}

}
