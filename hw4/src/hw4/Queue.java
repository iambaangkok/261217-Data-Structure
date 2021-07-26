package hw4;

public class Queue implements List{
    // Implement Queue using Linked List with tail
    Node head;
    Node tail;
    
    public void push(Node node){
        if (head == null){
            // set node as head and tail
            head = node;
            tail = node;
        }else{
            // set node as head with a bit of rewiring, dont mess with tail
            node.next = head;
            head = node;
        }
    }
    
    public void pop(){ // pop back
        if (head != null){
            if (head != tail){
                // loop to the node before tail
                Node currNode = head;
                while(currNode.next != tail){
                    currNode = currNode.next;
                }
                // then set tail to that node instead (dereference tail)
                tail = currNode;
            }else{
                // dereference both head and tail
                head = null;
                tail = null;
            }
        }else{
            System.out.println("Error: Queue Underflow");
        }
    }
    
    public Node top(){
        if(tail != null){
            return tail;
        }
        return null;
    }
    
}