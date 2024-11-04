package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;

    /**
     * 1st constructor that creates an empty max heap of prioritized elements.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    /**
     * @return size of heap
     */
    @Override
    public int size() {
        return _heap.size();
    }

    /**
     * TODO (Task 2A): enqueue using value and priority
     * @param value
     * @param priority
     */
    @Override
    public void enqueue(V value, P priority) {
    }

    /**
     * TODO (Task 2A): enqueue (overloaded) using only value
     * @param value
     */
    public void enqueue(V value) {
    }

    /**
     * TODO (Task 2A): dequeue from maxBinHeapER
     * @return element with highest priority
     */
    @Override
    public V dequeue() {
         return null;
    }

    /**
     * TODO (Task 2A): getMax
     * @return return value of element with the highest priority
     */
    @Override
    public V getMax() {
    	 return null;
    }

    /**
     * TODO (Task 2B): updatePriority
     * Change the priority of the patient with the given value.
     * if newPriority is <0 then remove the element with given value
     * (In case value is not matching and existing Priority < 0, nothing is to be done.)
     * @param value
     * @param newPriority
     */
    public void updatePriority(V value, P newPriority) {
    }

    /**
     * TODO (Task 3): MaxBinHeapER
     * 2nd constructor that builds a heap given an initial array of prioritized elements.
     * @param initialEntries This is an initial ArrayList of patients
     */
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
    }

    /**
     * Retrieves contents of heap as an array.
     * @return array of Prioritized elements in the order stored in the heap
     */
    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }
}

