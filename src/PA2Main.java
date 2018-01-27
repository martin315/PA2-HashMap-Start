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
        HashMap<String, Integer> flights = countDepartures(input);

        // Look at write up to understand the alphabetical sorting
        display(flights, command);
        input.close();
        // System.out.println(command);
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

    // just prints HashMap
    public static void display(HashMap<String, Integer> flights,
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
