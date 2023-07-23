package com.example.thirddatastructureproject;

import java.util.ArrayList;

public class AVLTreeSeat<H extends Comparable<H>>{
    AVLNodeSeat<H> root;

    public AVLTreeSeat() {
    }

    public void insert(H key, CircularDoublyLinkedList DLinked) {
        root = insert(root, key);
    }

    private AVLNodeSeat<H> insert(AVLNodeSeat<H> root, H key) {
        if (root == null) {
            return new AVLNodeSeat<H>(key);
            //root.setPoint(DLinked.head);
        }
        if (key.compareTo(root.getData()) < 0) {
            root.setLeft(insert(root.getLeft(), key));
        } else {
            root.setRight(insert(root.getRight(), key));
        }
        return rebalance(root);
    }

    private AVLNodeSeat rebalance(AVLNodeSeat root){
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

    private int getBalance(AVLNodeSeat<H> root) {
        if (root == null) {
            return 0;
        }
        return getHeight(root.getLeft()) - getHeight(root.getRight());
    }

    private int getHeight(AVLNodeSeat<H> curr) {
        if (curr == null)
            return 0;
        if (curr.isLeaf())
            return 1;
        else
            return Math.max(1 + getHeight(curr.getLeft()), 1 + getHeight(curr.getRight()));
    }

    public AVLNodeSeat<H> rotateRight(AVLNodeSeat<H> node) {
        AVLNodeSeat<H> nodeC = node.getLeft();
        node.setLeft(nodeC.getRight());
        nodeC.setRight(node);
        return nodeC;
    }

    public AVLNodeSeat<H> rotateLeft(AVLNodeSeat<H> node) {
        AVLNodeSeat<H> nodeC = node.getRight();
        node.setRight(nodeC.getLeft());
        nodeC.setLeft(node);
        return nodeC;
    }

    public AVLNodeSeat<H> rotateRightLeft(AVLNodeSeat<H> node) {
        AVLNodeSeat<H> nodeC = node.getRight();
        node.setRight(rotateRight(nodeC));
        return rotateLeft(node);
    }

    public AVLNodeSeat<H> rotateLeftRight(AVLNodeSeat<H> node) {
        AVLNodeSeat<H> nodeC = node.getLeft();
        node.setLeft(rotateLeft(nodeC));
        return rotateRight(node);
    }

    public boolean isEmpty() {
        return root==null;
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(AVLNodeSeat<H> node) {
        if (node != null) {
            if (node.getLeft() != null)
                traverseInOrder(node.getLeft());
            System.out.print(node + " ");
            if (node.getRight() != null)
                traverseInOrder(node.getRight());
        }
    }

    public AVLNodeSeat delete(H data) {
        AVLNodeSeat temp = deleteSuper(data);
        if(temp!= null){
            AVLNodeSeat rootNode = root;
            root = rebalance(rootNode);
        }
        return temp;
    }

    private AVLNodeSeat<H> deleteSuper(H data) {
        AVLNodeSeat current = root;
        AVLNodeSeat parent = root;
        boolean isLeftChild = false;

        if(isEmpty())
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
            AVLNodeSeat<H> successor = getSuccessor(current);
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

    private AVLNodeSeat<H> getSuccessor(AVLNodeSeat node) {
        AVLNodeSeat parentOfSuccessor = node;
        AVLNodeSeat successor = node;
        AVLNodeSeat current = node.getRight();
        while (current != null) {
            parentOfSuccessor = successor;
            successor = current;
            current = current.getLeft();
        }
        if (successor.getData().compareTo(node.getRight().getData())!=0) { // fix successor connections
            parentOfSuccessor.setLeft(successor.getRight());
            successor.setRight(node.getRight());
        }
        return successor;
    }

    public AVLNodeSeat find(H data) {
        return find(data, root);
    }

    public AVLNodeSeat find(H data, AVLNodeSeat node) {
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

    public ArrayList<Integer> levelOrder() {
        return levelOrder(root);
    }
    private ArrayList<Integer> levelOrder(AVLNodeSeat node) {
        Queue<AVLNodeSeat> queue = new Queue<>();
        ArrayList<Integer>ans=new ArrayList<>();
        queue.enqueue(node);
        while(!queue.isEmpty()){
            node=queue.dequeue();
            if(node.getLeft()!=null){
                queue.enqueue(node.getLeft());
            }
            if(node.getRight()!=null){
                queue.enqueue(node.getRight());
            }
            ans.add((Integer) node.getData());
        }
        return ans;
    }

    public String printHeight(){
        String s="";
        int h=getHeight(root);
        s= String.valueOf(h);
        return s;
    }

    public void emptyOut(){
        root=null;
    }


}
