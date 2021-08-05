package hw5;

public class Queue {
    Node[] arr; // circular Queue
    int capacity;
    int front;
    int back;
    int size;
    
    public Queue(int cap){
        arr = new Node[cap];
        capacity = cap; // this queue will not expand in capacity
        front = 0;
        back = 0;
    }
    
    public void enqueue(Node node){
        if (!isFull()){
            // add to queue at index:back then increase back
            arr[back] = node;
            back = (back+1)%capacity;

            size++;
        }else{
            System.out.println("Queue Overflow!!!");
        }
    }
    
    public Node dequeue(){
        
        if (!isEmpty()){
            // remove and return at index:front then move front
            Node frontNode = arr[front];
            arr[front] = null;// dont forget to dereference
            front = (front+1)%capacity;

            size--;
            return frontNode;
        }else{
            System.out.println("Queue Underflow!!!");
        }
        return new Node(0);
    }
    
    public boolean isEmpty(){
        return size == 0; // self explanatory
    }
    
    public boolean isFull(){
        return size == capacity; // self explanatory
    }
    
    public void printCircularIndices(){
        System.out.println("Front index = " + front + " Back index = " + back);
    }
    
    public void printQueue(){
        if (!isEmpty()){
            System.out.print("[Front] ");
            // loop through circular queue
            int i = front;
            int ss = size;
            while(i != back || ss > 0){
                System.out.print("" + arr[i].data + " ");
                i = (i+1)%capacity;
                ss--;
            }
            System.out.println("[Back]");
        }else{
            System.out.println("Empty Queue!!!");
        }
    }
}
