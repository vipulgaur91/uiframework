package pageObjects;

import automationTests.TestInitiator;
import io.restassured.response.Response;

public class GetWeatherByCity {
	static String GET_BY_CITY = "/data/2.5/weather?q=Delhi&appid=7fe67bf08c80ded756e598d6f8fedaea";
	/**
	 * execute GET API
	 */
	public static Response getByCity() {
		return TestInitiator.apiSpec.when().get(GET_BY_CITY);
	}
}
