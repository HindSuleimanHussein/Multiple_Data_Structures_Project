package com.example.thirddatastructureproject;

import java.util.ArrayList;

public class TawjihiDS<H extends Comparable<H>> {
    CircularDoublyLinkedList<Tawjihi> doublyLinkedList = new CircularDoublyLinkedList<>();
    AVLNodeSeat<Tawjihi> avlNodeSeat = new AVLNodeSeat<>();
    AVLNodeAverage<Tawjihi> avlNodeAverage = new AVLNodeAverage<>();
    AVLTreeSeat<Integer> avlTreeSeat=new AVLTreeSeat<>();
    AVLTreeAverage<Double> avlTreeAverage = new AVLTreeAverage<>();
    DoublyNode head = doublyLinkedList.getHead();

    public void insert(Tawjihi data) {
        doublyLinkedList.insertAtHead(data);
        avlNodeSeat.setPoint(doublyLinkedList.getHead());
        avlTreeSeat.insert(data.getSeatNumber(),doublyLinkedList);
        avlTreeAverage.insert(data.getAverage(), doublyLinkedList);




    }

    public void update(int data, double average){
        AVLNodeSeat node = avlTreeSeat.find(data);
        if(node.getPoint()!=null) {
            AVLNodeAverage nodeTwo = avlTreeAverage.find(node.getPoint().getData().getAverage());
            node.getPoint().getData().setAverage(average);
            avlTreeAverage.insert(average, doublyLinkedList);
            System.out.println(avlTreeAverage.levelOrder());

            SingleNode curr=nodeTwo.getPoint().getHead();
            SingleNode prev;

            if(curr.getPoint().getData().getSeatNumber()==data){
                nodeTwo.getPoint().delete(curr.getData());

            }
            else{
                while(curr!=null ){
                    curr=curr.getNext();
                    prev=curr;
                    if(curr!=null) {
                        if (curr.getPoint().getData().getSeatNumber() == data) {
                            prev.setNext(curr.getNext());
                            nodeTwo.getPoint().delete(curr.getData());
                            break;

                        }
                    }
                    else{
                        break;
                    }
                }
            }
            if(nodeTwo.getPoint().isEmpty()) {
                avlTreeAverage.delete(node.getPoint().getData().getAverage());
                System.out.println(avlTreeAverage.levelOrder());
            }

        }

    }

    public void delete(int data) {
        AVLNodeSeat node = avlTreeSeat.find(data);
        if(node.getPoint()!=null) {
            if(node.getPoint()== doublyLinkedList.getHead()){
                doublyLinkedList.setHead(node.getPoint().getNext());
            }
            System.out.println(node.getPoint().getPrev());
            System.out.println(node.getPoint().getNext());
            node.getPoint().getPrev().setNext(node.getPoint().getNext());
            node.getPoint().getNext().setPrev(node.getPoint().getPrev());

            if(doublyLinkedList.isEmpty()){
                doublyLinkedList.deleteAllNodes();
            }

            avlTreeSeat.delete(data);
            AVLNodeAverage nodeTwo = avlTreeAverage.find(node.getPoint().getData().getAverage());
            SingleNode curr=nodeTwo.getPoint().getHead();
            SingleNode prev;

            if(curr.getPoint().getData().getSeatNumber()==data){
                nodeTwo.getPoint().delete(curr.getData());

            }
            else{
                while(curr!=null ){
                    curr=curr.getNext();
                    prev=curr;
                    if(curr!=null) {
                        if (curr.getPoint().getData().getSeatNumber() == data) {
                            prev.setNext(curr.getNext());
                            nodeTwo.getPoint().delete(curr.getData());
                            break;

                        }
                    }
                    else{
                        break;
                    }
                }
            }
            if(nodeTwo.getPoint().isEmpty()) {
                avlTreeAverage.delete(node.getPoint().getData().getAverage());
            }


        }

        System.out.println(doublyLinkedList);
        System.out.println(avlTreeAverage.levelOrder());


    }

    public AVLNodeSeat find(int search) {
        return avlTreeSeat.find(search);
    }

    public DoublyNode findNode(int search){
        while(avlNodeSeat.getPoint()!=null) {
            if (avlNodeSeat.getPoint().getData().getSeatNumber() == search) {
                return avlNodeSeat.getPoint();
            }
            avlNodeSeat.setPoint(avlNodeSeat.getPoint().getNext());
        }
        return null;
    }

    public String toString(){
        if(doublyLinkedList.isEmpty()){
            return " ";
        }
        return doublyLinkedList.toString();

    }

    public String getAll(double average){
        String s="";
        DoublyNode nodeSingle = doublyLinkedList.getHead();
        if (nodeSingle.getPrev().getData().getAverage() == average) {
            s += nodeSingle.getPrev() + "\n";
        }

        while(nodeSingle.getNext()!= doublyLinkedList.getHead()) {
            if (nodeSingle.getData().getAverage() == average) {
                s += nodeSingle+ "\n";

            }
            nodeSingle=nodeSingle.getNext();
        }
        return s;
    }

    public ArrayList<Integer> forAvlSeatString(){
        if(avlTreeSeat.levelOrder()==null){
            return null;
        }

        return avlTreeSeat.levelOrder();

    }

    public ArrayList<Double> forAvlAverageString(){
        return avlTreeAverage.levelOrder();
    }


}
