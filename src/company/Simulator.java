package company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Simulator {
    public static void main(String[] args) throws Exception {
        WeatherTower weatherTower = new WeatherTower();
        int simulatorNumber = 0;
        FileReader fileReader = new FileReader("src/company/scenario.txt");
        BufferedReader reader = new BufferedReader(fileReader);
        String line = reader.readLine();
        List<String> aircraftsParam = new ArrayList<String>();
        while (line != null) {
            if (simulatorNumber == 0) {
                try {
                    simulatorNumber = Integer.parseInt(line);
                } catch(NumberFormatException ex) {
                    System.err.println(ex.getMessage());
                    return;
                }
            } else {
                String[] parameters = line.split(" ");
                if (parameters.length != 5) {
                    System.err.println("Wrong number of parameters");
                    return;
                }
                if (!parameters[0].equals("Baloon") && !parameters[0].equals("Helicopter")
                        && !parameters[0].equals("JetPlane")) {
                    System.err.println("Wrong aircraft type");
                    return;
                }
                try {
                    Integer.parseInt(parameters[2]);
                    Integer.parseInt(parameters[3]);
                    Integer.parseInt(parameters[4]);
                } catch(NumberFormatException ex) {
                    System.err.println("Wrong aircraft coordinates");
                    return;
                }
                if (Integer.parseInt(parameters[2]) < 0 || Integer.parseInt(parameters[3]) < 0
                        || Integer.parseInt(parameters[4]) < 0)  {
                    System.err.println("Wrong aircraft data, coordinates less than zero");
                    return;
                }
            }
            aircraftsParam.add(line);
            line = reader.readLine();
        }
        for(int i = 1; i < aircraftsParam.size(); i++) {
            String[] params = aircraftsParam.get(i).split(" ");
            Flyable flyable = AircraftFactory.newAircraft(params[0], params[1], Integer.parseInt(params[2]),
                    Integer.parseInt(params[2]), Integer.parseInt(params[2]));
            flyable.registerTower(weatherTower);
        }
        for(int i = 0; i < simulatorNumber; i++) {
            weatherTower.changeWeather();
        }
    }
}
