/*
  Jonathan Gentry
  COSC 2336-48F
  Instructor: Dr. Doerschuk
  Programming Assignment 8
  Due: 11/4/2020
  Submitted: 11/4/2020
  Programming language: Java JDK 14
  Add methods public void breadthFirstTraversal() and public int height() to the BST class in chapter 25.
  breadthFirstTraversal() implemented at line 284
  height() implemented at line 304
 */

package Main.Java.ClassWork.BinarySearchTrees.Assingment;

import java.util.*;

public class BinarySearchTreeJonathanGentry {
    public static void main(String[] args) {

        //Create a BST for colors, display height
        BST<String> colorTree = new BST<>();
        System.out.println("Empty tree of Strings created. The height of the tree is " + colorTree.height());

        // Add element and display height
        colorTree.insert("Red");
        System.out.println("After Red is added the height of the tree is " + colorTree.height());

        // Add element and display height
        colorTree.insert("Green");
        System.out.println("After Green is added the height of the tree is " + colorTree.height());

        // Display to console assignment text
        System.out.println("Creating a new BinarySearchTree with the following String elements:");
        System.out.println("Tom, George, Jean, Jane, Kevin, Peter, Susan, Jen, Kim, Michael, Michelle.");

        // Create a BST then add names in order (I could of made a string array, just followed book example)
        BST<String> stringTree = new BST<>();
        stringTree.insert("Tom");
        stringTree.insert("George");
        stringTree.insert("Jean");
        stringTree.insert("Jane");
        stringTree.insert("Kevin");
        stringTree.insert("Peter");
        stringTree.insert("Susan");
        stringTree.insert("Jen");
        stringTree.insert("Kim");
        stringTree.insert("Michael");
        stringTree.insert("Michelle");
        System.out.println();

        // Display the breadthFirstTraversal method and tree height
        System.out.print("The breadth-first traversal is ");
        stringTree.breadthFirstTraversal();
        System.out.println("\nThe height of the tree is " + stringTree.height());

        // Display to console assignment text
        System.out.println("Creating a new BinarySearchTree with the following Integers: ");
        System.out.println("50, 45, 35, 48, 59, 51, 58");
        System.out.println();

        // Create array then create BST and add array
        Integer[] numbers = {50, 45, 35, 48, 59, 51, 58};
        BST<Integer> intTree = new BST<>(numbers);

        // Display the breadthFirstTraversal method and tree height
        System.out.print("The breadth-first traversal for the tree is ");
        intTree.breadthFirstTraversal();
        System.out.println("\nThe height of the tree is " + intTree.height());
    }
}

class BST<E extends Comparable<E>> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;

    /** Create a default binary tree */
    public BST() {
    }

    /** Create a binary tree from an array of objects */
    public BST(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    @Override /** Returns true if the element is in the tree */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else // element matches current.element
                return true; // Element is found
        }

        return false;
    }

    @Override /** Insert element e into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                }
                else
                    return false; // Duplicate node not inserted

            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        return true; // Element inserted successfully
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override /** Inorder traversal from the root */
    public void inorder() {
        inorder(root);
    }

    /** Inorder traversal from a subtree */
    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    @Override /** Postorder traversal from the root */
    public void postorder() {
        postorder(root);
    }

    /** Postorder traversal from a subtree */
    protected void postorder(TreeNode<E> root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    @Override /** Preorder traversal from the root */
    public void preorder() {
        preorder(root);
    }

    /** Preorder traversal from a subtree */
    protected void preorder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /** This inner class is static, because it does not access
     any instance members defined in its outer class */
    public static class TreeNode<E extends Comparable<E>> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    @Override /** Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }

    /** Returns the root of the tree */
    public TreeNode<E> getRoot() {
        return root;
    }

    /** Returns a path from the root leading to the specified element */
    public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                break;
        }

        return list; // Return an array list of nodes
    }

    @Override /** Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            }
            else
                break; // Element is in the tree pointed at by current
        }

        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left child
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            }
            else {
                if (e.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        }
        else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
        }

        size--;
        return true; // Element deleted successfully
    }

    /** Displays the nodes in a breadth-first traversal */
    public void breadthFirstTraversal() {
        List<TreeNode<E>> list = new ArrayList<TreeNode<E>>();
        if (root != null)
            list.add(root);

        TreeNode<E> current;

        while (!list.isEmpty()) {
            current = (TreeNode<E>) list.remove(0);
            // Display the element in the tree
            System.out.print(current.element + " ");

            if (current.left != null)
                list.add(current.left);
            if (current.right != null)
                list.add(current.right);
        }
    }

    /** Returns the height of this binary tree */
    public int height() {
        return height(root);
    }

    /** Height helper class */
    private int height(TreeNode<E> root) {
        if (root == null){
            return 0;
        } else
            return 1 + Math.max(height(root.left), height(root.right));
    }

    @Override /** Obtain an iterator. Use inorder. */
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    private class InorderIterator implements java.util.Iterator<E> {
        // Store the elements in a list
        private java.util.ArrayList<E> list =
                new java.util.ArrayList<>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /** Inorder traversal from the root*/
        private void inorder() {
            inorder(root);
        }

        /** Inorder traversal from a subtree */
        private void inorder(TreeNode<E> root) {
            if (root == null) return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override /** More elements for traversing? */
        public boolean hasNext() {
            if (current < list.size())
                return true;

            return false;
        }

        @Override /** Get the current element and move to the next */
        public E next() {
            return list.get(current++);
        }

        @Override // Remove the element returned by the last next()
        public void remove() {
            if (current == 0) // next() has not been called yet
                throw new IllegalStateException();

            delete(list.get(--current));
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    @Override /** Remove all elements from the tree */
    public void clear() {
        root = null;
        size = 0;
    }
}

interface Tree<E> extends Collection<E> {
    /** Return true if the element is in the tree */
    public boolean search(E e);

    /** Insert element e into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(E e);

    /** Delete the specified element from the tree
     * Return true if the element is deleted successfully */
    public boolean delete(E e);

    /** Get the number of elements in the tree */
    public int getSize();

    /** Inorder traversal from the root*/
    public default void inorder() {
    }

    /** Postorder traversal from the root */
    public default void postorder() {
    }

    /** Preorder traversal from the root */
    public default void preorder() {
    }

    @Override /** Return true if the tree is empty */
    public default boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public default boolean contains(Object e) {
        return search((E)e);
    }

    @Override
    public default boolean add(E e) {
        return insert(e);
    }

    @Override
    public default boolean remove(Object e) {
        return delete((E)e);
    }

    @Override
    public default int size() {
        return getSize();
    }

    @Override
    public default boolean containsAll(Collection<?> c) {
        // Left as an exercise
        return false;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c) {
        // Left as an exercise
        return false;
    }

    @Override
    public default boolean removeAll(Collection<?> c) {
        // Left as an exercise
        return false;
    }

    @Override
    public default boolean retainAll(Collection<?> c) {
        // Left as an exercise
        return false;
    }

    @Override
    public default Object[] toArray() {
        // Left as an exercise
        return null;
    }

    @Override
    public default <T> T[] toArray(T[] array) {
        // Left as an exercise
        return null;
    }
}