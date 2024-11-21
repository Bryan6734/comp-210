package assn04;

import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
    private T _element;
    private BST<T> _left;
    private BST<T> _right;

    public NonEmptyBST(T element) {

        _left = new EmptyBST<T>();
        _right = new EmptyBST<T>();
        _element = element;
    }

    // TODO: insert
    @Override
    public BST<T> insert(T element) {

        if (element.compareTo(_element) < 0) {
            _left = _left.insert(element);
        } else if (element.compareTo(_element) > 0) {
            _right = _right.insert(element);
        } else {
            // Element is already in the tree, do nothing
            return this;
        }
        return this;
    }

    // TODO: findMin
    @Override
    public T findMin() {
        if (_left.isEmpty()) {
            return _element;
        }
        return _left.findMin();

    }

    // TODO: remove


    /*
     * The intuition is that you basically 
     * 
     * 
     * 
     */
    @Override
    public BST<T> remove(T element) {

        if (element.compareTo(_element) < 0) {
            _left = _left.remove(element);

        } else if (element.compareTo(_element) > 0) {
            _right = _right.remove(element);
        }

        else if (element.compareTo(_element) == 0) {

            // There are three possible cases:
            // [1] The node has no children

            if (_left.isEmpty() && _right.isEmpty()) {
                return new EmptyBST<>(); // could return null, but wanted to keep consistent
            }

            // [2] The node has only one child
            if (_left.isEmpty()) {
                return _right;
            }

            else if (_right.isEmpty()) {
                return _left;
            }

            else {
                // [3] The node has two children
                T min = _right.findMin();
                _element = min;
                _right = _right.remove(min);

            }

        }

        return this;
    }

    // TODO: replace_range

    @Override
    public BST<T> replace_range(T start, T end, T newValue) {

        BST<T> my_x = this.temp(start, end, newValue);

        if (newValue.compareTo(start) < 0 || newValue.compareTo(end) > 0) {
            my_x.insert(newValue);
            
        }
        return my_x;
    }

    public BST<T> temp(T start, T end, T newValue) {

        if (!_left.isEmpty()) {
            _left = _left.temp(start, end, newValue);
        }

        if (!_right.isEmpty()) {
            _right = _right.temp(start, end, newValue);
        }

        if (_element.compareTo(start) >= 0 && _element.compareTo(end) <= 0) {
            return this.remove(_element);
        }

        return this;

    }

    // TODO: printPreOrderTraversal
    @Override
    public void printPreOrderTraversal() {

        System.out.print(_element + " ");

        if (!_left.isEmpty()) {
            _left.printPreOrderTraversal();
        }

        if (!_right.isEmpty()) {
            _right.printPreOrderTraversal();
        }

    }

    // TODO: printPostOrderTraversal
    @Override
    public void printPostOrderTraversal() {

        if (!_left.isEmpty()) {
            _left.printPostOrderTraversal();
        }

        if (!_right.isEmpty()) {
            _right.printPostOrderTraversal();
        }

        System.out.print(_element + " ");

    }

    // Do not change the methods below
    @Override
    public int getHeight() {
        return Math.max(_left.getHeight(), _right.getHeight()) + 1;
    }

    @Override
    public BST<T> getLeft() {
        return _left;
    }

    @Override
    public BST<T> getRight() {
        return _right;
    }

    @Override
    public T getElement() {
        return _element;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    

}
