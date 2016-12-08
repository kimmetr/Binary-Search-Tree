package com.company;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.Scanner;

public class BinarySearchTree<E extends Comparable<E>> {

    private int size;

    private BinarySearchTreeNode<E> root;

    public BinarySearchTree() {
        size = 0;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < 50; i++) {
            int num = (int) (Math.random() * 100);
            if ((i == 0) || !(tree.root.contains(num))) {
                tree.add(num);
            }
        }
        int choice = 0;
        do {
            Scanner myScanner = new Scanner(System.in);
            System.out.println("Options:");
            System.out.println("1) Display tree with pre-order");
            System.out.println("2) Display tree with in-order");
            System.out.println("3) Display tree with post-order");
            System.out.println("4) Display tree with array-sort");
            System.out.println("5) Add an elt");
            System.out.println("6) Remove elt");
            System.out.println("7) Quit");
            if (myScanner.hasNextInt()) {
                choice = myScanner.nextInt();
            }
            myScanner.reset();
            if (choice == 1) {
                tree.preOrder(new PrintVisitor());
            } else if (choice == 2) {
                tree.inOrder(new PrintVisitor());
            } else if (choice == 3) {
                tree.postOrder(new PrintVisitor());
            } else if (choice == 4) {
                System.out.println(tree.treeSort());
            } else if (choice == 5) {
                System.out.print("New int: ");
                if (myScanner.hasNextInt()) {
                    tree.add(myScanner.nextInt());
                }
                myScanner.reset();
            } else if (choice == 6) {
                System.out.println("Int to remove: ");
                if (myScanner.hasNextInt()) {
                    tree.delete(myScanner.nextInt());
                }
                myScanner.reset();
            } else if (choice == 7) {
                System.out.println("Exiting program...");
            }
        } while (choice != 7);
    }

    public void add(E elt) {
        if (size != 0) {
            root.add(elt);
        } else {
            root = new BinarySearchTreeNode<>(elt);
            size = 1;
        }
    }

    public boolean contains(E elt) {
        return (root.contains(elt));
    }

    public void delete(E elt) {
        if (root.contains(elt)) {
            root.delete(elt);
            if (root.getElt() == null) {
                root = null;
            }
        } else {
            System.out.print("Element is non existent...");
        }
    }

    public void display() {
        root.display();
    }

    public void preOrder(Visitor visitor) {
        root.preOrder(visitor);
    }

    public void inOrder(Visitor visitor) {
        root.inOrder(visitor);
    }

    public void postOrder(Visitor visitor) {
        root.postOrder(visitor);
    }

    public ArrayList<E> treeSort() {
        ArrayList returnList = new ArrayList();
        this.inOrder(new ArrayVisitor(returnList));
        return returnList;
    }
    //Average case: O(n)
    //There is a greater chance that the tree will be more unbalanced than balanced moving its evaluation towards O(n) or worst case.
}