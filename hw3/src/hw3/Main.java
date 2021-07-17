package hw3;

public class Main {
    public static void main(String[] args) throws Exception {
        Node node;
        DoublyLinkedList list1 = new DoublyLinkedList("list1");
        node = new Node(5906001, "Matthew", 3.50);
        list1.pushFront(node);
        node = new Node(5906002, "Mark", 2.75);
        list1.pushFront(node);
        node = new Node(5906003, "Luke", 3.00);
        list1.pushFront(node);
        node = new Node(5906004, "John", 3.75);
        list1.pushFront(node);
        list1.printStructure();
        DoublyLinkedList list2 = new DoublyLinkedList("list2");
        list2.pushBack(new Node(5906005, "James", 3.25));
        list2.pushBack(new Node(5906006, "Peter", 2.85));
        list2.pushBack(new Node(5906007, "John", 2.50));
        list2.pushBack(new Node(5906008, "Jude", 3.15));
        list2.printStructure();
        list1.merge(list2);
        list1.printStructure();
    }
}
