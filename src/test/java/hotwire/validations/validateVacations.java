package hotwire.validations;

import org.openqa.selenium.NoSuchElementException;

public class validateVacations {
	
	public boolean validateSearchResult(int count) {		
		try {
			return count>0;
			}
			catch(NoSuchElementException e) {
				return false;
			}
	}
}
