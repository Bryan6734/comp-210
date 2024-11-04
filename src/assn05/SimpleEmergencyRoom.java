package assn05;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> _patients;

    public SimpleEmergencyRoom() {
        _patients = new ArrayList<>();
    }

    /**
     * TODO (Task 1): dequeue
     * 
     * @return return patient with the highest priority
     */
    public Patient dequeue() {
        int max_priority = 0;
        int max_index = 0; 
        for (int i = 0; i < _patients.size(); i++) {

            int current_priority = (Integer) _patients.get(i).getPriority();

            if (current_priority > max_priority) {
                max_index = i;
            }

        }
        
        return _patients.remove(max_index);
    }

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        _patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        _patients.add(patient);
    }

    public List getPatients() {
        return _patients;
    }

    public int size() {
        return _patients.size();
    }

}
