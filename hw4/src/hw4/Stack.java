package hw4;

public class Stack implements List{
    // Implement Stack using Linked List without tail
    Node head;
    
    public void push(Node node){ // push front
        if (head == null){
            // set node as head
            head = node;
        }else{
            // set node as head with a bit of rewireing
            node.next = head;
            head = node;
        }
    }
    
    public void pop(){ // pop front
        if (head != null){
            // set head tobe next node
            if(head.next != null){
                head = head.next;
            }else{
                head = null;
            }
        }else{
            System.out.println("Error: Stack Underflow");
        }
    }
    
    public Node top(){
        if(head != null){
            return head;
        }
        return null;
    }
    
}