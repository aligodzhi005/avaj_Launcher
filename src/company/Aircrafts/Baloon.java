package company.Aircrafts;

import company.WeatherTower;
import company.WriterToFile;

public class Baloon extends Aircraft implements Flyable{
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateCondition() {
        String weather = weatherTower.getWeather(coordinates);
        switch(weather) {
            case("SNOW"):
                WriterToFile.writeToFile("Baloon#" + getName() + "(" + getId() + ")"
                                            + ":My rotor is going to freeze.\n");
                break;
            case("FOG"):
                WriterToFile.writeToFile("Baloon#" + getName() + "(" + getId() + ")"
                        + ":I am not looking at anything.\n");
                break;
            case("SUN"):
                WriterToFile.writeToFile("Baloon#" + getName() + "(" + getId() + ")"
                        + ":Great weather! Flight is a pleasure.\n");
                break;
            case("RAIN"):
                WriterToFile.writeToFile("Baloon#" + getName() + "(" + getId() + ")"
                        + ":Fucking rain it is hard to fly.\n");
                break;
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        WriterToFile.writeToFile("Tower says: Baloon#" + this.getName() + "(" + this.getId()
                                    + ")" + this.getId() + "registered to weather tower.\n");
    }

    @Override
    public void changeCoordinates(int mathDifLongitude, int mathDifLatitude, int mathDifHeight) {
        int longitude = this.coordinates.getLongitude();
        int latitude = this.coordinates.getLatitude();
        int height = this.coordinates.getHeight();

        longitude += mathDifLongitude;
        latitude += mathDifLatitude;
        height += mathDifHeight;

        if (height < 0) {
            this.weatherTower.unregister(this);
            WriterToFile.writeToFile("Baloon#" + this.name + "(" + this.id + ") landing. Longitude = " +
                                                longitude + ", Latitude = " + latitude + ".");
            WriterToFile.writeToFile("Tower says: Baloon#" + this.name + "(" + this.id +
                                                ") unregistered from weather tower.");
            height = 0;
        }
        if (height > 100)
            height = 100;

        this.coordinates.setLongitude(longitude);
        this.coordinates.setLatitude(latitude);
        this.coordinates.setHeight(height);
    }
}
