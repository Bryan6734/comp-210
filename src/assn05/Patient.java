package assn05;

import java.util.Random;

/**
 * class Patient implements the Prioritized interface
 * @param <V> generic data type for the value
 * @param <P> P to be assigned for the priority
 */
public class Patient<V, P extends Comparable<P>> implements Prioritized<V, P> {
    private P _priority;
    private V _value;

    /**
     * 1st constructor that sets both value and priority
     * @param value the value to be set
     * @param priority priority to be set
     */
    public Patient(V value, P priority) {
        this._value = value;
        this._priority = priority;
    }

    /**
     * 2nd constructor that takes only value and randomly sets priority
     * @param value the value to be set
     */
    public Patient(V value) {
        this._value = value;
        calculatePriority();
    }

    /**
     * @return the _value field.
     */
    @Override
    public V getValue() {
        return _value;
    }

    @Override
    public void setPriority(P priority) {
        this._priority = priority;
    }

    /**
     * @return returns _priority field.
     */
    @Override
    public P getPriority() { return _priority; }

    /**
     * This helper method sets _priority field to an integer between 0 and 999,999 (i.e. 1000,000-1).
     */
    private void calculatePriority() {
        Random random = new Random();
        this._priority = (P) Integer.valueOf(random.nextInt(1000000));
    }

    /**
     * @param other The 'other' item to be compared with
     * @return int 0 if priorities are equal, or <0 if (this.getPriority < other.getPriority), or >0 otherwise.
     */
    public int compareTo(Prioritized<V, P> other) {
    	return this._priority.compareTo(other.getPriority());
    }
}
