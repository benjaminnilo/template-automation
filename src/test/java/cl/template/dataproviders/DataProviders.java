package cl.template.dataproviders;

import java.util.ArrayList;
import org.testng.annotations.DataProvider;
import com.google.gson.Gson;

import cl.bice.robotpro.dataprovider.FileDataProvider;

public class DataProviders {

	public static ArrayList<Object> getGsonEnvironment() {
		Gson gson = new Gson();
		String env = System.getProperty("env");
		if (env == null)
			env = "qa";
		ArrayList<Object> arrayList = new ArrayList<Object>();
		arrayList.add(gson);
		arrayList.add(env);
		return arrayList;
	}

	public static Object matchJsonAndEntity(String clase, String nameJson)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Object instancia = Class.forName(clase).newInstance();
		instancia = ((Gson) getGsonEnvironment().get(0)).fromJson(
				FileDataProvider.asString(
						String.format("src/test/resources/data/%s/" + nameJson + ".json", getGsonEnvironment().get(1))),
				instancia.getClass());
		return instancia;
	}

	@DataProvider
	public static Object[][] user() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		final String USER = "cl.template.entities.User";
		Object user = matchJsonAndEntity(USER, "user");
		return new Object[][] { { user } };
	}
}
