package hw4;

public class Queue implements List{
    // Implement Queue using Linked List with tail
    
    Node head;// pop here
    Node tail;// push here
    
    public void push(Node node){
        if (head == null){
            // set node as head and tail
            head = node;
            tail = node;
        }else{
            // set node as next of tail then set tail to be node
            tail.next = node;
            tail = node;
        }
    }
    
    public void pop(){ // pop back 
        if (head != null){ //dereferece head
            head = head.next;
        }else{
            System.out.println("Error: Queue Underflow");
        }
    }
    
    public Node top(){
        if(head != null){
            return head;
        }
        return null;
    }
    
}