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
        HashMap<String, Integer> numOfFlights = calcNumFlights(input);

        if (command.equals("MAX")) {
            System.out.println("max");
        } else if (command.equals("DEPARTURES")) {
            flightInfo = calcDepartures(input, flightInfo);
        } else if (command.equals("LIMIT")) {
            // check for agrs 2
            flightInfo = calcLimit(input, flightInfo, numOfFlights,
                    Integer.parseInt(args[2]));
        }
        // Look at write up to understand the alphabetical sorting
        if (flightInfo != null) {
            display(flightInfo, command);
        }
        input.close();
    }

    public static HashMap<String, Integer> calcNumFlights(Scanner input) {
        HashMap<String, Integer> numOfFlights = new HashMap<String, Integer>();

        while (input.hasNextLine()) {
            String[] info = input.nextLine().split(",");
            numOfFlights = updateNumInfo(info[2], numOfFlights);
            numOfFlights = updateNumInfo(info[4], numOfFlights);
        }
        return numOfFlights;
    }

    public static HashMap<String, String> calcLimit(Scanner input,
            HashMap<String, String> flightInfo,
            HashMap<String, Integer> numInfo, int limit) {

        for (String key : numInfo.keySet()) {
            if (numInfo.get(key) > limit) {
                flightInfo.put(key, String.valueOf(numInfo.get(key)));
            }
        }
        return flightInfo;
    }

    public static HashMap<String, Integer> updateNumInfo(String key,
            HashMap<String, Integer> numOfFlights) {
        if (numOfFlights.containsKey(key)) {
            numOfFlights.put(key, numOfFlights.get(key) + 1);
        } else {
            numOfFlights.put(key, 1);
        }
        return numOfFlights;
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
