package cl.template.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import cl.bice.robotpro.configuration.Env;
import cl.bice.robotpro.driver.DriverFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseConfig {

	public WebDriver driver;
	public Env env;
	public DriverFactory factory;
	private static String result = System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "allure-results";
	
	//private static final String HOST_ALLRE = "allure.properties";


	@BeforeMethod(alwaysRun = true)
	public void beforeMethodSetup(Method method) throws Exception {
		factory = new DriverFactory();
		env = new Env();
		driver = factory.getWebDriver();
		try {
			driver.manage().deleteAllCookies();
			driver.get(env.getBaseURL());

		} catch (Exception e) {

		}
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethodSetup(ITestResult result) {
		try {
			driver.close();
			driver.quit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@BeforeSuite
	public void beforeSuiteSetup() {
		try {
			String sCarpAct = result;
			File carpeta = new File(sCarpAct);
			FileUtils.cleanDirectory(carpeta);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@AfterSuite
	public void afterSuiteSetup() {
		try {
			String sCarpAct = result;
			String filePath;
			File carpeta = new File(sCarpAct);

			String[] listado = carpeta.list();
			if (listado == null || listado.length == 0) {
				System.out.println("No hay elementos dentro de la carpeta actual");
				return;
			} else {
				String message;
				RestAssured.baseURI = "http://172.16.43.248:5050";
				RequestSpecification request = RestAssured.given();

				JSONObject json = new JSONObject();
				JSONArray array = new JSONArray();

				for (int i = 0; i < listado.length; i++) {
					filePath = result + System.getProperty("file.separator") + listado[i];
					File file = new File(filePath);
					System.out.println(listado[i]);

					JSONObject item = new JSONObject();
					item.put("file_name", listado[i]);
					item.put("content_base64", encodeBase64(file));
					array.add(item);
				}

				json.put("results", array);
				message = json.toString();

				request.header("Content-Type", "application/json");
				request.body(json);

				Response response = request
						.post("/allure-docker-service/send-results?project_id=template&force_project_creation=true");
				int statusCode = response.getStatusCode();
				Assert.assertEquals(statusCode, 200);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String encodeBase64(File file) {
		byte[] encodedBytes = Base64.getEncoder().encode(convertFileToByteArray(file));
		return new String(encodedBytes);
	}

	private static byte[] convertFileToByteArray(File file) {
		FileInputStream fis = null;
		byte[] bArray = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(bArray);
			fis.close();
		} catch (IOException ioExp) {
			ioExp.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bArray;
	}
}
