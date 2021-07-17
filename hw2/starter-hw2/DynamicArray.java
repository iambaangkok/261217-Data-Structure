package hw2;

public class DynamicArray {
    private int[] arr;
    private int capacity;
    private int size; // Last element can be indexed at size-1
    
    public DynamicArray(int cap){ // Class Constructor
        arr = new int[cap];
        capacity = cap;
    }
    
    public void pushBack(int data){
        // FIXED THIS
    }
    public int popBack(){
        // FIXED THIS
        return 0;
    }

    public int get(int i){
        // FIXED THIS
        return 0;
    }
    public void set(int i, int value){
        // FIXED THIS
    }
    
    public void remove(int i){
        // FIXED THIS
    }
    
    public boolean isEmpty(){
        // FIXED THIS
        return false;
    }
    
    public int getSize(){
        // FIXED THIS
        return 0;
    }
    
    public void printStructure(){
        // FIXED THIS
        System.out.print("Size = " + size + ", Cap = " + capacity + ", arr = [ ] ");
    }
}
