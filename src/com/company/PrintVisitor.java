package com.company;

public class PrintVisitor implements Visitor {
    public void visit(BinarySearchTreeNode node) {
        System.out.println(node.getElt());
    }
}