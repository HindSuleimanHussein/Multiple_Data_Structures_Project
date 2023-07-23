package com.example.thirddatastructureproject;
class QNode<H> {
    private QNode<H> next;
    private H data;

    public QNode(H data) {
        this.data=data;
    }

    public H getData() {
        return data;
    }

    public void setData(H data) {
        this.data = data;
    }

    public QNode<H> getNext() {
        return next;
    }

    public void setNext(QNode<H> next) {
        this.next = next;
    }
}

