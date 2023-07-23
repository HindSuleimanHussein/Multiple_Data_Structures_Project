package com.example.thirddatastructureproject;
public class Queue <H>{
    private QNode<H> last;

    public void enqueue(H data){
        QNode<H> newNode = new QNode<>(data);
        if(isEmpty())
            newNode.setNext(newNode);
        else{
            newNode.setNext(last.getNext());
            last.setNext(newNode);
        }
        last=newNode;
    }

    public H getFront(){
        if(!isEmpty())
            return last.getNext().getData();
        return null;
    }

    public H dequeue(){
        H data;
        if(!isEmpty()){
            if(last.getNext()==last){
                data=last.getData();
                last=null;
            }
            else{
                data=last.getNext().getData();
                last.setNext(last.getNext().getNext());
            }
            return data;
        }
        return null;
    }


    public boolean isEmpty() {
        return (last==null);
    }


    public void clear () {
        last = null;
    }

    @Override
    public String toString() {
        QNode c = null;
        String s="First-->";
        if(last!=null)
            c = last.getNext();
        while(true){
            s+=c.getData()+"-->";

            if(c==last)
                break;

            c=c.getNext();

        }
        return s;
    }


}