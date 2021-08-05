package hw5;

public class Queue {
    Node[] arr; // circular Queue
    int capacity;
    int front;
    int back;
    int size;
    
    public Queue(int cap){

    }
    
    public void enqueue(Node node){
        if (!isFull()){
            // do something
            
        }else{
            System.out.println("Queue Overflow!!!");
        }
    }
    
    public Node dequeue(){
        
        if (!isEmpty()){
            // do something
            
        }else{
            System.out.println("Queue Underflow!!!");
        }
        return null; // fix this (out of place)
    }
    
    public boolean isEmpty(){
        return false; // fix this
    }
    
    public boolean isFull(){
        return false; // fix this
    }
    
    public void printCircularIndices(){
        System.out.println("Front index = " + front + " Back index = " + back);
    }
    
    public void printQueue(){
        if (!isEmpty()){
            System.out.print("[Front] ");
            // do something here
            System.out.println("[Back]");
        }else{
            System.out.println("Empty Queue!!!");
        }
    }
}
