package com.example.thirddatastructureproject;

public class SingleNode<H extends Comparable<H>>{

    private H data;
    private SingleNode<H> next;
    private DoublyNode point;

    public SingleNode() {

    }

    public DoublyNode getPoint() {
        return point;
    }

    public void setPoint(DoublyNode point) {
        this.point = point;
    }

    public SingleNode(H data) {
        super();
        this.data = data;
    }


    public H getData() {
        return data;
    }


    public void setData(H data) {
        this.data = data;
    }


    public SingleNode<H> getNext() {
        return next;
    }


    public void setNext(SingleNode<H> next) {
        this.next = next;
    }


    @Override
    public String toString() {
        return " " + data;
    }


}