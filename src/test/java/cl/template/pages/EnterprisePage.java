package cl.template.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EnterprisePage extends BasePage {

	public EnterprisePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "selectEnterprise")
	private WebElement selectEnterpriseList;

	public boolean validateLogin() {
		try {
			if(selectEnterpriseList.isDisplayed()) {
				return true;
			} else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
