import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class PA2Main {

    public static void main(String[] args) {
        // open file by checking arg[0] (which should be the file name)
        Scanner input = null;
        try {
            input = new Scanner(new File(args[0]));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }
        String command = args[1];
        // HashMap<String, Integer> flights = countDepartures(input);
        HashMap<String, String> flightInfo = new HashMap<String, String>();
        if (command.equals("MAX")) {
            System.out.println("max");
        } else if (command.equals("DEPARTURES")) {
            flightInfo = calcDepartures(input, flightInfo);
            // System.out.println("departures");
        } else if (command.equals("LIMIT")) {
            System.out.println("limit");
        }
        // Look at write up to understand the alphabetical sorting
        if (flightInfo != null) {
            display(flightInfo, command);
        }
        input.close();
    }

    public static HashMap<String, Integer> countDepartures(Scanner in) {

        HashMap<String, Integer> airportToNumFlights = new HashMap<String, Integer>();
        in.nextLine();

        while (in.hasNextLine()) {
            String[] info = in.nextLine().split(",");
            String key = info[2];
            if (airportToNumFlights.containsKey(key)) {
                airportToNumFlights.put(key, airportToNumFlights.get(key) + 1);
            } else {
                airportToNumFlights.put(key, 1);
            }
        }
        return airportToNumFlights;
    }

    public static HashMap<String, String> calcDepartures(Scanner input,
            HashMap<String, String> flightInfo) {
        input.nextLine();
        while (input.hasNextLine()) {
            String[] info = input.nextLine().split(",");
            String key = info[2];
            String flight = info[4];
            if (flightInfo.containsKey(key)) {
                flightInfo.put(key, flightInfo.get(key) + " " + flight);
            } else {
                flightInfo.put(key, flight);
            }
        }
        return flightInfo;
    }

    // just prints HashMap
    public static void display(HashMap<String, String> flights,
            String command) {
        String between = "";
        if (command.equals("DEPARTURES")) {
            between = " flys to ";
        } else if (command.equals("LIMIT")) {
            between = " - ";
        }
        ArrayList<String> airportsSorted = new ArrayList<String>(
                flights.keySet());
        Collections.sort(airportsSorted);
        for (String airport : airportsSorted) {
            System.out.println(airport + between + flights.get(airport));
        }
    }
}
