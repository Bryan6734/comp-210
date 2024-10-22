package assn04;

public class Main {
    public static void main(String[] args) {
        /*
         * This is a basic example of how to create a BST and add values to it.
         * You should add more examples and use this class to debug your code
         */
        BST<Integer> bst = new NonEmptyBST<Integer>(20);
        bst = bst.insert(15);
        bst = bst.insert(30);
        bst = bst.insert(10);
        bst = bst.insert(18);
        bst = bst.insert(25);
        bst = bst.insert(35);
        // bst = bst.remove(9);
        // bst.printPreOrderTraversal();
        bst = bst.replace_range(15, 30, 400);



        bst.printPreOrderTraversal();

    }

}
