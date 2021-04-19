package cl.template.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		 super(driver);
	}

	@FindBy(id = "inputRut")
	private WebElement rutInp;

	@FindBy(id = "inputPassword")
	private WebElement claveInp;

	@FindBy(id = "entrar")
	private WebElement ingresarBtn;
	
	@FindBy(name = "q")
	private WebElement buscadorInp;
	
	@FindBy(name = "btnK")
	private WebElement btnK;
	

	public EnterprisePage login(String rut, String clave) throws InterruptedException {
		rutInp.sendKeys(rut);
		claveInp.sendKeys(clave);

		ingresarBtn.click();
		
		return new EnterprisePage(driver);
	}
}
