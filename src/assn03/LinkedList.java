package assn03;

// Starter Code provided with Assignment #3 for COMP210 Fall 2024

public class LinkedList<T> {
  private Node<T> _head = null;
  private Node<T> _tail = null;
  private int _size = 0;

  /**
   * Task1
   * Remove the node at index i of the list.
   * Note that the first element is at index 0
   * If i is larger than the _size of the list, throw an IndexOutOfBounds
   * Exception
   *
   * ex: list: A -> B -> C -> D
   * i: 1
   * list after removeAtIndex: A -> C -> D
   *
   * @param i - index of node to remove
   */
  public void removeAtIndex(int i) {

    if (i >= _size) {
      throw new IndexOutOfBoundsException();
    }

    if (i < 0) {
      throw new IndexOutOfBoundsException();
    }

    if (_size == 0) {
      return;
    }

    int currentIndex = 0;
    Node<T> currentNode = _head;

    while (currentIndex < i) {
      currentNode = currentNode.getNext();
      currentIndex++;
    }

    // If the index is 0, then we just set the head to the next node.
    if (i == 0) {
      _head = currentNode.getNext();
      _size--;
      return;
    }

    // If the index is the last element, then we set the tail to the previous node.
    if (i == _size - 1) {
      Node<T> previousNode = _head;
      while (previousNode.getNext() != currentNode) {
        previousNode = previousNode.getNext();
      }
      previousNode.setNext(null);
      _tail = previousNode;
      _size--;
      return;
    }

    // If the index is in the middle, then we set the previous node's next to the
    // next node.
    Node<T> previousNode = _head;
    while (previousNode.getNext() != currentNode) {
      previousNode = previousNode.getNext();
    }
    previousNode.setNext(currentNode.getNext());
    _size--;

  }

  /**
   * Task2
   * Return true if this linked list is equal to the list argument, false
   * otherwise.
   * Two lists are equal if they have the same _size, and the same
   * elements in the same order.
   * ex: list: 1 -> 4 -> 2
   * list2: 1 -> 4 -> 2
   * return: true
   *
   * list: 1 -> 5
   * list2: 2 -> 5
   * return false;
   *
   * @param list2 - the list to compare with the current list
   * @return true if the lists have the same elements in the same order, false
   *         otherwise
   */
  public boolean isEqual(LinkedList list2) {
    if (size() != list2.size()) {
      return false;
    }

    Node<T> currentNode1 = _head;
    Node<T> currentNode2 = list2._head;

    // Traverse both lists
    while (currentNode1 != null && currentNode2 != null) {

      // Return false if values are not equal
      if (currentNode1.getValue() != currentNode2.getValue()) {
        return false;
      }

      // Set the respective nodes to be the next node.
      // If the next node is null, each will be set to null and the loop will end.
      currentNode1 = currentNode1.getNext();
      currentNode2 = currentNode2.getNext();
    }

    // If both are null, then that basically means we've traversed the lists with no
    // problems.
    return currentNode1 == null && currentNode2 == null;

  }

  /**
   * Task3
   * Given a sorted linked list, remove the duplicate values from the list
   * ex: list: 5 -> 6 -> 7 -> 7 -> 7 -> 8 -> 8 -> 9
   * list after removeRepeats: 5 -> 6 -> 7 -> 8 -> 9
   *
   */
  public void removeRepeats() {

    if (_size < 2)
      return;

    Node<T> currentNode = _head;
    Node<T> nextUniqueNode;

    while (currentNode.hasNext()) {

      // Obtain the next unique node, ignoring all duplicates
      nextUniqueNode = getNextUnique(currentNode);

      // Set the currentNode's next node to that unique node
      currentNode.setNext(nextUniqueNode);

      // Set currentNode to the next unique, so we can repeat
      // UNLESS unique node is null.
      if (nextUniqueNode != null) {
        currentNode = nextUniqueNode;
      }
    }
  }

  public Node<T> getNextUnique(Node<T> startNode) {

    Node<T> pointerNode = startNode;

    if (!pointerNode.hasNext()) {
      return null;
    }

    while (pointerNode.getValue() == startNode.getValue()) {
      if (pointerNode.hasNext()) {
        pointerNode = pointerNode.getNext();
      } else {
        pointerNode = null;
        break;
      }
    }

    return pointerNode;
  }

  /**
   * Task4
   * Reverse the first n nodes of the list.
   * eg list: 10 -> 9 -> 8 -> 7, and n = 2
   * list after reverse: 9 -> 10 -> 8 -> 7
   */
  public void reverse(int n) {

    if (n <= 1) {
      return;
    }

    if (_size < 2) {
      return;
    }

    /*
     * Fundamentally, this task is exactly the same as reversing a regular linked
     * list (classic LeetCode problem)
     * The only difference is that we reverse the first n elements, and then store a
     * pointer to the head of our original linked list (reversedTailNode). This initially points .next to null, but we'll point it to the unreversed part of the LinkedList.
     */

    // First, we iterate until currentNode.next is null, or when we reach i == n
    int index = 0;
    Node<T> currentNode = _head;
    Node<T> previousNode = null; // Ideally we want this to be the rest of the list that's not being sorted
    Node<T> nextNode;
    Node<T> reversedTailNode = currentNode;

    // Continue until index is less than n OR until we reach the end of the list.
    while ((index < n) && (currentNode != null)) {

      // Suppose we have a list of 10 -> 9 -> 8 -> 7 (n=3)
      nextNode = currentNode.getNext(); // nextNode = 9
      currentNode.setNext(previousNode); // currentNode.next = null (10 -> null)

      previousNode = currentNode; // previousNode = 10
      currentNode = nextNode; // currentNode = 9

      // After the first iteration, we have:
      // null <- 10 | 9 -> 8 -> 7
      // previousNode = 10, currentNode = 9, nextNode = 8

      // After the second iteration, we have:
      // null <- 10 <- 9 | 8 -> 7
      // previousNode = 9, currentNode = 8, nextNode = 7

      // After the third iteration, we have:
      // null <- 10 <- 9 <- 8 | 7
      // previousNode = 8, currentNode = 7, nextNode = null

      index++;
    }

    reversedTailNode.setNext(currentNode); // Connect the last node of the reversed part to the rest of the list
    _head = previousNode;

  }

  /**
   * Task5
   * Merge the given linked list2 into the current list. The 2 lists will always
   * be
   * either the same _size, or the current list will be longer than list2.
   * The examples below show how to handle each case.
   *
   * Note: Do NOT create and return a new list, merge the second list into the
   * first one.
   *
   * ex: list: 1 -> 2 -> 3
   * list2: 4 -> 5 -> 6
   * return: 1 -> 4 -> 2 -> 5 -> 3 -> 6
   *
   * list: 1 -> 2 -> 3 -> 4
   * list2: 5 -> 6
   * return 1 -> 5 -> 2 -> 6 -> 3 -> 4
   *
   * @param list2
   */
  public void merge(LinkedList list2) {

    Node<T> currentNode1 = _head;
    Node<T> currentNode2 = list2._head;

    while (currentNode1 != null && currentNode2 != null) {

      Node<T> nextNode1 = currentNode1.getNext();
      Node<T> nextNode2 = currentNode2.getNext();

      currentNode1.setNext(currentNode2);
      currentNode2.setNext(nextNode1);

      currentNode1 = nextNode1;
      currentNode2 = nextNode2;
    }

  }

  /* Implementations below are being given to you. Do not modify below this. */

  public int size() {
    return _size;
  }

  public boolean isEmpty() {
    return _size == 0;
  }

  public void clear() {
    _head = null;
    _tail = null;
    _size = 0;
  }

  public boolean contains(Object element) {
    Node<T> current = _head;
    while (current != null) {
      if (current.getValue().equals(element)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }

  public T[] toArray() {
    T[] arr = (T[]) new Object[size()];
    Node<T> current = _head;
    int i = 0;
    if (isEmpty()) {
      return arr;
    }
    while (current != null) {
      arr[i] = current.getValue();
      current = current.getNext();
      i++;
    }
    return arr;
  }

  public void add(Object element) {
    Node<T> newNode = new NodeImpl<T>((T) element, null);
    if (isEmpty()) {
      _head = newNode;
      _tail = newNode;
      _size++;
    } else {
      _tail.setNext(newNode);
      _tail = newNode;
      _size++;
    }

  }

  public boolean remove(Object element) {
    Node<T> current = _head;
    if (isEmpty()) {
      return false;
    }
    if (current.getValue() == element) {
      _head = _head.getNext();
      _size--;
      return true;
    }
    while (current.getNext().getValue() != element) {
      current = current.getNext();
      if (current == null) {
        return false;
      }
    }
    if (current.getNext().getNext() == null) {
      _tail = current;
    }
    current.setNext(current.getNext().getNext());
    _size--;
    return true;
  }

  public T get(int index) {
    validIndex(index);
    Node<T> current = _head;
    int i = 0;
    while (i < index) {
      current = current.getNext();
      i++;
    }
    return current.getValue();
  }

  public T set(int index, Object element) {
    validIndex(index);
    Node<T> current = _head;
    T prevValue = null;
    int i = 0;
    if (index == 0) {
      prevValue = _head.getValue();
      _head.setValue((T) element);
    } else {
      while (current != null) {
        if (i == index) {
          prevValue = current.getValue();
          current.setValue((T) element);
          return prevValue;
        }
        current = current.getNext();
        i++;
      }
    }

    return prevValue;
  }

  public void add(int index, Object element) {
    if (index > _size) {
      validIndex(index);
    }
    Node<T> current = _head;
    int i = 0;
    if (index == 0) {
      if (isEmpty()) {
        add(element);
        return;
      } else {
        Node<T> newNode = new NodeImpl<T>((T) element, _head.getNext());
        _head = newNode;
        _size++;
        return;
      }

    } else if (index == _size) {
      add(element);
      return;
    }
    while (current != null) {
      if (i == (index - 1)) {
        Node<T> temp = current.getNext();
        Node<T> newNode = new NodeImpl<T>((T) element, temp);
        current.setNext(newNode);
        _size++;
        return;
      } else {
        current = current.getNext();
        i++;
      }
    }
  }

  public int indexOf(Object element) {
    Node<T> current = _head;
    int index = 0;
    while (current != null) {
      if (current.getValue().equals((T) element)) {
        return index;
      }
      index++;
      current = current.getNext();
    }
    return -1;
  }

  public int lastIndexOf(Object element) {
    Node<T> current = _head;
    int index = -1;
    int i = 0;
    while (current != null) {
      if (current.getValue().equals((T) element)) {
        index = i;
      }
      i++;
      current = current.getNext();
    }
    return index;
  }

  public void validIndex(int i) {
    if (i < 0 || i >= _size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
  }

  public Node<T> gethead() {
    return _head;
  }

  @Override
  public String toString() {
    String list = "";
    Node<T> current = _head;
    while (current != null) {
      if (current.getNext() == null)
        list += current.getValue();
      else
        list += current.getValue() + " -> ";
      current = current.getNext();
    }
    return list;
  }
}