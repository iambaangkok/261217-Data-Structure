package hw3;

public class DoublyLinkedList {
    Node head;
    Node tail;
    String listName;
    
    public DoublyLinkedList(String name){
        // freshly created list have no element -> no head and tail
        head = null;
        tail = null;    
        listName = name;
    }
    
    public void popBack() {
        if (isEmpty()){
            System.out.println("ERROR");
        }else{
            if(tail == head){
                // dereference both head and tail
                head = null;
                tail = null;
            }else{
                // dereference tail and set tail to the previous node
                tail = tail.previous;
                tail.next = null;
            }
        }
    }
    
    public void popFront(){
        if (isEmpty()){
            System.out.println("ERROR");
        }else{
            if(tail == head){
                // dereference both head and tail
                head = null;
                tail = null;
            }else{
                // dereference head and set head to the next node
                head = head.next;
                head.previous = null;
            }
        }
    }
    
    public Node topFront(){
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else { 
            //front == head -> return head
            return head; 
        }
    }
    
    public Node topBack(){
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else { //back == tail -> return tail
            return tail;
        }
    }
    
    public void pushFront(Node node){
        if (isEmpty()){
            head = node;
            tail = node;
        }else{ 
            // set newNode as head
            node.next = head;
            head.previous = node;
            head = node;
        }
    }
    
    public void pushBack(Node node) {
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            // set newNode as tail
            node.previous = tail;
            tail.next = node;
            tail = node;
        }
    }

    public Node findNode(int id){
        if (isEmpty()){
            return new Node("Empty List!");
        } else {
            Node currNode = head;
            while(currNode != null){ // iterate through the list to find the targetNode(node with matching student_id)
                if(id == currNode.student_id){
                    return currNode;
                }else{
                    currNode = currNode.next;
                }
            }
        }
        return new Node("Student Not Found!");
    }
    
    public Node eraseNode(int id){
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            if(id == head.student_id){ // erase head == popFront
                Node targetNode = topFront();
                popFront();
                return targetNode;
            }
            Node currNode = head;
            while(currNode.next != null){ // iterate through the list to find the node BEFORE the targetNode(node with matching student_id)
                                          // [currNode] <-> [targetNode] <-> [nextNode]
                if(id == currNode.next.student_id){
                    break;
                }else{
                    currNode = currNode.next;
                }
            }
            if(currNode.next == null){
                return new Node("Student Not Found!");
            }
            if(currNode.next.student_id == id){
                if(currNode.next == tail){ // erase tail == popBack
                    Node targetNode = topBack();
                    popBack();
                    return targetNode;
                    
                }else {
                    Node targetNode = currNode.next;
                    Node nextNode = targetNode.next;
                    // dereference targetNode and set currNode to pointforward to nextNode, also set the previous of nextNode to be currNode
                    currNode.next = nextNode;
                    nextNode.previous = currNode;
                    return targetNode;
                }
            }
        }
        return new Node("Student Not Found!");
    }

    public boolean isEqualNode(Node a, Node b){ // check if 2 nodes is equal in terms of data (student_id, name, gpa)
        return (a.student_id == b.student_id && a.name == b.name && a.gpa == b.gpa);
    }
    
    public void addNodeAfter(Node node1, Node node2){
        Node currNode = head;
        while(currNode != null){ // iterate through the list to find the targetNode(node1)
            if(isEqualNode(currNode,node1)){
                break;
            }else{
                currNode = currNode.next;
            }
        }
        if(currNode == null){ // node1 not found
            System.out.println("ERROR");
            return;
        }
        // add node2 to the list   [targetNode]<->[node2]<->[nextNode]
        if(currNode == tail){ // node2 will be the new tail
            pushBack(node2);
        }else{
            Node nextNode = currNode.next;
            currNode.next = node2;
            node2.next = nextNode;
            node2.previous = currNode;
            if(nextNode != null){ // for cases that targetNode == tail
                nextNode.previous = node2;
            }
        }
    }
    
    public void addNodeBefore(Node node1, Node node2){
        Node currNode = head;
        while(currNode != null){ // iterate through the list to find the targetNode(node1)
            if(isEqualNode(currNode,node1)){
                break;
            }else{
                currNode = currNode.next;
            }
        }
        if(currNode == null){ // node1 not found
            System.out.println("ERROR");
            return;
        }
        // add node2 to the list   [prevNode]<->[node2]<->[targetNode]
        if(currNode == head){ // node2 will be the new head
            pushFront(node2);
        }else{
            Node prevNode = currNode.previous;
            currNode.previous = node2;
            node2.previous = prevNode;
            node2.next = currNode;
            if(prevNode != null){ // for cases that targetNode == head
                prevNode.next = node2;
            }
        }
    }
    
    public boolean isEmpty(){ // list is empty if there're no head and no tail 
        return (head == null && tail == null);
    }
    public void merge(DoublyLinkedList list){
        if(head == null){ // if this list is empty then it's just the other list alone
            head = list.head;
        }
        // join this list's tail and the other list's head together
        tail.next = list.head;
        list.head.previous = tail;
    }
    
    public void printStructure(){ // iterate through each node and print student_id from front to back
        Node current=head;
        System.out.print(listName + ": head <-> ");
        while(current!=null){
            System.out.print("{" + current.student_id + "} <-> ");
            current = current.next;
        }
        System.out.println("tail");
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
            return new Node("Empty List!");
        } else {
            Node maxNode = head;
            Node currNode = head;
            while(currNode != null){ 
                // iterate from front to back checking for node with gpa higher than maxNode.gpa, if it's higher, it's the new maxNode
                if(currNode.gpa >= maxNode.gpa){
                    maxNode = currNode;
                }
                currNode = currNode.next;
            }
            return maxNode;
        }
    }
}