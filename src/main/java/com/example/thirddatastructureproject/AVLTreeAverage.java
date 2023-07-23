package com.example.thirddatastructureproject;

import java.util.ArrayList;

public class AVLTreeAverage<H extends Comparable<H>> {
    AVLNodeAverage<H> root;

    SingleNode<Tawjihi> singleLinkedNode = new SingleNode<>();
    CircularDoublyLinkedList<Tawjihi> doublyLinkedList = new CircularDoublyLinkedList<>();

    public AVLTreeAverage() {
    }

    public void insert(H key, CircularDoublyLinkedList doublyLinkedList) {
        if (root == null) {
            root = new AVLNodeAverage<>(key);
            root.getPoint().getHead().setPoint(doublyLinkedList.head);
        } else
            insert(root, key, doublyLinkedList);

    }

    private AVLNodeAverage<H> insert(AVLNodeAverage<H> root, H key, CircularDoublyLinkedList doubly) {
        if(root == null){
            return new AVLNodeAverage<>(key);
        }
        if (key.compareTo(root.getData()) < 0) {
            root.setLeft(insert(root.getLeft(), key, doubly));
            root.getLeft().getPoint().getHead().setPoint(doubly.head);
        } else if (key.compareTo(root.getData()) == 0) {
            root.getPoint().insertAtHead(null);
            root.getPoint().getHead().setPoint(doubly.head);
        } else {
            root.setRight(insert(root.getRight(), key,doubly));
            root.getRight().getPoint().getHead().setPoint(doubly.head);
        }
        return rebalance(root);
    }

    private AVLNodeAverage rebalance(AVLNodeAverage root) {
        if (root == null) {
            return root;
        }
        int balance = getBalance(root);
        if (balance > 1) {
            if (getBalance(root.getLeft()) > 0) {
                root = rotateRight(root);
            } else {
                root = rotateLeftRight(root);
            }
        } else if (balance < -1) {
            if (getBalance(root.getRight()) < 0) {
                root = rotateLeft(root);
            } else {
                root = rotateRightLeft(root);
            }
        }
        return root;
    }

    private int getBalance(AVLNodeAverage<H> root) {
        if (root == null) {
            return 0;
        }
        return getHeight(root.getLeft()) - getHeight(root.getRight());
    }

    private int getHeight(AVLNodeAverage<H> curr) {
        if (curr == null)
            return 0;
        if (curr.isLeaf())
            return 1;
        else
            return Math.max(1 + getHeight(curr.getLeft()), 1 + getHeight(curr.getRight()));
    }

    public AVLNodeAverage<H> rotateRight(AVLNodeAverage<H> node) {
        AVLNodeAverage<H> nodeC = node.getLeft();
        node.setLeft(nodeC.getRight());
        nodeC.setRight(node);
        return nodeC;
    }

    public AVLNodeAverage<H> rotateLeft(AVLNodeAverage<H> node) {
        AVLNodeAverage<H> nodeC = node.getRight();
        node.setRight(nodeC.getLeft());
        nodeC.setLeft(node);
        return nodeC;
    }

    public AVLNodeAverage<H> rotateRightLeft(AVLNodeAverage<H> node) {
        AVLNodeAverage<H> nodeC = node.getRight();
        node.setRight(rotateRight(nodeC));
        return rotateLeft(node);
    }

    public AVLNodeAverage<H> rotateLeftRight(AVLNodeAverage<H> node) {
        AVLNodeAverage<H> nodeC = node.getLeft();
        node.setLeft(rotateLeft(nodeC));
        return rotateRight(node);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(AVLNodeAverage<H> node) {
        if (node != null) {
            if (node.getLeft() != null)
                traverseInOrder(node.getLeft());
            System.out.print(node + " ");
            if (node.getRight() != null)
                traverseInOrder(node.getRight());
        }
    }

    public AVLNodeAverage delete(H data) {
        AVLNodeAverage temp = deleteSuper(data);
        if (temp != null) {
            AVLNodeAverage rootNode = root;
            root = rebalance(rootNode);
        }

        return temp;
    }

    private AVLNodeAverage<H> deleteSuper(H data) {
        AVLNodeAverage current = root;
        AVLNodeAverage parent = root;
        boolean isLeftChild = false;

        if (isEmpty())
            return root;


        while (current != null && !current.getData().equals(data)) {
            parent = current;
            if (data.compareTo((H) current.getData()) < 0) {
                current = current.getLeft();
                isLeftChild = true;
            } else {
                current = current.getRight();
                isLeftChild = false;
            }
        }
        if (current == null)
            return null;


        // case 1: node is a leaf
        if (!current.hasLeft() && !current.hasRight()) {
            if (current == root) // tree has one node
                root = null;

            else {
                if (isLeftChild)
                    parent.setLeft(null);

                else
                    parent.setRight(null);
            }
        } else if (current.hasLeft() && !current.hasRight()) { // current has left child only
            if (current == root) {
                root = current.getLeft();
            } else if (isLeftChild) {
                parent.setLeft(current.getLeft());
            } else {
                parent.setRight(current.getLeft());
            }
        } else if (current.hasRight() && !current.hasRight()) { // current has right child only
            if (current == root) {
                root = current.getRight();
            } else if (isLeftChild) {
                parent.setLeft(current.getRight());
            } else {
                parent.setRight(current.getRight());
            }
        } else {
            AVLNodeAverage<H> successor = getSuccessor(current);
            if (current == root)
                root = successor;
            else if (isLeftChild) {
                parent.setLeft(successor);
            } else {
                parent.setRight(successor);
            }
            successor.setLeft(current.getLeft());


        }

        return current;
    }

    private AVLNodeAverage<H> getSuccessor(AVLNodeAverage node) {
        AVLNodeAverage parentOfSuccessor = node;
        AVLNodeAverage successor = node;
        AVLNodeAverage current = node.getRight();
        while (current != null) {
            parentOfSuccessor = successor;
            successor = current;
            current = current.getLeft();
        }
        if (successor.getData().compareTo(node.getRight().getData()) != 0) { // fix successor connections
            parentOfSuccessor.setLeft(successor.getRight());
            successor.setRight(node.getRight());
        }
        return successor;
    }

    public AVLNodeAverage find(H data) {
        return find(data, root);
    }

    public AVLNodeAverage find(H data, AVLNodeAverage node) {
        if (node != null) {
            if (node.getData().compareTo(data) == 0)
                return node;
            else if (node.getData().compareTo(data) > 0 && node.hasLeft())
                return find(data, node.getLeft());
            else if (node.getData().compareTo(data) < 0 && node.hasRight())
                return find(data, node.getRight());
        }
        return null;
    }

    public ArrayList<Double> levelOrder() {
        return levelOrder(root);
    }

    private ArrayList<Double> levelOrder(AVLNodeAverage node) {
        Queue<AVLNodeAverage> queue = new Queue<>();
        ArrayList<Double> ans = new ArrayList<>();
        queue.enqueue(node);
        while (!queue.isEmpty()) {
            node = queue.dequeue();
            if (node.getLeft() != null) {
                queue.enqueue(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.enqueue(node.getRight());
            }
            ans.add((Double) node.getData());
        }
        return ans;
    }

    public String printHeight() {
        String s = "";
        int h = getHeight(root);
        s = String.valueOf(h);
        return s;
    }

    public void emptyOut() {
        root = null;
    }


}