package com.example.thirddatastructureproject;

public class AVLNodeAverage<T extends Comparable<T>> {
    T data;
    int height=0;
    private AVLNodeAverage<T> left;
    private AVLNodeAverage<T> right;
    private SingleLinkedList<T> point = new SingleLinkedList<>();

    public AVLNodeAverage(T data) {
        this.data = data;
        this.point.insertAtHead(null);
    }

    public AVLNodeAverage() {

    }

    public SingleLinkedList getPoint() {
        return point;
    }

    public void setPoint(SingleLinkedList point) {
        this.point = point;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public AVLNodeAverage<T> getLeft() {
        return left;
    }

    public void setLeft(AVLNodeAverage<T> left) {
        this.left = left;
    }

    public AVLNodeAverage<T> getRight() {
        return right;
    }

    public void setRight(AVLNodeAverage<T> right) {
        this.right = right;
    }

    public boolean isLeaf() {
        return (left == null && right == null);
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String toString() {
        return "[" + data + "]";
    }
}
