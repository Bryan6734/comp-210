package assn06;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;

    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    /**
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
    private AVLTree<T> rotateLeft() {
        // You should implement left rotation and then use this
        // method as needed when fixing imbalances.
        // TODO

        AVLTree<T> newRoot = _right;
        AVLTree<T> temp = newRoot._left;

        newRoot._left = this;
        _right = temp;

        _height = 1 + Math.max(_left.height(), _right.height());
        newRoot._height = 1 + Math.max(newRoot._left.height(), newRoot._right.height());

        _size = 1 + _left.size() + _right.size();
        newRoot._size = 1 + newRoot._left.size() + newRoot._right.size();

        return newRoot;
    }

    /**
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */
    private AVLTree<T> rotateRight() {
        // You should implement right rotation and then use this
        // method as needed when fixing imbalances.
        // TODO

        AVLTree<T> newRoot = _left;
        AVLTree<T> temp = newRoot._right;

        newRoot._right = this;
        _left = temp;

        _height = 1 + Math.max(_left.height(), _right.height());
        newRoot._height = 1 + Math.max(newRoot._left.height(), newRoot._right.height());

        _size = 1 + _left.size() + _right.size();
        newRoot._size = 1 + newRoot._left.size() + newRoot._right.size();

        return newRoot;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T value) {

        if (isEmpty()) {
            _value = value;
            _left = new AVLTree<>();
            _right = new AVLTree<>();
            _height = 0;
            _size = 1;
            return this;
        }

        if (value.compareTo(_value) > 0) {
            _right = (AVLTree<T>) _right.insert(value);
        } else if (value.compareTo(_value) < 0) {
            _left = (AVLTree<T>) _left.insert(value);
        } else {
            return this;
        }

        
        _height = 1 + Math.max(_left.height(), _right.height());
        _size = 1 + _left.size() + _right.size();

        // get the balanceFactor first
        int balanceFactor = _left.height() - _right.height();
        System.err.println("[" + _value + "] " + balanceFactor);

        // Left left:
        if (balanceFactor > 1 && value.compareTo(_left.getValue()) < 0) {
            return rotateRight();
        }
        
        // Right right:
        if (balanceFactor < -1 && value.compareTo(_right.getValue()) > 0) {
            return rotateLeft();
        }

        
        // Left right
        // this shit is confusing, but basically you treat the left child as the root and do a left rotation to make it balanced
        // THEN, you do a right rotation. this makes sure the balancing doesnt get screwed up even more lol!

        if (balanceFactor > 1 && value.compareTo(_left.getValue()) > 0) {
            _left = (AVLTree<T>) _left.rotateLeft();
            return rotateRight();
        }

        // Right left
        if (balanceFactor < -1 && value.compareTo(_right.getValue()) < 0) {
            _right = (AVLTree<T>) _right.rotateRight();
            return rotateLeft();
        }

        return this; 
    }
    
 

    @Override
    public SelfBalancingBST<T> remove(T value) {
        // TODO

        return null;
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        
        AVLTree<T> temp = this;
        while (temp._left != null) {
            temp = temp._left;
        }
        
        System.out.println(temp.getValue());

        return null;
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        // TODO

        return null;
    }

    @Override
    public boolean contains(T element) {
        // TODO

        return false;
    }


    @Override
    public boolean rangeContain(T start, T end) {
        // TODO

        return false;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }
        return _right;
    }


}
