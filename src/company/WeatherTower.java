package company;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getWeatherProvider().getCurrentWeather(coordinates);
    }

    void changeWeather() {
        super.conditionsChanged();
    }

    @Override
    public void register(Flyable flyable) {
        super.register(flyable);
    }
}
