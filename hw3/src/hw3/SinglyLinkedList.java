package hw3;

public class SinglyLinkedList {
    Node head;
    String listName;
    
    public SinglyLinkedList(String name){
        // freshly created list have no element -> no head 
        head = null;
        listName = name;
    }
    
    public void popBack() {
        if (isEmpty()){
            System.out.println("ERROR");
        } else {
            if(head.next == null){ // there's only one node
                // dereference head
                head = null;
                return;
            }
            Node currNode = head;
            while(currNode.next.next != null){ // iterate through the list to find the node BEFORE the last node
                currNode = currNode.next;
            }
            // dereference last node
            currNode.next = null;
        }
    }
    
    public void popFront(){
        if (isEmpty()){
            System.out.println("ERROR");
        }else{
            if(head.next == null){ // there's only one node
                // dereference head
                head = null;
            }else{
                // dereference head and set head to the next node
                head = head.next;
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
        } else {
            Node currNode = head;
            while(currNode.next != null){ // iterate through the list to find the last node
                currNode = currNode.next;
            }
            return currNode;
        }
    }
    
    public void pushFront(Node node){
        if (isEmpty()){
            head = node;
        }else{
            // set newNode as head
            node.next = head;
            head = node;
        }
    }
    
    public void pushBack(Node node) {
        if (isEmpty()){
            head = node;
        } else {
            Node currNode = head;
            while(currNode.next != null){ // iterate through the list to find the last node
                currNode = currNode.next;
            }
            // set newNode to be last node  [currNode]->[newNode]->null
            currNode.next = node;
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
                Node targetNode = currNode.next;
                Node nextNode = targetNode.next;
                // dereference targetNode and set currNode to pointforward to nextNode
                currNode.next = nextNode;
                return targetNode;
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
        Node nextNode = currNode.next;
        currNode.next = node2;
        node2.next = nextNode;
    }
    
    public void addNodeBefore(Node node1, Node node2){
        if(isEqualNode(node1, head)){ // add before head == pushFront
            pushFront(node2);
            return;
        }
        Node currNode = head;
        while(currNode.next != null){ // iterate through the list to find the node BEFORE targetNode(node1)
            if(isEqualNode(currNode.next,node1)){
                break;
            }else{
                currNode = currNode.next;
            }
        }
        if(currNode.next == null){ // node1 not found
            System.out.println("ERROR");
            return;
        }
        // add node2 to the list   [currNode]<->[node2]<->[targetNode]
        if(currNode.next != null){
            node2.next = currNode.next;
        }
        currNode.next = node2;
    }
    
    public boolean isEmpty(){ // list is empty if there're no head
        return (head == null);
    }
    public void merge(SinglyLinkedList list){
        if(head == null){ // if this list is empty then it's just the other list alone
            head = list.head;
        }
        Node currNode = head;
        while(currNode.next != null){ // iterate through the list to find the last node
            currNode = currNode.next;
        }
        // join this list's last node and the other list's head together
        currNode.next = list.head;
    }
    
    public void printStructure(){
        Node current=head;
        System.out.print(listName + ": head -> ");
        while(current!=null){
            System.out.print("{" + current.student_id + "} -> ");
            current = current.next;
        }
        System.out.println("null");
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