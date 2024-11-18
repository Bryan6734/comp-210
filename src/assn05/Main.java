package assn05;

public class Main {

    /**
     * Write a series of tests to check the functionality of each task
     * 
     * @param args
     */
    public static void main(String[] args) {
        // testT1();
        testT2();
        // testT3();
        // testT4();
    }

    /**
     * Test Task 1 - Write some tests to convince yourself that your code for Task 1
     * is working
     */
    public static void testT1() {

        SimpleEmergencyRoom emergencyRoom = new SimpleEmergencyRoom();

        emergencyRoom.addPatient(0, 1);


        Patient patient = emergencyRoom.dequeue();
        System.out.println(patient.getValue());
        System.out.println(patient.getPriority());
    }

    /**
     * Test Task 2 - Write some tests to convince yourself that your code for Task 2
     * (A & B) is working
     */
    public static void testT2() {

        MaxBinHeapER<Integer, Integer> emergencyRoom = new MaxBinHeapER<>();

        for (int i = 0; i < 1; i++) {
            emergencyRoom.enqueue(i, i + 1);
        }

        emergencyRoom.enqueue(10);

        emergencyRoom.updatePriority(5, 100);

        Prioritized[] arr = emergencyRoom.getAsArray();
        for (int i = 0; i < emergencyRoom.size(); i++) {
            System.out.println("Value: " + arr[i].getValue() + ", Priority: " + arr[i].getPriority());
        }

        System.out.println("Size: " + emergencyRoom.size());

    }

    /**
     * Test Task 3 - This part can be used to test for task 3.
     */
    public static void testT3() {
        MaxBinHeapER transfer = new MaxBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();
        for (int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }
    }

    /**
     * Test Task 4 - Write some tests to convince yourself that your code for Task 4
     * is working
     * You can use some of the helper methods already given below
     */
    public static void testT4() {
        double[] results = compareRuntimes();
        System.out.println("Total nanosec for simpleER: " + results[0]);
        System.out.println("Average nanosec for simpleER: " + results[1]);
        System.out.println("Total nanosec for maxHeapER: " + results[2]);
        System.out.println("Average nanosec for maxHeapER: " + results[3]);
    }

    /**
     * fills up an Emergency Room based on a MaxBinHeapER
     * 
     * @param complexER an initially empty MaxBinHeapER
     */
    public static void fillER(MaxBinHeapER complexER) {
        for (int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }

    /**
     * fills up an Emergency Room based on a SimpleEmergencyRoom (overloaded)
     * 
     * @param simpleER an initially empty SimpleEmergencyRoom
     */
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for (int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    /**
     * Creates an array of patients
     * 
     * @return returns this array of patients
     */
    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for (int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    /**
     * TODO (Task 4): compareRuntimes
     * Compares the Run Times of the SimpleEmergencyRoom vs MaxBinHeapER
     * 
     * @return an array of results as follows:
     *         index 0: total nanosec for simpleER
     *         index 1: average nanosec for simpleER
     *         index 2: total nanosec for maxHeapER
     *         index 3: average nanosec for maxHeapER
     */
    public static double[] compareRuntimes() {
        // Array which you will populate as part of Task 4
        double[] results = new double[4];

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        // Code for (Task 4.1) Here

        // Code for (Task 4.2) Here
        long startTime = System.nanoTime();
        while (simplePQ.size() > 0) {
            simplePQ.dequeue();
        }
        long endTime = System.nanoTime();
        results[0] = endTime - startTime;
        results[1] = results[0] / 100000.0;

        startTime = System.nanoTime();
        while (binHeap.size() > 0) {
            binHeap.dequeue();
        }
        endTime = System.nanoTime();
        results[2] = endTime - startTime;
        results[3] = results[2] / 100000.0;

        return results;
    }

}
