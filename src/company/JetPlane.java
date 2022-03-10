package company;

public class JetPlane extends Aircraft implements Flyable{
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateCondition() {
        String weather = weatherTower.getWeather(coordinates);
        switch(weather) {
            case("SNOW"):
                WriterToFile.writeToFile("JetPlane#" + getName() + "(" + getId() + ")"
                                            + ":My rotor is going to freeze.\n");
                changeCoordinates(0, 0, 7);
                break;
            case("FOG"):
                WriterToFile.writeToFile("JetPlane#" + getName() + "(" + getId() + ")"
                        + ":I am not looking at anything.\n");
                changeCoordinates(0, 1, 0);
                break;
            case("SUN"):
                WriterToFile.writeToFile("JetPlane#" + getName() + "(" + getId() + ")"
                        + ":Great weather! Flight is a pleasure.\n");
                changeCoordinates(0, 10, 2);
                break;
            case("RAIN"):
                WriterToFile.writeToFile("JetPlane#" + getName() + "(" + getId() + ")"
                        + ":Fucking rain it is hard to fly.\n");
                changeCoordinates(0, 1, 0);
                break;
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        WriterToFile.writeToFile("Tower says: JetPlane#" + this.getName() + "(" + this.getId()
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
            WriterToFile.writeToFile("JetPlane#" + this.name + "(" + this.id + ") landing. Longitude = " +
                                                longitude + ", Latitude = " + latitude + ", Height = " + height + ".\n");
            WriterToFile.writeToFile("Tower says: JetPlane#" + this.name + "(" + this.id +
                                                ") unregistered from weather tower.\n");
        }
        if (height > 100)
            height = 100;

        this.coordinates.setLongitude(longitude);
        this.coordinates.setLatitude(latitude);
        this.coordinates.setHeight(height);
    }
}
