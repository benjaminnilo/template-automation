package cl.template.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import cl.template.dataproviders.DataProviders;
import cl.template.entities.User;
import cl.template.pages.EnterprisePage;
import cl.template.pages.LoginPage;

public class TestSelenium extends BaseConfig {
	
	@Test(dataProvider = "user", dataProviderClass = DataProviders.class, description = "Test 001")
	  public void test001(User user) throws InterruptedException {
		
		LoginPage loginPage = new LoginPage(driver);
		EnterprisePage  enterprisePage= loginPage.login(user.getRut(), user.getPassword());
		
	    Assert.assertTrue(enterprisePage.validateLogin(), "Login incorrecto");
	  }
}