package hw2;

public class DynamicArray {
    private int[] arr;
    private int capacity;
    private int size; // Last element can be indexed at size-1
    
    public DynamicArray(int cap){ // Class Constructor
        arr = new int[cap];
        capacity = cap;
        size = 0;
    }
    
    public void pushBack(int data){
        if(size >= capacity){ // If Full
            // Expand capacity
            int[] temp = new int[capacity*2];
            for (int i = 0 ; i < capacity; ++i) {
                temp[i] = arr[i];
            }
            arr = temp;
            capacity *= 2;
        }
        size++;
        arr[size-1] = data;
    }

    public int popBack(){
        int value = 0;
        if(size > 0){
            value = arr[size-1];
            arr[size-1] = 0;
            size--;
        }else{
            System.out.println("ERROR");
        }
        return value;
    }

    public int get(int i){
        if(i >= 0 && i < size){
            return arr[i];
        }else{
            System.out.println("ERROR");
        }
        return 0;
    }

    public void set(int i, int value){
        if(i >= 0 && i < size){
            arr[i] = value;
        }else{
            System.out.println("ERROR");
        }
    }
    
    public void remove(int i){
        if(i >= 0 && i < size){
            for(int j = i ; j < size-1; ++j){ // keep order
                arr[j] = arr[j+1];
            }
            size--;
        }else{
            System.out.println("ERROR");
        }
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    public int getSize(){
        return size;
    }
    
    public void printStructure(){
        String stringifiedArr = "";
        for(int i = 0 ; i < size; ++i){
            stringifiedArr += ((i == size-1)?  (""+arr[i]):("" + arr[i] + ", "));
        }
        if(size > 0){
            stringifiedArr += " ";
        }
        System.out.println("Size = " + size + ", Cap = " + capacity + ", arr = [ " + stringifiedArr + "] ");
    }
}