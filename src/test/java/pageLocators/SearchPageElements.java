package pageLocators;

public interface SearchPageElements {
	String searchPageTitle = "Delhi, Delhi, India Today, Tonight & Tomorrow's Weather Forecast | AccuWeather";
	String searchBar = "div.searchbar-content";
	String currentPanel = "div[class^='cur-con-weather'] h2";
	String currentWeather = "CURRENT WEATHER";
	String temp = "//div[starts-with(@class, 'cur-con-weather')]/div[@class='forecast-container']//div[@class='temp']";
}
