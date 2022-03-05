package company.Aircrafts;

import company.Aircrafts.Coordinates;
import company.Aircrafts.Flyable;
import company.Aircrafts.Helicopter;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        if (height > 100)
            height = 100;

        Coordinates tmp = new Coordinates(longitude, latitude, height);

        switch (type) {
            case ("Helicopter"):
                return new Helicopter(name, tmp);
            case ("JetPlane"):
                return new JetPlane(name, tmp);
            case ("Baloon"):
                return new Baloon(name, tmp);
            default:
                System.out.println("Wrong flyable type");
        }
        return null;
    }
}
