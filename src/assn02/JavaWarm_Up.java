package assn02;

import java.util.HashMap;
import java.util.Scanner;

public class JavaWarm_Up {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputs = parseInput(scanner);
        printHighestLowestFee(inputs);
        printDeviceStatistics(inputs);

    }

    // Input: Scanner class.
    // Output: An array of strings, where each element is a line from the input.
    public static String[] parseInput(Scanner scanner){

        // Read the first integer (i.e. how many lines are there?)
        int numberOfLines = scanner.nextInt();

        // Create array with that many lines
        String[] inputs = new String[numberOfLines];

        // Go to next line, skipping the first integer
        scanner.nextLine();

        // For each line, add to inputs
        for (int i = 0; i < numberOfLines; i++) {
            String line = scanner.nextLine();
            inputs[i] = line;
        }

        // Return inputs
        return inputs;

    }

    public static void printHighestLowestFee(String[] inputs){

        // Initialize highestFee and lowestFee to extreme values.
        // That way, the first inequality comparison will always hold.
        double highestFee = Double.NEGATIVE_INFINITY;
        double lowestFee = Double.POSITIVE_INFINITY;

        // Store the input line that contains the desired information.
        // We'll constantly update this if we encounter a new row that satisfies our criterion.
        String[] highestFeeRow = new String[7];
        String[] lowestFeeRow = new String[7];

        for (String entry : inputs) {
            String[] entry_array = entry.split(" ");
            if (Double.parseDouble(entry_array[3]) >= highestFee) {
                highestFee = Double.parseDouble(entry_array[3]);
                highestFeeRow = entry_array;
            } else if (Double.parseDouble(entry_array[3]) <= lowestFee) {
                lowestFee = Double.parseDouble(entry_array[3]);
                lowestFeeRow = entry_array;
            }
        }

        // Finally, print the statistics we care about.
        for (int i = 0; i < 4; i++){
            System.out.println(highestFeeRow[i]);
        }

        for (int i = 0; i < 4; i++){
            System.out.println(lowestFeeRow[i]);
        }

    }

    public static void printDeviceStatistics(String[] inputs){

        // Initialize a hashmap.
        HashMap<String, double[]> deviceStatistics = new HashMap<>();

        for (String entry : inputs) {
            String[] entry_array = entry.split(" ");
            String category = entry_array[2]; // Laptop, phone, smart_watch

            // Total units
            double totalUnits = Double.parseDouble(entry_array[4]);
            double totalFees = Double.parseDouble(entry_array[3]) * totalUnits;

            // Compute total costs (labor + assembling)
            double laborCost = Double.parseDouble(entry_array[5]) * 16;
            double assemblingCost = Double.parseDouble(entry_array[6]);
            double totalCosts = laborCost + assemblingCost;

            if (deviceStatistics.containsKey(category)) {

                double[] stats = deviceStatistics.get(category);

                stats[0] += totalUnits;
                stats[1] += totalFees;
                stats[2] += totalCosts;

                deviceStatistics.put(category, stats);

            } else {
                deviceStatistics.put(category, new double[]{totalUnits, totalFees, totalCosts});
            }

        }

        // Finally, print the statistics we care about.
        for (String category : new String[] {"phone", "laptop", "smart_watch"}) {
            double[] stats = deviceStatistics.get(category);

            // Compute statistics
            double averageAssemblingFeePerUnit = stats[1] / stats[0];
            double averageNetProfitsPerUnit = (stats[1] - stats[2]) / stats[0];

            // Round to two decimal places
            averageAssemblingFeePerUnit = Math.round(averageAssemblingFeePerUnit * 100.0) / 100.0;
            averageNetProfitsPerUnit = Math.round(averageNetProfitsPerUnit * 100.0) / 100.0;

            // Print out statistics
            System.out.println(category);
            System.out.println((int)stats[0]); // Units
            System.out.printf("%.2f%n", averageAssemblingFeePerUnit);
            System.out.printf("%.2f%n", averageNetProfitsPerUnit);
            

        }
    }
}