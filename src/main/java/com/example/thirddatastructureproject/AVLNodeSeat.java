package com.example.thirddatastructureproject;

public class AVLNodeSeat<T extends Comparable<T>> {
    T data;
    int height=0;
    private AVLNodeSeat<T> left;
    private AVLNodeSeat<T> right;
    private DoublyNode point;

    public AVLNodeSeat(T data) {
        this.data = data;
    }

    public AVLNodeSeat() {

    }

    public DoublyNode getPoint() {
        return point;
    }

    public void setPoint(DoublyNode point) {
        this.point = point;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public AVLNodeSeat<T> getLeft() {
        return left;
    }

    public void setLeft(AVLNodeSeat<T> left) {
        this.left = left;
    }

    public AVLNodeSeat<T> getRight() {
        return right;
    }

    public void setRight(AVLNodeSeat<T> right) {
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
