package com.example.thirddatastructureproject;

public class CircularDoublyLinkedList<H extends Comparable<H>> {

    DoublyNode head;

    public DoublyNode getHead() {
        return head;
    }

    public void setHead(DoublyNode head) {
        this.head = head;
    }

    public void insertAtHead(H data){
        DoublyNode<H> newNode = new DoublyNode(data);
        if(head!=null){
            DoublyNode<H> headPrev = head.getPrev();
            newNode.setNext(head);
            newNode.setPrev(headPrev);
            head.setPrev(newNode);
            headPrev.setNext(head.getPrev());

        }
        else {
            newNode.setPrev(newNode);
            newNode.setNext(newNode.getPrev());
        }
        head=newNode;
    }


    public boolean find(H search) {
        DoublyNode temp = head;
        if(temp == null)
            return false;
        else {
            while(temp.getNext() != head) {
                if(temp.getData().equals(search)) {
                    return true;
                }
                temp = temp.getNext();
            }

            if(temp.getData().equals(search)) {
                return true;
            }
        }
        return false;
    }

    void deleteAllNodes() {
            head = null;
    }

    public void delete(H data) {
        DoublyNode current = head;
        if (head != null) {
            do {
                if (current.getData().equals(data)) {
                    if (current == head) {
                        head = head.getNext();
                        head.setPrev(current.getPrev());
                        current.getPrev().setNext(head);
                    } else {
                        current.getPrev().setNext(current.getNext());
                        current.getNext().setPrev(current.getPrev());
                    }
                    return;
                }
                current = current.getNext();
            } while (current != head);
        }
    }


    @Override
    public String toString() {
        DoublyNode curr=head;
        String s="";
        if(head==null){
            System.out.println("ok hi");
            return null;
        }
//        s+=curr+"⇔";
        while(curr.getNext()!=head){
            s+=curr + "⇔";
            curr=curr.getNext();
            s+="\n";
        }
        s+=curr+"⇔";

        return s;
    }

    public boolean isEmpty(){
        return (head==null);
    }


    public DoublyNode getNext(H data){
        DoublyNode<H> newNode = new DoublyNode(data);
        return newNode.getNext();
    }

    public DoublyNode getPrev(H data){
        DoublyNode<H> newNode = new DoublyNode(data);
        return newNode.getPrev();
    }
}
