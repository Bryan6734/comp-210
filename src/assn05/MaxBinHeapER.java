package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MaxBinHeapER<V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V, P>> _heap;

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
     * 
     * @param value
     * @param priority
     */
    @Override
    public void enqueue(V value, P priority) {

        Patient<V, P> patient = new Patient<>(value, priority);
        _heap.add(patient);
        System.out.println("Enqueuing: " + value + ", " + priority);
        bubbleUp(_heap.size() - 1);

    }

    /**
     * TODO (Task 2A): enqueue (overloaded) using only value
     * 
     * @param value
     */
    public void enqueue(V value) {
        Patient<V, P> patient = new Patient<>(value);

    }

    // bubblesUp the child priority element at index and returns final index
    private int bubbleUp(int index) {
        if (index == 0) {
            return index;
        }

        // We begin at the very last index of the array, and get the child node (i.e.
        // current) and the parent node.
        else {
            Prioritized<V, P> child = _heap.get(index);
            Prioritized<V, P> parent = _heap.get(parentIndex(index));

            // If the child is greater than parent, we have an ISSUE!
            // Then, we must swap.
            if (child.getPriority().compareTo(parent.getPriority()) > 0) {
                _heap.set(parentIndex(index), child);
                _heap.set(index, parent);

                // We than do a bubbleUp on the parentIndex of the current node..
                return (bubbleUp(parentIndex(index)));
            } else {
                // If parent is greater than child, no modifications are needed.
                return index;
            }
        }
    }

    private void bubbleDown(int index) {
        Prioritized<V, P> parent = _heap.get(index);

        // case 1: leaf
        if (!hasLeftChild(index) && !hasRightChild(index)) {
            return;
        }
        // case 2: only left child
        else if (!hasRightChild(index)) {

            // if left child is greater than current parent node
            if (_heap.get(leftChildIndex(index)).getPriority().compareTo(parent.getPriority()) > 0) {
                swap(leftChildIndex(index), index);
                bubbleDown(leftChildIndex(index));
            }

        }
        // case 3: only right child
        else if (!hasLeftChild(index)) {
            if (_heap.get(rightChildIndex(index)).getPriority().compareTo(parent.getPriority()) > 0) {
                swap(rightChildIndex(index), index);
                bubbleDown(rightChildIndex(index));
            }
        }
        // case 4: two children
        else if (hasRightChild(index) && hasLeftChild(index)) {
            // get max of both childrenm
            Prioritized<V, P> leftChild = _heap.get(leftChildIndex(index));
            Prioritized<V, P> rightChild = _heap.get(rightChildIndex(index));

            // if left child is greater than right child
            if (leftChild.getPriority().compareTo(rightChild.getPriority()) > 0) {
                if (leftChild.getPriority().compareTo(parent.getPriority()) > 0) {
                    swap(leftChildIndex(index), index);
                    bubbleDown(leftChildIndex(index));
                }
            // if right child is greater than left child
            } else {
                if (rightChild.getPriority().compareTo(parent.getPriority()) > 0) {
                    swap(rightChildIndex(index), index);
                    bubbleDown(rightChildIndex(index));
                }
            }
        }
    }

    /**
     * TODO (Task 2A): dequeue from maxBinHeapER
     * 
     * @return element with highest priority
     */
    @Override
    public V dequeue() {
        // We cannot just take the largest child of the two, as it might not result in a
        // CBT.
        // Steps:
        // remove top, replace with last element
        // bubble down recursively.
        // -- find max of left/right, and swap.
        // -- recurse downwards
        if (_heap.isEmpty()) {
            return null;
        } else if (_heap.size() == 1) {
            return (_heap.remove(0).getValue());
        } else {
            Prioritized<V, P> returnValue = _heap.get(0);
            // replace root node with last element
            _heap.set(0, _heap.remove(_heap.size() - 1));

            // bubble down, starting at 0
            bubbleDown(0);

            return returnValue.getValue();
        }
    }

    /**
     * TODO (Task 2A): getMax
     * 
     * @return return value of element with the highest priority
     */
    @Override
    public V getMax() {

        if (_heap.isEmpty()) {
            return null;
        }

        return _heap.get(0).getValue();
    }

    /**
     * TODO (Task 2B): updatePriority
     * Change the priority of the patient with the given value.
     * if newPriority is <0 then remove the element with given value
     * (In case value is not matching and existing Priority < 0, nothing is to be
     * done.)
     * 
     * @param value
     * @param newPriority
     */
    public void updatePriority(V value, P newPriority) {
        
        // search for patient
        for (int i = 0; i < _heap.size(); i++) {
            if (_heap.get(i).getValue().equals(value)) {

                // if newPriority < 0, remove element with given value
                if (newPriority.compareTo((P) Integer.valueOf(0)) < 0) {
                    _heap.remove(i);
                    bubbleDown(i);
                    break;
                }
                
                // update the priority
                _heap.get(i).setPriority(newPriority);

                // bubble up or down
                if (newPriority.compareTo(_heap.get(parentIndex(i)).getPriority()) > 0) {
                    // if the new priority is greater than the parent, OH NO!
                    // then, we bubble up
                    bubbleUp(i);
                } else {
                    // if the new priority is less than the parent, OH NO!
                    // then, we bubble down
                    bubbleDown(i);
                }

                break;
            } 
        }

    }

    /**
     * TODO (Task 3): MaxBinHeapER
     * 2nd constructor that builds a heap given an initial array of prioritized
     * elements.
     * 
     * @param initialEntries This is an initial ArrayList of patients
     */
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries) {


        // build max heap algorithm
        // 1. start from the last non-leaf node
        // 2. bubble down

        _heap = new ArrayList<>();
        for (int i = 0; i < initialEntries.length; i++) {
            _heap.add(initialEntries[i]);
        }

        for (int i = parentIndex(_heap.size() - 1); i >= 0; i--) {
            bubbleDown(i);
        }


    }

    /**
     * Retrieves contents of heap as an array.
     * 
     * @return array of Prioritized elements in the order stored in the heap
     */
    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V, P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }

    static int leftChildIndex(int index) {
        return (2 * index + 1);
    }

    static int rightChildIndex(int index) {
        return (2 * index + 2);
    }

    static int parentIndex(int index) {
        return ((index - 1) / 2);
    }

    boolean validIndex(int index) {
        if ((index >= 0) && (index < _heap.size())) {
            return true;
        } else {
            return false;
        }
    }

    boolean hasLeftChild(int index) {
        return validIndex(leftChildIndex(index));
    }

    boolean hasRightChild(int index) {
        return validIndex(rightChildIndex(index));
    }

    void swap(int index_1, int index_2) {
        Prioritized<V, P> temp = _heap.get(index_1);
        _heap.set(index_1, _heap.get(index_2));
        _heap.set(index_2, temp);
    }
}
