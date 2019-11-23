package hotwire.pages;

import org.openqa.selenium.By;

import hotwire.util.BaseTest;
import hotwire.util.DriverManager;

public class SearchResultPage extends BaseTest {
	
	private By resultsGrid = By.xpath("//*[@id='resultsContainer']/section/article");
	
	
	
	public int countofresultGrid() {
		//wait for the result to be loaded
		getElement(resultsGrid, 30);
		//return the count of results
		return DriverManager.getDriver().findElements(resultsGrid).size();
	}
}
