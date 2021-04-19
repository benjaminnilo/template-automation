package cl.template.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import cl.template.dataproviders.DataProviders;
import cl.template.entities.User;
import cl.template.pages.EnterprisePage;
import cl.template.pages.LoginPage;

public class TestSelenium2 extends BaseConfig {
	
	@Test(dataProvider = "user", dataProviderClass = DataProviders.class, description = "Test 002")
	  public void test001(User user) throws InterruptedException {
		
		LoginPage loginPage = new LoginPage(driver);
		EnterprisePage  enterprisePage= loginPage.login(user.getRut(), user.getPassword());
		
	    Assert.assertFalse(enterprisePage.validateLogin(), "Login incorrecto falso positivo 1");
	  }
}