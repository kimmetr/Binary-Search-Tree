package com.company;

public class BinarySearchTreeNode<E extends Comparable<E>> {
    private E elt;
    private BinarySearchTreeNode<E> left;
    private BinarySearchTreeNode<E> right;

    public BinarySearchTreeNode(E elt) {
        this.elt = elt;
    }

    public void add(E elt) {
        if (elt.compareTo(this.elt) < 0) {
            if (left != null) {
                left.add(elt);
            } else {
                left = new BinarySearchTreeNode<>(elt);
            }
        } else if (elt.compareTo(this.elt) > 0) {
            if (right != null) {
                right.add(elt);
            } else {
                right = new BinarySearchTreeNode<>(elt);
            }
        } else {
            System.out.println("Duplicates are not allowed!");
        }
    }

    public boolean contains(E elt) {
        if (elt.compareTo(this.elt) == 0) {
            return true;
        } else if ((elt.compareTo(this.elt) < 0) && (left != null)) {
            return left.contains(elt);
        } else if ((elt.compareTo(this.elt) > 0) && (right != null)) {
            return right.contains(elt);
        }
        return false;
    }

    public void display() {
        if (left != null) {
            left.display();
        }
        System.out.println(this.elt);
        if (right != null) {
            right.display();
        }
    }

    public void delete(E elt) {
        BinarySearchTreeNode<E> currentNode = this.getNode(elt);
        BinarySearchTreeNode<E> parentNode = this.getParent(elt);
        BinarySearchTreeNode<E> traversalNode;
        BinarySearchTreeNode<E> repNode;
        if ((currentNode.getLeft() == null) && (currentNode.getRight() == null)) {
            if (parentNode == null) {
                this.elt = null;
            } else if (parentNode.getLeft().getElt().compareTo(currentNode.getElt()) == 0) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }
        } else {
            if (currentNode.getLeft() != null) {
                traversalNode = currentNode.getLeft();
                if (traversalNode.getRight() != null) {
                    repNode = traversalNode.getRight();
                } else {
                    repNode = traversalNode;
                }
                while (repNode.getRight() != null) {
                    traversalNode = repNode;
                    repNode = repNode.getRight();
                }
                if (traversalNode.equals(repNode)) {
                    currentNode.setLeft(repNode.getLeft());
                } else {
                    traversalNode.setRight(null);
                }
            } else {
                traversalNode = currentNode.getRight();
                if (traversalNode.getLeft() != null) {
                    repNode = traversalNode.getLeft();
                } else {
                    repNode = traversalNode;
                }
                while (repNode.getLeft() != null) {
                    traversalNode = repNode;
                    repNode = repNode.getLeft();
                }
                if (traversalNode.equals(repNode)) {
                    currentNode.setRight(repNode.getRight());
                } else {
                    traversalNode.setLeft(null);
                }
            }
            currentNode.setElt(repNode.getElt());
        }

    }

    private BinarySearchTreeNode<E> getNode(E elt) {
        if (elt.compareTo(this.elt) == 0) {
            return this;
        } else if ((elt.compareTo(this.elt) < 0) && (left != null)) {
            return left.getNode(elt);
        } else if ((elt.compareTo(this.elt) > 0) && (right != null)) {
            return right.getNode(elt);
        }
        return null;
    }

    //Added these for my deletion method, cannot call variables without them.
    public BinarySearchTreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(BinarySearchTreeNode<E> elt) {
        left = elt;
    }

    public BinarySearchTreeNode<E> getRight() {
        return right;
    }

    public void setRight(BinarySearchTreeNode<E> elt) {
        right = elt;
    }

    public BinarySearchTreeNode<E> getParent(E elt) {
        BinarySearchTreeNode<E> parent = null;
        BinarySearchTreeNode<E> child = this;
        while ((child.getElt() != elt) && !((child.getLeft() == null) && (child.getRight() == null))) {
            parent = child;
            if ((child.getLeft() != null) && (child.getLeft().contains(elt))) {
                child = child.getLeft();
            } else {
                child = child.getRight();
            }
        }
        return parent;
    }

    public E getElt() {
        return elt;
    }

    public void setElt(E elt) {
        this.elt = elt;
    }

    public void preOrder(Visitor visitor) {
        visitor.visit(this);
        if (left != null) {
            left.preOrder(visitor);
        }
        if (right != null) {
            right.preOrder(visitor);
        }
    }

    public void inOrder(Visitor visitor) {
        if (left != null) {
            left.inOrder(visitor);
        }
        visitor.visit(this);
        if (right != null) {
            right.inOrder(visitor);
        }
    }

    public void postOrder(Visitor visitor) {
        if (left != null) {
            left.postOrder(visitor);
        }
        if (right != null) {
            right.postOrder(visitor);
        }
        visitor.visit(this);
    }
}