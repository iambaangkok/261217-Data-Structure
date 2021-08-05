package hw5;

public class Stack {
    Node[] arr; // regular array
    int capacity;
    int size;

    public Stack(int cap){
        arr = new Node[cap];
        capacity = cap; // this will not expand in capacity
        size = 0;
    }
    
    public void push(Node node){
        if (!isFull()){
            // add to stack at index:size then increase size
            arr[size] = node;
            size++;
        }else{
            System.out.println("Stack Overflow!!!");
        }
    }
    public Node pop(){
        if (!isEmpty()){
            // remove and return at index:size-1 then decrease size
            Node top = arr[size-1];
            arr[size-1] = null; // dereference
            size--;
            return top;
        }else{
            System.out.println("Stack Underflow!!!");
        }
        return new Node(0);
    }
    public boolean isFull(){
        return size == capacity; // self explanatory
    }
    public boolean isEmpty(){
        return size == 0; // self explanatory
    }
    
    public void printStack(){
        if (!isEmpty()) {
            System.out.print("[Bottom] ");
            // loop from index: 0 to size-1
            for(int i = 0 ; i < size; ++i){
                System.out.print("" + arr[i].data + " ");
            }
            System.out.println("[Top]");
        } else {
            System.out.println("Empty Stack!!!");
        }
    }
}
