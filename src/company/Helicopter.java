package company;

public class Helicopter extends Aircraft implements Flyable{
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateCondition() {
        String weather = weatherTower.getWeather(coordinates);
        switch(weather) {
            case("SNOW"):
                WriterToFile.writeToFile("Helicopter#" + getName() + "(" + getId() + ")"
                                            + ":My rotor is going to freeze.\n");
                changeCoordinates(0, 0, -12);
                break;
            case("FOG"):
                WriterToFile.writeToFile("Helicopter#" + getName() + "(" + getId() + ")"
                        + ":I am not looking at anything.\n");
                changeCoordinates(1, 0, 0);
                break;
            case("SUN"):
                WriterToFile.writeToFile("Helicopter#" + getName() + "(" + getId() + ")"
                        + ":Great weather! Flight is a pleasure.\n");
                changeCoordinates(10, 0, 2);
                break;
            case("RAIN"):
                WriterToFile.writeToFile("Helicopter#" + getName() + "(" + getId() + ")"
                        + ":Fucking rain it is hard to fly.\n");
                changeCoordinates(5, 0, 0);
                break;
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        WriterToFile.writeToFile("Tower says: Helicopter#" + this.getName() + "(" + this.getId()
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
            height = 0;
            this.weatherTower.unregister(this);
            WriterToFile.writeToFile("Helicopter#" + this.name + "(" + this.id + ") landing. Longitude = " +
                                                longitude + ", Latitude = " + latitude + ", Height = " + height + ".\n");
            WriterToFile.writeToFile("Tower says: Helicopter#" + this.name + "(" + this.id +
                                                ") unregistered from weather tower.\n");
        }
        if (height > 100)
            height = 100;

        this.coordinates.setLongitude(longitude);
        this.coordinates.setLatitude(latitude);
        this.coordinates.setHeight(height);
    }
}
