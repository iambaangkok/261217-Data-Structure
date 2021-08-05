package hw5;

public class Node {

    Node left;
    Node right;
    int data;

    public Node(int data) {

    }

    public void printTree() {
        // uncomment the following line and fix the errors
        // super.printTree(this);
    }

    public void printBFT() {
        Queue q = new Queue(50);
        System.out.print("BFT node sequence [ ");
        System.out.println("]");
    }

    public void printDFT() { // PreOrder
        Stack s = new Stack(50);
        System.out.print("DFT node sequence [ ");
        System.out.println("]");
    }
}
