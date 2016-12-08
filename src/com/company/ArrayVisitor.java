package com.company;

import java.util.ArrayList;

public class ArrayVisitor implements Visitor {

    private ArrayList list;

    public void visit(BinarySearchTreeNode node) {
        list.add(node.getElt());
    }

    public ArrayVisitor(ArrayList list){
        this.list = list;
    }
}