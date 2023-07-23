package com.example.thirddatastructureproject;

public class SingleLinkedList<H extends Comparable<H>> {
    private SingleNode<H> head;

    public SingleNode<H> getHead() {
        return head;
    }

    public void setHead(SingleNode<H> head) {
        this.head = head;
    }

    public void insertAtHead(H data) {
        SingleNode<H> newNode = new SingleNode<>(data);
        newNode.setNext(head);
        head = newNode;

    }

    public void insertAtEnd(H data) {
        SingleNode<H> newNode = new SingleNode(data);
        SingleNode<H> current = head;
        if (current == null) {
            insertAtHead(data);
        } else {
            while (current.getNext() != null) {
                current = current.getNext();
            }

            current.setNext(newNode);
        }

    }

    public void insertSorted(H data) {
        SingleNode<H> newNode = new SingleNode<>(data);
        SingleNode<H> curr = head;
        SingleNode<H> prev = null;
        if (head == null) {
            head = newNode;
        } else {
            while (curr != null && curr.getData().compareTo(data) < 0) {
                prev = curr;
                curr = curr.getNext();

            }
            if (prev == null) {
                head = newNode;
                head.setNext(curr);
            } else {
                newNode.setNext(curr);
                prev.setNext(newNode);

            }
        }
    }

    public SingleNode find(H data){
        SingleNode<H> curr = head;
        if(head==data){
            return head;
        }
        while(curr!=null){
            if(curr.getData()==data)
                return curr;

            curr=curr.getNext();
        }

        return null;
    }

    public void delete(H data) {
        SingleNode<H> curr = head;
        SingleNode<H> prev = null;
        if (curr != null && curr.getData() == data) {
            head = curr.getNext();
            return;
        }

        while (curr != null && curr.getData() != data) {
            prev = curr;
            curr = curr.getNext();
        }

        if (curr == null)
            return;


        prev.setNext(curr.getNext());

    }

    public boolean isEmpty(){
        return (head==null);
    }


    @Override
    public String toString() {
        String s = "";
        SingleNode<H> curr = head;
        if(curr==null){
            return null;
        }
        while (curr != null) {
            s += curr;
            curr = curr.getNext();
        }
        return s + "";
    }

}
