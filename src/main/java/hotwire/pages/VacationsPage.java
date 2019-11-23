package hotwire.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import hotwire.util.BaseTest;

public class VacationsPage extends BaseTest {
	
	//Selection elements for Vacation Package
	private By chk_Flight = By.xpath("//button[text()='Flight']");
	private By chk_Hotel = By.xpath("//button[text()='Hotel']");
	private By chk_Car = By.xpath("//button[text()='Car']");
	
	//Elements for Entering Origin and Destination and dates
	private By txt_Origin = By.id("farefinder-package-origin-location-input");
	private By txt_Dest = By.id("farefinder-package-destination-location-input");
	
	private By dt_StartDate = By.id("farefinder-package-startdate-input");
	private By dt_EndDate = By.id("farefinder-package-enddate-input");
	
	private By drp_Pickuptime = By.id("farefinder-package-pickuptime-input");
	private By drp_Droptime = By.id("farefinder-package-dropofftime-input");
	
	//Search button
	private By btn_Search = By.id("farefinder-package-search-button");
	
	private By lbl_loading = By.id("interstitial-message");
	
	public void selectPackage(String options) {
		
		if(options.equals("all")) {
			if(!getElement(chk_Flight, 5).getAttribute("class").contains("active")) {
				getElement(chk_Flight, 5).click();
			}
			if(!getElement(chk_Hotel, 5).getAttribute("class").contains("active")) {
				getElement(chk_Hotel, 5).click();
			}
			if(!getElement(chk_Car, 5).getAttribute("class").contains("active")) {
				getElement(chk_Car, 5).click();
			}
		}
	}
	
	public void enterLocations(String origin, String dest) {
		getElement(txt_Origin, 5).sendKeys(origin);
		getElement(By.xpath("//a[contains(@title,'"+origin+"')]"), 5).click();
		
		getElement(txt_Dest, 5).sendKeys(dest);
		getElement(By.xpath("//a[contains(@title,'"+dest+"')]"), 5).click();
	}
	
	public void selectDates(String strtDate, String endDate) {
		getElement(dt_StartDate, 5).clear();
		getElement(dt_StartDate, 5).sendKeys(strtDate);
		getElement(dt_EndDate, 5).clear();
		getElement(dt_EndDate, 5).sendKeys(endDate);
		
	}
	
	public void selectTime(String depTime, String retTime) {
		Select s = new Select(getElement(drp_Pickuptime, 5));
		s.selectByVisibleText(depTime);
		s = new Select(getElement(drp_Droptime, 5));
		s.selectByVisibleText(retTime);
				
	}
	
	public void submitPage() {
		getElement(btn_Search, 5).click();
		getElement(lbl_loading, 10);
	}
}
