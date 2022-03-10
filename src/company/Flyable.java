package company;

public interface Flyable {
    void updateCondition();
    void registerTower(WeatherTower weatherTower);
    void changeCoordinates(int longitude, int latitude, int height);
}
