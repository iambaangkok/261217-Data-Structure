public class Heap {
    
    int capacity;
    Node[] arr;
    int size;
    
    boolean isMinHeap;  // true if minHeap, false if maxHeap
    
    int timer;    // For each push, the timer will increase by 1
    
    public Heap(boolean isMinHeap, int cap){
        // Initialize the heap here
        // Don't forget that we will build the binary heap using
        // the concept of "Complete binary tree based on the array implementation"
        // The 0 index will not be used, The index starts from 1 (remember?)

        capacity = cap;
        arr = new Node[capacity+1];
        size = 0;
        this.isMinHeap = isMinHeap;
    }
    public Node top(){
        if(size>=1){
            return arr[1];
        }
        return null;
    }
    
    public void push(Node node){
        //If array is full, increase capacity
        if(size == capacity){
            Node[] newArr = new Node[capacity*2 + 1];
            for(int i = 1 ; i <= capacity; ++i){
                newArr[i] = arr[i];
            }
            capacity = capacity*2;
            arr = newArr;
        }


        // Increase timer each time you push something into the Queue
        timer++;
        node.timestamp = timer; // Stamp the time number to the node
        
        // To do:
        // 1. Push the new node at the end of the array (via back pointer)
        node.timestamp = timer;
        node.isMinHeap = isMinHeap;
        arr[size+1] = node;

        // 2. Sift (percolate) it up the heap
        //      * Check priority of the current node with its parent
        //      * Swap the current node if the priority is higher than the parent
        //      * Repeat the process until reaching the root or there is no swap (Pls use while loop)
        int currIndex = size+1;
                    // if(isMinHeap){
                    //     System.out.println("min push " + (size+1));
                    // }else{
                    //     System.out.println("max push" + (size+1));
                    // }
                    // for(int i = 1 ; i <= size; ++i){
                    //     System.out.print(arr[i].price+","+arr[i].amount + " | ");
                    // }System.out.println("   size = " + size);
        while(currIndex > 1){
            int parentIndex = currIndex/2;
            if(arr[currIndex].compare(arr[parentIndex])){ // if curr < parent (minHeap)
                swap(currIndex, parentIndex);
                currIndex = parentIndex;
            }else{
                break;
            }
        }

        // 3. Increase the heap size
        size++;

                    // for(int i = 1 ; i <= size; ++i){
                    //     System.out.print(arr[i].price+","+arr[i].amount + " | ");
                    // }System.out.println("   size = " + size);
    }
    
    public Node pop(){
                    // for(int i = 1 ; i <= size; ++i){
                    //     System.out.print(arr[i].price+","+arr[i].amount + " | ");
                    // }System.out.println("   size = " + size);
        // To do:
        // 1. Mark the root for return (Don't forget to return this node)
        Node topNode = top();
        // 2. Move the last node to the root (change pointer, set null)
                    // for(int i = 1 ; i <= size; ++i){
                    //     System.out.print(arr[i].price+","+arr[i].amount + " | ");
                    // }System.out.println("   size = " + size);

        arr[1] = arr[size];
        arr[size] = null;
        // 3. Decrease the heap size
        size--;
        // 4. Sift (percolate) it down the heap
        //      * Check priority of the current node with both children
        //      * Swap the current node with the lower priority child
        //      * Repeat the process until the node has no child or there is no swap (Pls use while loop)
        int currIndex = 1;
        int lchildIndex = (2*currIndex);
        int rchildIndex = (2*currIndex+1);

                    // if(isMinHeap){
                    //     System.out.println("min pop");
                    // }else{
                    //     System.out.println("max pop");
                    // }
        while(arr[lchildIndex] != null || arr[rchildIndex] != null){
            // check which child is the more min
            if((arr[lchildIndex] != null && arr[rchildIndex] == null ) || 
                arr[lchildIndex].compare(arr[rchildIndex])){  // left child is more min
                            // System.out.println("l min");
                if(!arr[currIndex].compare(arr[lchildIndex])){ // if curr < child
                    swap(currIndex, lchildIndex);
                }else{
                    break;
                }
                currIndex = lchildIndex;
            }else if((arr[lchildIndex] == null && arr[rchildIndex] != null ) || 
            arr[rchildIndex].compare(arr[lchildIndex])){ // right child is more min
                            // System.out.println("r min");
                if(!arr[currIndex].compare(arr[rchildIndex])){ // if curr < child
                    swap(currIndex, rchildIndex);
                }else{
                    break;
                }
                currIndex = rchildIndex;
            }
            
            lchildIndex = (2*currIndex);
            rchildIndex = (2*currIndex+1);
        }

                    // for(int i = 1 ; i <= size; ++i){
                    //     System.out.print(arr[i].price+","+arr[i].amount + " | ");
                    // }System.out.println("   size = " + size);
            
        return topNode;
    }

    // This is an optional function, you may use it if you know what it is
    // This function is complete, no need to edit
    public void swap(int index1, int index2){
        // System.out.println("swap " + arr[index1].price + " " + arr[index2].price);
        Node temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

}
