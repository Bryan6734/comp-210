package assn06;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        // Create a new empty tree.
        // SelfBalancingBST<Integer> avl_bst = new AVLTree<Integer>();

        // Insert 50 random integers.
        // Note how we need to capture the result of insert back into
        // the variable avl_bst because the post-insertion root that is
        // returned may be different from the original root because of the insertion.
        // resulting height should be about 6.

        // for (int n = 1; n < 10; n++) {

        // for (int i = 0; i < n; i++) {
        // avl_bst = avl_bst.insert(i);
        // }
        // // System.out.println(avl_bst.height());
        // // System.out.println(avl_bst.size());
        // Queue<SelfBalancingBST<Integer>> queue = new LinkedList<>();
        // queue.add(avl_bst);

        // while (!queue.isEmpty()) {
        // SelfBalancingBST<Integer> node = queue.poll();
        // System.out.print(node.getValue() + " ");

        // if (node.getLeft() != null) {
        // queue.add(node.getLeft());
        // }
        // if (node.getRight() != null) {
        // queue.add(node.getRight());
        // }
        // }
        // System.out.println();
        // System.out.println("-----------");

        // }

        // System.out.println(avl_bst.findMax());

        // SelfBalancingBST<Integer> avl_bst2 = new AVLTree<Integer>();
        // // Now insert 50 integers in increasing order which would
        // // cause a simple BST to become very tall but for our
        // // self-balancing tree won't be too bad (should be 5)

        // for (int i=0; i<50; i++) {
        // avl_bst2 = avl_bst2.insert(i);
        // }
        // System.out.println(avl_bst2.height());
        // System.out.println(avl_bst2.size());
        // // Perform a BFS on avl_bst and print the elements

        SelfBalancingBST<Integer> avl_bst3 = new AVLTree<Integer>();
        avl_bst3 = avl_bst3.insert(20);
        avl_bst3 = avl_bst3.insert(11);
        avl_bst3 = avl_bst3.insert(50);
        avl_bst3 = avl_bst3.insert(4);
        avl_bst3 = avl_bst3.insert(6);
        avl_bst3 = avl_bst3.insert(15);
        avl_bst3 = avl_bst3.insert(3);
        avl_bst3 = avl_bst3.insert(16);
        avl_bst3 = avl_bst3.insert(17);
        avl_bst3 = avl_bst3.insert(2);
        System.out.println(avl_bst3.height());
        System.out.println(avl_bst3.size());

        // 11, 50, 6, 15, 16, 17, 2

        avl_bst3 = avl_bst3.remove(4);
        System.out.println(avl_bst3.height());
        System.out.println(avl_bst3.size());

        Queue<SelfBalancingBST<Integer>> queue = new LinkedList<>();
        queue.add(avl_bst3);

        while (!queue.isEmpty()) {
            SelfBalancingBST<Integer> node = queue.poll();
            System.out.print(node.getValue() + " ");

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }

        }

        // System.out.println(avl_bst3.contains(1));
        // System.out.println(avl_bst3.rangeContain(3,8)); // should print false
        // System.out.println(avl_bst3.rangeContain(3,5)); // should print true
        // System.out.println(avl_bst3.rangeContain(6,8)); // should print false
        // avl_bst3 = avl_bst3.insert(6);
        // System.out.println(avl_bst3.rangeContain(3,8)); // should print true
    }
}
