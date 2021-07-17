package hw3;

public class DoublyLinkedList {
    Node head;
    Node tail;
    String listName;
    
    public DoublyLinkedList(String name){

    }
    
    public void popBack() {
      if (isEmpty()){
 
        }else{

        }
    }
    
    public void popFront(){
        if (isEmpty()){
 
        }else{

        }
    }
    
    public Node topFront(){
        if (isEmpty()){
            return new Node();
        } else {
            return new Node();
        }
    }
    
    public Node topBack(){
        if (isEmpty()){
            return new Node();
        } else {
            return new Node();
        }
    }
    
    public void pushFront(Node node){
        if (isEmpty()){

        }else{
            
        }
    }
    
    public void pushBack(Node node) {
        if (isEmpty()) {

        } else {

        }
    }

    public Node findNode(int id){
        if (isEmpty()){
            return new Node();
        } else {
            return new Node();
        }
    }
    
    public Node eraseNode(int id){
        if (isEmpty()){
            return new Node();
        } else {
            return new Node();
        }
    }
    
    public void addNodeAfter(Node node1, Node node2){

    }
    
    public void addNodeBefore(Node node1, Node node2){

    }
    
    public boolean isEmpty(){
        return false;
    }
    public void merge(DoublyLinkedList list){
        
    }
    
    public void printStructure(){
        System.out.println("Hello World!");
    }
    
    // This may be useful for you for implementing printStructure()
    public void printStructureBackward(){ 
        Node current=tail;
        System.out.print(listName + ": tail <-> ");
        while(current!=null){
            System.out.print("{" + current.student_id + "} <-> ");
            current = current.previous;
        }
        System.out.println("head");
    }
    
    public Node whoGotHighestGPA(){
        if (isEmpty()) {
            return new Node();
        } else {
            return new Node();
        }
    }
}
