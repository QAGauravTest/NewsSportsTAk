package Base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class NavigateToURL extends DriverInitializer {
	static String SiteURL;

	
	public static void UrlNavigation() throws IOException {
		
		
		
		driver=Initializer();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		SiteURL = p.getProperty("News_URL");
		driver.get(SiteURL);
		driver.manage().window().maximize();
		
	}


	
}
