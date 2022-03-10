package company;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private final String[] weatherType = {"RAIN", "FOG", "SUN", "SNOW"};

    public static WeatherProvider getWeatherProvider() {
        if (weatherProvider == null)
            weatherProvider = new WeatherProvider();
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int index = (coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude()) % 4;
        return weatherType[index];
    }
}
