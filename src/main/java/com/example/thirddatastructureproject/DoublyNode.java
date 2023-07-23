package com.example.thirddatastructureproject;

public class DoublyNode<H extends Comparable<H>>{
    private Tawjihi data;
    private DoublyNode prev, next;

    public DoublyNode(H data){
        this.data= (Tawjihi) data;
    }

    public DoublyNode() {

    }

    public Tawjihi getData() {
        return data;
    }

    public void setData(H data) {
        this.data = (Tawjihi) data;
    }

    public DoublyNode getPrev() {
        return prev;
    }

    public void setPrev(DoublyNode prev) {
        this.prev = prev;
    }

    public DoublyNode getNext() {
        return next;
    }

    public void setNext(DoublyNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return data+" ";
    }


    public int compareTo(Tawjihi data) {
        return Integer.compare(this.getData().getSeatNumber(), data.getSeatNumber());
    }
}
